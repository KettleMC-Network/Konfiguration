package net.kettlemc.konfiguration.entry;

import io.leangen.geantyref.TypeToken;
import net.kettlemc.konfiguration.Configuration;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.Arrays;

public abstract class ConfigEntry<T> {

	protected final Configuration config;
	protected final String[] path;
	protected final ConfigurationNode node;
	protected T defaultValue;

	ConfigEntry(Configuration config, T defaultValue, String... path) {
		this.config = config;
		this.path = path;
		this.defaultValue = defaultValue;
		this.node = config.getNode(path);
		if (this.node.virtual()) {
			Configuration.LOGGER.debug("Creating config entry for " + Arrays.toString(path));
			try {
				this.config.setValue(node, defaultValue);
			} catch (SerializationException e) {
				Configuration.LOGGER.error("Couldn't save value " + defaultValue + " as " + e.expectedType().getTypeName());
				e.printStackTrace();
			}
			this.config.save();
		}
	}

	abstract T parseValue(String input);

	abstract TypeToken getType();

	public String[] getPath() {
		return path;
	}

	public ConfigurationNode getNode() {
		return config.getNode(path);
	}

	public T getDefaultValue() {
		return defaultValue;
	}

	public T getValue() {
		try {
			return node.virtual() ? defaultValue : (T) config.getValue(node, getType());
		} catch (SerializationException e) {
			Configuration.LOGGER.error("Couldn't get value from node " + node.toString() + " (" + e.expectedType().getTypeName() + ").");
			return defaultValue;
		}
	}

	public void setValue(T value) {
		try {
			this.config.setValue(node, getValue());
		} catch (SerializationException e) {
			Configuration.LOGGER.error("Couldn't save value " + value + " as " + e.expectedType().getTypeName());
			e.printStackTrace();
		}
	}

	public void setDefaultValue(T value) {
		this.defaultValue = value;
	}

	public String toString() {
		return getValue().toString();
	}

}