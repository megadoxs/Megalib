package com.megadoxs.megalib.screen_element.layout;

import com.megadoxs.megalib.Megalib;
import com.megadoxs.megalib.data.MegalibDataTypes;
import com.megadoxs.megalib.screen.UserInterface.UserInterface;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import com.megadoxs.megalib.util.DataType.Alignment;
import com.megadoxs.megalib.util.DataType.Axis;
import com.megadoxs.megalib.util.DataType.Size;
import com.megadoxs.megalib.util.Screen.MegalibDirectionalLayoutWidget;
import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.client.gui.widget.DirectionalLayoutWidget;
import net.minecraft.client.gui.widget.Widget;

import java.util.ArrayList;
import java.util.List;

public class DirectionalLayout {
    // will be completly changed to use a MegalibScrollableWidget
    public static void widgets(SerializableData.Instance data, ArrayList<Widget> widgets, int x, int y, int width, int height) {
        List<ScreenElementFactory.Instance> screenElements = data.get("elements");
        int index = widgets.size();
        MegalibDirectionalLayoutWidget layout =  new MegalibDirectionalLayoutWidget(
                data.get("axis").equals(Axis.VERTICAL) ? MegalibDirectionalLayoutWidget.DisplayAxis.VERTICAL : MegalibDirectionalLayoutWidget.DisplayAxis.HORIZONTAL,
                width,
                height,
                data.get("overflow")
        );

        Size size = data.get("size");
        layout.setDimensions(size.getWidth(width), size.getHeight(height));

        // adds all elements to the widget list
        screenElements.forEach(listElement -> listElement.getWidgets(widgets, x, y, width, height));

        // adds all the widgets to the layout
        // will cause issue with sub-layout widget because it will also add them to the list
        widgets.subList(index > 0 ? index + 1 : index, widgets.size()).forEach(layout::add);

        // need to add size to this formula
        Alignment alignment = data.get("alignment");
        layout.setPosition(alignment.getX(x, width, layout.getWidth()), alignment.getY(y, height, layout.getHeight()));

        // sets elements spacing
        if(data.get("spacing") != null)
            layout.spacing(data.get("spacing"));

        // sets elements positions
        layout.refreshPositions(); // will probably have to modify how this method works
    }

    public static ScreenElementFactory getFactory() {
        return new ScreenElementFactory
                (Megalib.identifier("directional_layout"),
                        new SerializableData()
                                .add("elements", MegalibDataTypes.SCREEN_ELEMENTS)
                                .add("size", MegalibDataTypes.SIZE, new Size(Size.Unit.PERCENTS, 0, 0))
                                .add("overflow", SerializableDataTypes.BOOLEAN, false)
                                .add("alignment", MegalibDataTypes.ALIGNMENT, Alignment.CENTER)
                                .add("axis", MegalibDataTypes.AXIS)
                                .add("spacing", ApoliDataTypes.NON_NEGATIVE_INT, null),
                        DirectionalLayout::widgets
                );
    }
}
