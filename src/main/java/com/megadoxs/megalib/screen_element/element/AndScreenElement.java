package com.megadoxs.megalib.screen_element.element;

import com.megadoxs.megalib.Megalib;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import com.megadoxs.megalib.util.Screen.ScreenElement;
import io.github.apace100.apoli.power.PowerType;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataType;
import net.minecraft.client.gui.widget.Widget;

import java.util.ArrayList;
import java.util.List;

public class AndScreenElement implements ScreenElement {

    public static ArrayList<Widget> widgets(SerializableData.Instance data, int width, int height, PowerType<?> powerType) {
        List<ScreenElementFactory.Instance> screenElements = data.get("elements");
        ArrayList<Widget> widgets = new ArrayList<>();
        screenElements.forEach(widget -> widgets.addAll(widget.getWidgets(width, height, powerType)));
        return widgets;
    }

    public static ScreenElementFactory getFactory(SerializableDataType<List<ScreenElementFactory.Instance>> listDataType) {
        return new ScreenElementFactory
                (Megalib.identifier("and"),
                new SerializableData()
                        .add("elements", listDataType),
                AndScreenElement.class
        );
    }
}
