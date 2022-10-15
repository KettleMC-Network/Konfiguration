package net.kettlemc.konfiguration.entry;

import io.leangen.geantyref.TypeToken;
import net.kettlemc.konfiguration.Configuration;

public class IntegerConfigEntry extends ConfigEntry<Integer> {

	public IntegerConfigEntry(Configuration config, Integer defaultValue, String path) {
		super(config, defaultValue, path);
	}

	public IntegerConfigEntry(Configuration config, String path) {
		this(config, 0, path);
	}

	@Override
	Integer parseValue(String input) {
		return Integer.parseInt(input);
	}

	@Override
	TypeToken getType() {
		return TypeToken.get(Integer.class.getGenericSuperclass());
	}
}