package com.megadoxs.megalib;

import com.megadoxs.megalib.networking.packet.c2s.IsButtonConditionFulfilledC2SPacket;
import com.megadoxs.megalib.networking.packet.c2s.PerformWidgetActionC2SPacket;
import com.megadoxs.megalib.networking.packet.c2s.SetDisplayValueC2SPacket;
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
	public static void performWidgetActions(int index) {
		ClientPlayNetworking.send(new PerformWidgetActionC2SPacket(index));
	}

	public static void isButtonConditionFulfilled(int index) {
		// will make it wait for a response
		ClientPlayNetworking.send(new IsButtonConditionFulfilledC2SPacket(index));
	}

	public static void setDisplayValue(int index) {
		// will make it wait for a response
		ClientPlayNetworking.send(new SetDisplayValueC2SPacket(index));
	}
}