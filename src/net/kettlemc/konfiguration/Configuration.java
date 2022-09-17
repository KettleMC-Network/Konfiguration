package net.kettlemc.konfiguration;

import io.leangen.geantyref.TypeToken;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.gson.GsonConfigurationLoader;
import org.spongepowered.configurate.serialize.SerializationException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Configuration {

    private static final int INDENT = 4;

    public static final Logger LOGGER = Logger.getLogger(Configuration.class.getSimpleName());

    private final Path path;
    private final GsonConfigurationLoader loader;
    private ConfigurationNode configurationRoot;

    public Configuration(Path path) {
        this.path = path;
        this.loader = GsonConfigurationLoader.builder().path(path).indent(INDENT).build();
        if (!this.load()) {
            LOGGER.log(Level.SEVERE, "Couldn't load " + getFileName());
        }
    }

    /**
     * Reads all values out of the file.
     * @return boolean - whether the config could be loaded
     */
    public boolean load() {
        File configFile = path.toFile();
        if (!configFile.exists()) {
            LOGGER.log(Level.INFO, "Creating Configuration file " + getFileName() + ".");
            try {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Couldn't load configuration " + getFileName() + ".");
                e.printStackTrace();
                return false;
            }
        }
        try {
            this.configurationRoot = loader.load();
            return true;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Couldn't load configuration " + getFileName() + ".");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Returns the name of the current config file
     * @return String - name of the config file
     */
    public String getFileName() {
        return path.getFileName().toString();
    }

    /**
     * Saves the config to the file
     * @return boolean - whether the config could be saved
     */
    public boolean save() {
        if (this.configurationRoot == null) {
            LOGGER.log(Level.SEVERE, "Couldn't save configuration " + getFileName() + ": " + " Root Node hasn't been initialized.");
            return false;
        }
        try {
            loader.save(this.configurationRoot);
            return true;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Couldn't save configuration " + getFileName() + ".");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @return GsonConfigurationLoader - The currently used loader for the config
     */
    public GsonConfigurationLoader getLoader() {
        return this.loader;
    }

    /**
     * @return Path - path of the config
     */
    public Path getPath() {
        return this.path;
    }

    /**
     * Returns the node at a certain path from the root node
     * @param path The path of the node
     * @return ConfigurationNode - the node located at the provided path
     */
    public ConfigurationNode getNode(String... path) {
        return this.configurationRoot.node(path);
    }

    /**
     * Sets a config value
     * @param path The path of the node you want to upade
     * @param value The value you want to set
     */
    public void setValue(String path, Object value) throws SerializationException {
        getNode(path).set(value);
    }

    public void setValue(ConfigurationNode node, Object value) throws SerializationException {
        node.set(value);
    }

    /**
     * Gets a config value
     * @param path The path of the node you want to get the value from
     * @return Object - The object located at the provided path or null if it doesn't exist
     */
    public Object getValue(String path, TypeToken type) throws SerializationException {
        return getNode(path).get(type);
    }

    public Object getValue(ConfigurationNode node, TypeToken type) throws SerializationException {
        return node.get(type);
    }

    /**
     * Checks if a config path is valid/exists
     * @param path The path of the node you want to check
     * @return boolean - If the node at the provided path exists (is virtual)
     */
    public boolean valueExists(String path) {
        return getNode(path).virtual();
    }
}
