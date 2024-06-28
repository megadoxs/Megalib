package com.megadoxs.megalib.screen_element.layout;

import com.megadoxs.megalib.Megalib;
import com.megadoxs.megalib.data.MegalibDataTypes;
import com.megadoxs.megalib.screen.UserInterface.UserInterface;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import com.megadoxs.megalib.util.DataType.Axis;
import com.megadoxs.megalib.util.Screen.ScreenElement;
import com.megadoxs.megalib.util.DataType.Size;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.calio.data.SerializableData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.DirectionalLayoutWidget;
import net.minecraft.client.gui.widget.Widget;

import java.util.ArrayList;
import java.util.List;

public class DirectionalLayout implements ScreenElement {

    public static ArrayList<Widget> widgets(SerializableData.Instance data, UserInterface userInterface, int width, int height) {
        List<ScreenElementFactory.Instance> screenElements = data.get("elements");
        DirectionalLayoutWidget layout;

        // sets the axis of the display
        if (data.get("axis").equals(Axis.VERTICAL))
            layout = DirectionalLayoutWidget.vertical();
        else
            layout = DirectionalLayoutWidget.horizontal();

        // alignment will play with those in the future
        layout.setPosition((MinecraftClient.getInstance().getWindow().getScaledWidth()-width)/2, (MinecraftClient.getInstance().getWindow().getScaledWidth()-width)/2);

        // adds all elements to layout
        screenElements.forEach(listElement -> listElement.getWidgets(userInterface, width, height).forEach(widget -> widget.forEachChild(layout::add)));

        // sets elements spacing
        if(data.get("spacing") != null)
            layout.spacing(data.get("spacing"));

        // sets elements positions
        layout.refreshPositions();
        ArrayList<Widget> widgets = new ArrayList<>();
        layout.forEachChild(widgets::add);
        return widgets;
    }

    public static ScreenElementFactory getFactory() {
        return new ScreenElementFactory
                (Megalib.identifier("directional_layout"),
                        new SerializableData()
                                .add("elements", MegalibDataTypes.SCREEN_ELEMENTS)
                                .add("size", MegalibDataTypes.SIZE, new Size(Size.Unit.PERCENTS, 0, 0)) // will be added with the custom class for the widget being done.
                                .add("axis", MegalibDataTypes.AXIS)
                                .add("spacing", ApoliDataTypes.NON_NEGATIVE_INT, null),
                        DirectionalLayout.class
                );
    }
}
