package com.megadoxs.megalib.networking.task;

import com.megadoxs.megalib.access.UserInterfaceAction;
import com.megadoxs.megalib.access.UserInterfaceCondition;
import com.megadoxs.megalib.access.UserInterfaceDisplay;
import com.megadoxs.megalib.networking.packet.c2s.IsButtonConditionFulfilledC2SPacket;
import com.megadoxs.megalib.networking.packet.c2s.PerformWidgetActionC2SPacket;
import com.megadoxs.megalib.networking.packet.c2s.SetDisplayValueC2SPacket;
import io.github.apace100.apoli.Apoli;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.concurrent.atomic.AtomicReference;

@SuppressWarnings("UnstableApiUsage")
public class ModPacketsC2S {

    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(PerformWidgetActionC2SPacket.TYPE, ModPacketsC2S::performWidgetActions);
        ServerPlayNetworking.registerGlobalReceiver(IsButtonConditionFulfilledC2SPacket.TYPE, ModPacketsC2S::IsButtonConditionFulfilled);
        ServerPlayNetworking.registerGlobalReceiver(SetDisplayValueC2SPacket.TYPE, ModPacketsC2S::setDisplayValue);
    }

    private static void performWidgetActions(PerformWidgetActionC2SPacket packet, ServerPlayerEntity player, PacketSender responseSender) {
        int index = packet.index();

        if(!player.getWorld().isClient && player instanceof UserInterfaceAction userInterface) {
            userInterface.megalib$getWidgetAction(index).accept(player);
            userInterface.megalib$updateUserInterface();
        }
    }

    private static void IsButtonConditionFulfilled(IsButtonConditionFulfilledC2SPacket packet, ServerPlayerEntity player, PacketSender responseSender) {
        int index = packet.index();

        if(!player.getWorld().isClient && player instanceof UserInterfaceCondition condition)
            condition.megalib$setWidgetConditionResult(index, condition.megalib$getWidgetCondition(index).isFulfilled(player));
    }

    private static void setDisplayValue(SetDisplayValueC2SPacket packet, ServerPlayerEntity player, PacketSender responseSender){
        int index = packet.index();

        // would love to make this work with commands like /data get
        if(!player.getWorld().isClient && player instanceof UserInterfaceDisplay display) {
            MinecraftServer server = player.getServer();
            assert server != null;

            AtomicReference<String> result = new AtomicReference<>();
            ServerCommandSource source = player.getCommandSource()
                    .withOutput(CommandOutput.DUMMY)
                    .withLevel(Apoli.config.executeCommand.permissionLevel)
                    .withReturnValueConsumer((successful, returnValue) -> result.set(String.valueOf(returnValue)));

            String command = display.megalib$getDisplayCommand(index);
            server.getCommandManager().executeWithPrefix(source, command);

            if (command.contains("execute if")){
                if(result.get().equals("1"))
                    result.set("true");
                else
                    result.set("false");
            }

            display.megalib$setDisplayValue(index, result.get());
        }
    }
}
