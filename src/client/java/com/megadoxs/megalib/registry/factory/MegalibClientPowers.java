package com.megadoxs.megalib.registry.factory;

import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import com.megadoxs.megalib.power.*;
import net.minecraft.registry.Registry;

public class MegalibClientPowers {

    public static void register() {
        register(UserInterfacePower.getFactory());
    }

    public static PowerFactory<?> register(PowerFactory<?> powerFactory) {
        return Registry.register(ApoliRegistries.POWER_FACTORY, powerFactory.getSerializerId(), powerFactory);
    }
}
