package com.megadoxs.megalib.screen_element.element;

import com.megadoxs.megalib.Megalib;
import com.megadoxs.megalib.data.MegalibDataTypes;
import com.megadoxs.megalib.screen_element.ScreenElementFactory;
import com.megadoxs.megalib.util.DataType.Size;
import com.megadoxs.megalib.util.Screen.MegalibTextureWidget;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.client.gui.widget.Widget;

import java.util.ArrayList;

public class TextureScreenElement {

    public static void widgets(SerializableData.Instance data, ArrayList<Widget> widgets, int x, int y, int width, int height) {
        Size size = data.get("size");
        // will make my own widget because this one extends from buttonWidget for no reason
        MegalibTextureWidget image = new MegalibTextureWidget(size.getWidth(width), size.getHeight(height), data.get("sprite_location"), size.getWidth(width), size.getHeight(height));
        widgets.add(image);
    }

    public static ScreenElementFactory getFactory() {
        return new ScreenElementFactory(
                Megalib.identifier("texture"),
                new SerializableData()
                        .add("sprite_location", SerializableDataTypes.IDENTIFIER)
                        .add("size", MegalibDataTypes.SIZE, new Size(Size.Unit.PIXELS, 16, 16)),
                TextureScreenElement::widgets
        );
    }
}
