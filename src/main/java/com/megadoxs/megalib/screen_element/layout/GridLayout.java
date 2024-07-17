package com.megadoxs.megalib.screen_element.layout;

import com.megadoxs.megalib.Megalib;
import com.megadoxs.megalib.screen.UserInterface.UserInterface;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataType;
import net.minecraft.client.gui.widget.Widget;

import java.util.ArrayList;
import java.util.List;

public class GridLayout {

    public static void widgets(SerializableData.Instance data, ArrayList<Widget> widgets, int x, int y, int width, int height) {
        List<ScreenElementFactory.Instance> screenElements = data.get("elements");
        ArrayList<Widget> list = new ArrayList<>();
        screenElements.forEach(widget -> widget.getWidgets(list, x, y, width, height));
        widgets.addAll(list);
    }

    public static ScreenElementFactory getFactory(SerializableDataType<List<ScreenElementFactory.Instance>> listDataType) {
        return new ScreenElementFactory
                (Megalib.identifier("and"),
                        new SerializableData()
                                .add("elements", listDataType),
                        GridLayout::widgets
                );
    }
}
