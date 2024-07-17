package com.megadoxs.megalib.util.Screen;

import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

// maybe add a special tooltip for when the button is deactivated (optional).
public class MegalibButtonWidget extends ButtonWidget implements MegalibActionWidget, MegalibWidgetVisibility{

    private Function<Widget, ActionFactory<Entity>.Instance> action;
    private ConditionFactory<Entity>.Instance condition;
    private ConditionFactory<Entity>.Instance visibilityCondition;
    private Boolean visible = true;

    public static MegalibButtonWidget.Builder betterBuilder(Text message, PressAction onPress) {
        return new MegalibButtonWidget.Builder(message, onPress);
    }

    public MegalibButtonWidget(int x, int y, int width, int height, Text message, PressAction onPress) {
        super(x, y, width, height, message, onPress, ButtonWidget.DEFAULT_NARRATION_SUPPLIER);
    }

    @Override
    public ConditionFactory<Entity>.Instance getVisibilityCondition() {
        return visibilityCondition;
    }

    @Override
    public void setVisibilityCondition(ConditionFactory<Entity>.Instance condition) {
        visibilityCondition = condition;
    }

    @Override
    public void setVisibility(boolean visible) {
        this.visible = visible;
    }

    @Override
    public boolean getVisibility() {
        return visible;
    }

    @Override
    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        if (visible)
            super.renderWidget(context, mouseX, mouseY, delta);
    }

    // builder will be removed because I don't need it xdd
    @Environment(EnvType.CLIENT)
    public static class Builder {
        private final Text message;
        private final PressAction onPress;
        @Nullable
        private Tooltip tooltip;
        private int x;
        private int y;
        private int width = 150;
        private int height = 20;

        public Builder(Text message, PressAction onPress) {
            this.message = message;
            this.onPress = onPress;
        }

        public MegalibButtonWidget.Builder position(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public MegalibButtonWidget.Builder width(int width) {
            this.width = width;
            return this;
        }

        public MegalibButtonWidget.Builder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public MegalibButtonWidget.Builder dimensions(int x, int y, int width, int height) {
            return this.position(x, y).size(width, height);
        }

        public MegalibButtonWidget.Builder tooltip(@Nullable Tooltip tooltip) {
            this.tooltip = tooltip;
            return this;
        }

        public MegalibButtonWidget build() {
            MegalibButtonWidget megalibButtonWidgetWidget = new MegalibButtonWidget(this.x, this.y, this.width, this.height, this.message, this.onPress);
            megalibButtonWidgetWidget.setTooltip(this.tooltip);
            return megalibButtonWidgetWidget;
        }
    }

    @Override
    public ActionFactory<Entity>.Instance getAction() {
        return action.apply(this);
    }

    @Override
    public void setAction(Function<Widget, ActionFactory<Entity>.Instance> action) {
        this.action = action;
    }

    public ConditionFactory<Entity>.Instance getCondition() {
        return condition;
    }

    public void setCondition(ConditionFactory<Entity>.Instance condition) {
        this.condition = condition;
    }
}
