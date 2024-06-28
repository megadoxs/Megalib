package com.megadoxs.megalib.screen_element.layout;

import com.megadoxs.megalib.Megalib;
import com.megadoxs.megalib.screen.UserInterface.UserInterface;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import com.megadoxs.megalib.screen_element.element.AndScreenElement;
import com.megadoxs.megalib.util.Screen.ScreenElement;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataType;
import net.minecraft.client.gui.widget.LayoutWidget;
import net.minecraft.client.gui.widget.Widget;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class DefaultLayout implements ScreenElement {

    public static ArrayList<Widget> widgets(SerializableData.Instance data, UserInterface userInterface, int width, int height) {
        List<ScreenElementFactory.Instance> screenElements = data.get("elements");


            ArrayList<Widget> widgets = new ArrayList<>();
            screenElements.forEach(widget -> widgets.addAll(widget.getWidgets(userInterface, width, height)));
        return widgets;
    }

    public static ScreenElementFactory getFactory(SerializableDataType<List<ScreenElementFactory.Instance>> listDataType) {
        return new ScreenElementFactory
                (Megalib.identifier("and"),
                        new SerializableData()
                                .add("elements", listDataType),
                        DefaultLayout.class
                );
    }
}