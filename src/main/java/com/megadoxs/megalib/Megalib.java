package com.megadoxs.megalib;

import com.megadoxs.megalib.networking.task.ModPacketsC2S;
import com.megadoxs.megalib.registry.factory.MegalibPowers;
import com.megadoxs.megalib.screen_element.ScreenElements;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Megalib implements ModInitializer {
	public static final String MOD_ID = "megalib";

	@Override
	public void onInitialize() {
		ModPacketsC2S.register();

		ScreenElements.register();

		MegalibPowers.register();

	}
	public static Identifier identifier(String path) {
		return new Identifier(MOD_ID, path);
	}
}