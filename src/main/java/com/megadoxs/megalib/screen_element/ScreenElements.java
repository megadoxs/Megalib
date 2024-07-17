package com.megadoxs.megalib.screen_element;

import com.megadoxs.megalib.registry.MegalibRegistries;
import com.megadoxs.megalib.screen_element.element.*;
import com.megadoxs.megalib.screen_element.layout.DirectionalLayout;
import io.github.apace100.calio.util.IdentifierAlias;
import net.minecraft.registry.Registry;

public class ScreenElements {

    public static final IdentifierAlias ALIASES = new IdentifierAlias();

    public static void register(){
        register(DirectionalLayout.getFactory());

        register(TextScreenElement.getFactory());
        register(ButtonScreenElement.getFactory());
        register(BooleanButtonScreenElement.getFactory());
        register(TextureScreenElement.getFactory());
        register(DisplayScreenElement.getFactory());
        register(CheckboxScreenElement.getFactory());
    }

    private static void register(ScreenElementFactory screenElementFactory) {
        Registry.register(MegalibRegistries.SCREEN_ELEMENT_FACTORY, screenElementFactory.getSerializerId(), screenElementFactory);
    }

}
