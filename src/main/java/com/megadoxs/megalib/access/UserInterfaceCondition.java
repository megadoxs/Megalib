package com.megadoxs.megalib.access;

import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import net.minecraft.entity.Entity;

public interface UserInterfaceCondition {
    ConditionFactory<Entity>.Instance megalib$getWidgetCondition(int index);

    void megalib$setWidgetConditionResult(int index, boolean result);
}
