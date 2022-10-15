package net.kettlemc.konfiguration.entry;

import io.leangen.geantyref.TypeToken;
import net.kettlemc.konfiguration.Configuration;

import java.util.ArrayList;

public class ListConfigEntry<T> extends ConfigEntry<ArrayList<T>>{

	public ListConfigEntry(Configuration config, ArrayList<T> defaultValue, String path) {
		super(config, defaultValue, path);
	}

	@Override
	ArrayList<T> parseValue(String input) {
		// TODO: build parser
		return new ArrayList<>();
	}


	@Override
	TypeToken getType() {
		return TypeToken.get(ArrayList.class.getGenericSuperclass());
	}
}