package com.megadoxs.megalib.mixin;

import com.megadoxs.megalib.access.UserInterfaceAction;
import com.megadoxs.megalib.access.UserInterfaceCondition;
import com.megadoxs.megalib.access.UserInterfaceDisplay;
import com.megadoxs.megalib.access.UserInterfaceViewer;
import com.megadoxs.megalib.data.UserInterfaceData;
import com.megadoxs.megalib.networking.packet.s2c.ShowInterfaceS2CPacket;
import com.megadoxs.megalib.screen.UserInterface.UserInterface;
import com.megadoxs.megalib.util.Screen.*;
import com.mojang.authlib.GameProfile;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin extends PlayerEntity implements UserInterfaceViewer, UserInterfaceAction, UserInterfaceCondition, UserInterfaceDisplay {
    public ServerPlayerEntityMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

    @Override
    public void megalib$showInterface(UserInterfaceData interfaceData) {
        ServerPlayNetworking.send((ServerPlayerEntity) (Object) this, new ShowInterfaceS2CPacket(interfaceData));
    }

    @Override
    public void megalib$updateUserInterface(){
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.currentScreen instanceof UserInterface screen)
            screen.update();
    }

    @Override // update the userInterface
    public ActionFactory<Entity>.Instance megalib$getWidgetAction(int index){
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.currentScreen instanceof UserInterface screen) {
            return ((MegalibActionWidget) screen.getWidget(index)).getAction();
        }
        return null;
    }

    @Override // will need change as well
    public ConditionFactory<Entity>.Instance megalib$getWidgetCondition(int index){
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.currentScreen instanceof UserInterface screen){
            if (screen.getWidget(index)instanceof MegalibButtonWidget buttonWidget)
                return buttonWidget.getCondition();
            else if (screen.getWidget(index) instanceof MegalibBooleanButtonWidget booleanButtonWidget)
                return booleanButtonWidget.getCondition();
        }
        return null;
    }

    @Override
    public void megalib$setWidgetConditionResult(int index, boolean result){
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.currentScreen instanceof UserInterface screen)
            ((MegalibButtonWidget) screen.getWidget(index)).active = result;
    }

    @Override // will also be used for boolean Button / Boolean Widget in general
    public void megalib$setDisplayValue(int index, String result) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.currentScreen instanceof UserInterface screen) {
            ((MegalibDisplayWidget) screen.getWidget(index)).setMessage(Text.translatable(result));
            ((MegalibDisplayWidget) screen.getWidget(index)).update();
        }
    }

    @Override
    public String megalib$getDisplayCommand(int index) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.currentScreen instanceof UserInterface screen)
            return ((MegalibDisplayWidget) screen.getWidget(index)).getCommand();
        return null;
    }
}
