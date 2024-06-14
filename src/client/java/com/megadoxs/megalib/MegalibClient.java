package com.megadoxs.megalib;

import com.megadoxs.megalib.registry.factory.MegalibClientPowers;
import net.fabricmc.api.ClientModInitializer;

public class MegalibClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		MegalibClientPowers.register();
	}
}