package com.megadoxs.megalib.registry;

import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;



public class MegalibRegistries {

    public static final Registry<ScreenElementFactory> SCREEN_ELEMENT_FACTORY;

    static {
        SCREEN_ELEMENT_FACTORY = create(MegalibRegistryKeys.SCREEN_ELEMENT_FACTORY);
    }

    private static <T> Registry<T> create(RegistryKey<Registry<T>> registryKey) {
        return FabricRegistryBuilder.createSimple(registryKey).buildAndRegister();
    }

}
