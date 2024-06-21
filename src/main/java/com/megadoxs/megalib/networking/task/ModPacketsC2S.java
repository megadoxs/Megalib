package com.megadoxs.megalib.networking.task;

import com.megadoxs.megalib.networking.packet.c2s.PerformButtonActionsC2SPacket;
import io.github.apace100.apoli.Apoli;
import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.PowerTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

@SuppressWarnings("UnstableApiUsage")
public class ModPacketsC2S {

    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(PerformButtonActionsC2SPacket.TYPE, ModPacketsC2S::performButtonActions);
    }

    private static void performButtonActions(PerformButtonActionsC2SPacket packet, ServerPlayerEntity player, PacketSender responseSender) {

        PowerHolderComponent component = PowerHolderComponent.KEY.get(player);
        Identifier identifier = packet.identifier();

            PowerType<?> powerType = PowerTypeRegistry.getNullable(identifier);
            if (powerType == null) {
                Apoli.LOGGER.warn("Found unknown power \"{}\" while receiving packet for triggering active powers of player {}!", identifier, player.getName().getString());
            }

            Power power = component.getPower(powerType);
            //something goes here to activate the action on the server
    }
}
