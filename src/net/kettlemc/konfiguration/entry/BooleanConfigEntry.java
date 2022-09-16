package net.kettlemc.konfiguration.entry;

import io.leangen.geantyref.TypeFactory;
import io.leangen.geantyref.TypeToken;
import jdk.nashorn.internal.codegen.types.Type;
import net.kettlemc.konfiguration.Configuration;

public class BooleanConfigEntry extends ConfigEntry<Boolean>{

	public BooleanConfigEntry(Configuration config, Boolean defaultValue, String... path) {
		super(config, defaultValue, path);
	}

	@Override
	Boolean parseValue(String input) {
		return Boolean.parseBoolean(input);
	}

	@Override
	TypeToken getType() {
		return TypeToken.get(Boolean.class.getGenericSuperclass());
	}
}