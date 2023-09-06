
### ⚠ This project is outdated and archived as there are much more powerfull libraries doing basically the same (like [JASKL](https://github.com/Almighty-Satan/JASKL)).


# 📑 Konfiguration
A simple json configuration library based on 'Configurate' and 'MiniMessage'

## How to use it?
Konfiguration is based on config entries. First, you have to create a `Configuration`. Then you can create `ConfigEntries` and add them to the created `Configuration`.

## Example
```java

import net.kettlemc.konfiguration.*;
import java.nio.file.Path;

public class ConfigHandler {

  private static final String EXAMPLE_CONFIG_NAME = "config.json";
  private static final Path CONFIG_FOLDER = ProxySystem.getInstance().getDataDirectory(); // example for a velocity proxy plugin
  private static final Configuration EXAMPLE_CONFIG = new Configuration(CONFIG_FOLDER.resolve(EXAMPLE_CONFIG_NAME));

  static {
    EXAMPLE_CONFIG.load();
  }

  public static final StringConfigEntry EXAMPLE_STRING = new MessageConfigEntry(EXAMPLE_CONFIG, "EXAMPLE STRING", "an", "example", "path");

  public static void printExample() {
    System.out.println(EXAMPLE_STRING.getValue());
  }
}
```
You can also edit a value during runtime by using `ConfigEntry#setValue()` and `Configuration#save()`.

## Content
Konfiguration includes the following config entry types:

- ConfigEntry (basic config entry used as parent class)
- BooleanConfigEntry 
- IntegerConfigEntry
- LongConfigEntry
- ListConfigEntry
- StringConfigEntry

More types can be added with ease by extending ConfigEntry and overriding the Constructor as well as `ConfigEntry#parseValue()` and `ConfigEntry#getType`.
