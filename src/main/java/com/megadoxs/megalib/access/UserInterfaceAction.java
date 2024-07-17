package com.megadoxs.megalib.access;

import io.github.apace100.apoli.power.factory.action.ActionFactory;
import net.minecraft.entity.Entity;

public interface UserInterfaceAction {
    ActionFactory<Entity>.Instance megalib$getWidgetAction(int index);
    void megalib$updateUserInterface();
}
