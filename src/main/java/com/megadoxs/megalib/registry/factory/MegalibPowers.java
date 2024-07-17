package com.megadoxs.megalib.registry.factory;

import com.megadoxs.megalib.power.UserInterfacePower;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import net.minecraft.registry.Registry;

public class MegalibPowers {

    public static void register() {
        register(UserInterfacePower.getFactory());
    }

    public static PowerFactory<?> register(PowerFactory<?> powerFactory) {
        return Registry.register(ApoliRegistries.POWER_FACTORY, powerFactory.getSerializerId(), powerFactory);
    }
}
