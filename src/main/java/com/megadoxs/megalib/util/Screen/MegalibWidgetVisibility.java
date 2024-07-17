package com.megadoxs.megalib.util.Screen;

import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import net.minecraft.entity.Entity;

public interface MegalibWidgetVisibility {
    ConditionFactory<Entity>.Instance getVisibilityCondition();
    void setVisibilityCondition(ConditionFactory<Entity>.Instance condition);
    void setVisibility(boolean visible);
    boolean getVisibility();
}
