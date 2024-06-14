package com.megadoxs.megalib;

import com.megadoxs.megalib.registry.factory.MegalibClientPowers;
import net.fabricmc.api.ClientModInitializer;

public class MegalibClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		MegalibClientPowers.register();
	}
}