package com.megadoxs.megalib.screen_element.element;

import com.megadoxs.megalib.Megalib;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import com.megadoxs.megalib.util.Screen.ScreenElement;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.client.gui.widget.Widget;

import java.util.ArrayList;

public class TextScreenElement implements ScreenElement {

    public static ArrayList<Widget> widgets(SerializableData.Instance data, int width, int height, PowerType<?> powerType) {
        ArrayList<Widget> widget = new ArrayList<>();
        widget.add(new TextWidget(data.get("text"), MinecraftClient.getInstance().textRenderer));
        return widget;
    }

    public static ScreenElementFactory getFactory() {
        return new ScreenElementFactory(
                Megalib.identifier("text"),
                new SerializableData()
                        .add("text", SerializableDataTypes.TEXT),
                TextScreenElement.class
        );
    }
}
