package com.megadoxs.megalib;

import com.megadoxs.megalib.registry.MegalibRegistries;
import com.megadoxs.megalib.registry.factory.MegalibPowers;
import com.megadoxs.megalib.screen_element.ScreenElements;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Megalib implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String MOD_ID = "megalib";

	@Override
	public void onInitialize() {
		ScreenElements.register();

		MegalibPowers.register();

	}
	public static Identifier identifier(String path) {
		return new Identifier(MOD_ID, path);
	}
}