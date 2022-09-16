package net.kettlemc.konfiguration.entry;

import io.leangen.geantyref.TypeToken;
import net.kettlemc.konfiguration.Configuration;

public class LongConfigEntry extends ConfigEntry<Long> {

	public LongConfigEntry(Configuration config, Long defaultValue, String... path) {
		super(config, defaultValue, path);
	}

	public LongConfigEntry(Configuration config, String... path) {
		this(config, 0L, path);
	}

	@Override
	Long parseValue(String input) {
		return Long.parseLong(input);
	}


	@Override
	TypeToken getType() {
		return TypeToken.get(Long.class.getGenericSuperclass());
	}
}