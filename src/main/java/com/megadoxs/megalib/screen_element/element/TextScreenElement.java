package com.megadoxs.megalib.screen_element.element;

import com.megadoxs.megalib.Megalib;
import com.megadoxs.megalib.data.MegalibDataTypes;
import com.megadoxs.megalib.screen.UserInterface.UserInterface;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import com.megadoxs.megalib.util.Screen.MegalibTextWidget;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.Widget;

import java.util.ArrayList;

public class TextScreenElement{

    public static void widgets(SerializableData.Instance data, ArrayList<Widget> widgets, int x, int y, int width, int height) {
        MegalibTextWidget text = new MegalibTextWidget(data.get("text"), MinecraftClient.getInstance().textRenderer);
        if (data.get("border") != null)
            text.setBorder(data.get("border"));
        if (data.get("text_alignment") != null)
            text.setAlignment(data.get("text_alignment"));
        if (data.get("size") != null)
            text.setSize(data.get("size"), width, height);

        widgets.add(text);
    }

    public static ScreenElementFactory getFactory() {
        return new ScreenElementFactory(
                Megalib.identifier("text"),
                new SerializableData()
                        .add("text", SerializableDataTypes.TEXT)
                        .add("size", MegalibDataTypes.SIZE, null)
                        .add("border", MegalibDataTypes.BORDER, null)
                        .add("text_alignment", MegalibDataTypes.ALIGNMENT, null),
                TextScreenElement::widgets
        );
    }
}
