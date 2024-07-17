package com.megadoxs.megalib.screen_element.element;

import com.megadoxs.megalib.Megalib;
import com.megadoxs.megalib.MegalibClient;
import com.megadoxs.megalib.data.MegalibDataTypes;
import com.megadoxs.megalib.screen.UserInterface.UserInterface;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import com.megadoxs.megalib.util.Screen.MegalibDisplayWidget;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class DisplayScreenElement {

    public static void widgets(SerializableData.Instance data, ArrayList<Widget> widgets, int x, int y, int width, int height) {
        // might add a max and min display that would display something defined when the value is lower or higher.
        int index = widgets.size();

        MegalibDisplayWidget display = new MegalibDisplayWidget(Text.of(""), MinecraftClient.getInstance().textRenderer, data.get("command"));
        DisplayScreenElement.setDisplayValue(index);

        if (data.get("border") != null)
            display.setBorder(data.get("border"));
        if (data.get("text_alignment") != null)
            display.setAlignment(data.get("text_alignment"));
        if (data.get("size") != null)
            display.setSize(data.get("size"), width, height);

        widgets.add(display);
    }

    public static void setDisplayValue(int index){
        // will need to find a way to include this when I build the Display.
        MegalibClient.setDisplayValue(index);

    }

    public static ScreenElementFactory getFactory() {
        return new ScreenElementFactory(
                Megalib.identifier("display"),
                new SerializableData()
                        .add("command", SerializableDataTypes.STRING)
                        .add("size", MegalibDataTypes.SIZE, null)
                        .add("border", MegalibDataTypes.BORDER, null)
                        .add("text_alignment", MegalibDataTypes.ALIGNMENT, null),
                DisplayScreenElement::widgets
        );
    }
}
