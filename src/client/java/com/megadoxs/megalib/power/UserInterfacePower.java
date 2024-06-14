package com.megadoxs.megalib.power;

import com.megadoxs.megalib.Megalib;
import com.megadoxs.megalib.content.UserInterface;
import com.sun.jna.platform.unix.X11;
import io.github.apace100.apoli.Apoli;
import io.github.apace100.apoli.component.PowerHolderComponent;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.Active;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataType;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.text.*;
import net.minecraft.util.Pair;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Predicate;

@SuppressWarnings("unused")
public class UserInterfacePower extends Power implements Active {

    private final UserInterface screen;

    private String screenTitle;
    private Key key;

    public UserInterfacePower(PowerType<?> type, LivingEntity entity, String screenTitle) {
        super(type, entity);
        this.screen = new UserInterface(screenTitle, 200, 200);
        this.setTicking(true);
    }

    @Override
    public void onUse() {
        if (this.isActive() && entity instanceof PlayerEntity player) {

        }
    }

    @Override
    public Key getKey() {
        return key;
    }

    @Override
    public void setKey(Key key) {
        this.key = key;
    }

    public static PowerFactory<?> getFactory() {
        return new PowerFactory<>(Apoli.identifier("UserInterface"),
                new SerializableData()
                        .add("key", ApoliDataTypes.BACKWARDS_COMPATIBLE_KEY, new Active.Key())
                        .add("title", SerializableDataTypes.STRING, "User Interface"),
                data ->
                        (powerType, livingEntity) -> {
                            UserInterfacePower UserInterfacePower = new UserInterfacePower(
                                    powerType,
                                    livingEntity,
                                    data.getString("title")
                            );
                            UserInterfacePower.setKey(data.get("key"));
                            return UserInterfacePower;
                        })
                .allowCondition();
    }
}
