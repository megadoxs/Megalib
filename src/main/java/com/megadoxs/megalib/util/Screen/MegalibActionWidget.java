package com.megadoxs.megalib.util.Screen;

import io.github.apace100.apoli.power.factory.action.ActionFactory;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.Entity;

import java.util.function.Function;

public interface MegalibActionWidget {
    void setAction(Function<Widget, ActionFactory<Entity>.Instance> action);
    ActionFactory<Entity>.Instance getAction();
}
