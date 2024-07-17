package com.megadoxs.megalib.registry;

import com.megadoxs.megalib.Megalib;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;



public class MegalibRegistryKeys {

    public static final RegistryKey<Registry<ScreenElementFactory>> SCREEN_ELEMENT_FACTORY;

    static {
        SCREEN_ELEMENT_FACTORY = create("screen_element");
    }

    private static <T> RegistryKey<Registry<T>> create(String path) {
        return RegistryKey.ofRegistry(Megalib.identifier(path));
    }

}
