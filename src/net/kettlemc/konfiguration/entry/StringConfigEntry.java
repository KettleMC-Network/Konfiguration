package net.kettlemc.konfiguration.entry;

import io.leangen.geantyref.TypeToken;
import net.kettlemc.konfiguration.Configuration;

public class StringConfigEntry extends ConfigEntry<String> {

	public StringConfigEntry(Configuration config, String defaultValue, String path) {
		super(config, defaultValue, path);
	}

	@Override
	String parseValue(String input) {
		return input;
	}

	@Override
	TypeToken getType() {
		return TypeToken.get(String.class.getGenericSuperclass());
	}
}