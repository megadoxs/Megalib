package com.megadoxs.megalib.power;

import com.megadoxs.megalib.Megalib;
import com.megadoxs.megalib.access.UserInterfaceViewer;
import com.megadoxs.megalib.data.UserInterfaceData;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.Active;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.LivingEntity;

import java.util.ArrayList;

@SuppressWarnings("unused")
public class UserInterfacePower extends Power implements Active {
    private Key key;

    private final SerializableData.Instance data;

    public UserInterfacePower(PowerType<?> type, LivingEntity entity, SerializableData.Instance data) {
        super(type, entity);
        this.data = data;
        this.setTicking(true);
    }

    @Override
    public void onUse() {
        // the condition apoli's toast power was using
        // !entity.getWorld().isClient && entity instanceof UserInterfaceViewer viewer
        // the condition I changed it to and works now...
        if (entity.getWorld().isClient && entity instanceof UserInterfaceViewer viewer) {
            viewer.megalib$showInterface(UserInterfaceData.fromData(data), getType());
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
        return new PowerFactory<>(
                Megalib.identifier("user_interface"),
                        UserInterfaceData.DATA
                        .add("key", ApoliDataTypes.BACKWARDS_COMPATIBLE_KEY, new Active.Key()),
                data -> (powerType, livingEntity) -> {
                    UserInterfacePower userInterfacePower = new UserInterfacePower(
                            powerType,
                            livingEntity,
                            data
                            );
                    userInterfacePower.setKey(data.get("key"));
                    return userInterfacePower;
                }).allowCondition();
    }
}
