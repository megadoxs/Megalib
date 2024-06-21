package com.megadoxs.megalib.screen_element;

import com.megadoxs.megalib.data.MegalibDataTypes;
import com.megadoxs.megalib.registry.MegalibRegistries;
import com.megadoxs.megalib.screen_element.element.AndScreenElement;
import com.megadoxs.megalib.screen_element.element.ButtonScreenElement;
import com.megadoxs.megalib.screen_element.element.TextScreenElement;
import io.github.apace100.calio.util.IdentifierAlias;
import net.minecraft.entity.Entity;
import net.minecraft.registry.Registry;

public class ScreenElements {

    public static final IdentifierAlias ALIASES = new IdentifierAlias();

    public static void register(){
        register(AndScreenElement.getFactory(MegalibDataTypes.SCREEN_ELEMENTS));

        register(TextScreenElement.getFactory());
        register(ButtonScreenElement.getFactory());
    }

    private static void register(ScreenElementFactory screenElementFactory) {
        Registry.register(MegalibRegistries.SCREEN_ELEMENT_FACTORY, screenElementFactory.getSerializerId(), screenElementFactory);
    }

}
