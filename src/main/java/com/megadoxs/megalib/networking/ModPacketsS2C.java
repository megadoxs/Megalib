package com.megadoxs.megalib.networking;

import com.megadoxs.megalib.access.UserInterfaceViewer;
import com.megadoxs.megalib.networking.packet.s2c.UserInterfaceS2CPacket;
import io.github.apace100.apoli.Apoli;
import io.github.apace100.apoli.networking.packet.VersionHandshakePacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientConfigurationNetworking;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.network.ClientPlayerEntity;

@SuppressWarnings("UnstableApiUsage")
@Environment(EnvType.CLIENT)
public class ModPacketsS2C {

    public static void register() {

        ClientConfigurationNetworking.registerGlobalReceiver(VersionHandshakePacket.TYPE, ModPacketsS2C::handleHandshake);

        ClientPlayConnectionEvents.INIT.register(((clientPlayNetworkHandler, minecraftClient) -> {
            ClientPlayNetworking.registerReceiver(UserInterfaceS2CPacket.TYPE, ModPacketsS2C::onShowInterface);
        }));

    }

    private static void handleHandshake(VersionHandshakePacket packet, PacketSender responseSender) {
        responseSender.sendPacket(new VersionHandshakePacket(Apoli.SEMVER));
    }

    public static void onShowInterface(UserInterfaceS2CPacket packet, ClientPlayerEntity player, PacketSender responseSender) {
        if (player instanceof UserInterfaceViewer viewer) {
            viewer.megalib$showInterface(packet.InterfaceData());
        }
    }

}
