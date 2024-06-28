package com.megadoxs.megalib.util.Screen;

import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataTypes;
import net.minecraft.client.gui.widget.Widget;

import java.util.ArrayList;

public interface ScreenElement {

    // this solution is scuffed af, please find a way to make it less shit.
    // this static method doesn't do shit, wish I could override it.
    static ArrayList<Widget> widgets(SerializableData.Instance data, int width, int height) {
        return null;
    }
}
