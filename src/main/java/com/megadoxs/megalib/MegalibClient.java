package com.megadoxs.megalib;

import com.megadoxs.megalib.networking.packet.c2s.PerformButtonActionsC2SPacket;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class MegalibClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
	}

	public static void performButtonActions(Identifier identifier) {
		ClientPlayNetworking.send(new PerformButtonActionsC2SPacket(identifier));
	}
}