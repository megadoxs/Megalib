package com.megadoxs.megalib.mixin;

import com.megadoxs.megalib.access.UserInterfaceViewer;
import com.megadoxs.megalib.data.UserInterfaceData;
import com.megadoxs.megalib.screen.UserInterface.UserInterface;
import com.mojang.authlib.GameProfile;
import io.github.apace100.apoli.power.PowerType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ClientPlayerEntity.class)
public abstract class ClientPlayerEntityMixin extends AbstractClientPlayerEntity implements UserInterfaceViewer {
    public ClientPlayerEntityMixin(ClientWorld world, GameProfile profile) {
        super(world, profile);
    }

    @Override
    public void megalib$showInterface(UserInterfaceData InterfaceData, PowerType<?> powerType) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.execute(() -> {
            UserInterface userInterface = new UserInterface(InterfaceData, powerType);
            client.setScreen(userInterface);
        });
    }
}
