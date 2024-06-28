package com.megadoxs.megalib.networking.task;

import com.megadoxs.megalib.access.UserInterfaceAction;
import com.megadoxs.megalib.access.UserInterfaceCondition;
import com.megadoxs.megalib.networking.packet.c2s.PerformButtonActionsC2SPacket;
import com.megadoxs.megalib.networking.packet.c2s.IsButtonConditionFulfilledC2SPacket;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;

@SuppressWarnings("UnstableApiUsage")
public class ModPacketsC2S {

    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(PerformButtonActionsC2SPacket.TYPE, ModPacketsC2S::performButtonActions);
        ServerPlayNetworking.registerGlobalReceiver(IsButtonConditionFulfilledC2SPacket.TYPE, ModPacketsC2S::IsButtonConditionFulfilled);
    }

    // I'm thinking I could maybe return a ScreenElementFactory and only have one function in the mixin instead that returns the whole widget
    private static void performButtonActions(PerformButtonActionsC2SPacket packet, ServerPlayerEntity player, PacketSender responseSender) {
        int index = packet.index();

        if(!player.getWorld().isClient && player instanceof UserInterfaceAction action)
            action.megalib$getWidgetAction(index).accept(player);
    }

    private static void IsButtonConditionFulfilled(IsButtonConditionFulfilledC2SPacket packet, ServerPlayerEntity player, PacketSender responseSender) {
        int index = packet.index();

        if(!player.getWorld().isClient && player instanceof UserInterfaceCondition condition)
            condition.megalib$setWidgetConditionResult(index, condition.megalib$getWidgetCondition(index).isFulfilled(player));
    }
}
