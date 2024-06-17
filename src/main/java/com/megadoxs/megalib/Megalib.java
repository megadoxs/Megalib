package com.megadoxs.megalib;

import com.megadoxs.megalib.registry.factory.MegalibPowers;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Megalib implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.

	public static final String MOD_ID = "megalib";
    public static final Logger LOGGER = LoggerFactory.getLogger("Megalib.class");

	@Override
	public void onInitialize() {
		MegalibPowers.register();

		LOGGER.info("Megalib has initialized. Ready to power up your game!");
	}
	public static Identifier identifier(String path) {
		return new Identifier(MOD_ID, path);
	}
}