package com.megadoxs.megalib;

import com.megadoxs.megalib.networking.packet.c2s.PerformButtonActionsC2SPacket;
import com.megadoxs.megalib.networking.packet.c2s.IsButtonConditionFulfilledC2SPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

@Environment(EnvType.CLIENT)
public class MegalibClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
	}

	// does will only work for buttons,
	public static void performButtonActions(int index) {
		ClientPlayNetworking.send(new PerformButtonActionsC2SPacket(index));
	}

	public static void isButtonConditionFulfilled(int index) {
		ClientPlayNetworking.send(new IsButtonConditionFulfilledC2SPacket(index));
	}
}