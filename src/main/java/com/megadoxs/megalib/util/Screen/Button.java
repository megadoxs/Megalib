package com.megadoxs.megalib.util.Screen;

import io.github.apace100.apoli.Apoli;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class Button extends ButtonWidget {

    private ActionFactory<Entity>.Instance action;

    private ConditionFactory<Entity>.Instance condition;

    public static Button.Builder betterBuilder(Text message, PressAction onPress) {
        return new Button.Builder(message, onPress);
    }
    protected Button(int x, int y, int width, int height, Text message, PressAction onPress, NarrationSupplier narrationSupplier) {
        super(x, y, width, height, message, onPress, narrationSupplier);
    }

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
        private NarrationSupplier narrationSupplier;

        public Builder(Text message, PressAction onPress) {
            this.narrationSupplier = ButtonWidget.DEFAULT_NARRATION_SUPPLIER;
            this.message = message;
            this.onPress = onPress;
        }

        public Button.Builder position(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Button.Builder width(int width) {
            this.width = width;
            return this;
        }

        public Button.Builder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Button.Builder dimensions(int x, int y, int width, int height) {
            return this.position(x, y).size(width, height);
        }

        public Button.Builder tooltip(@Nullable Tooltip tooltip) {
            this.tooltip = tooltip;
            return this;
        }

        public Button.Builder narrationSupplier(NarrationSupplier narrationSupplier) {
            this.narrationSupplier = narrationSupplier;
            return this;
        }

        public Button build() {
            Button buttonWidget = new Button(this.x, this.y, this.width, this.height, this.message, this.onPress, this.narrationSupplier);
            buttonWidget.setTooltip(this.tooltip);
            return buttonWidget;
        }
    }

    public ActionFactory<Entity>.Instance getAction() {
        return action;
    }

    public void setAction(ActionFactory<Entity>.Instance action) {
        this.action = action;
    }

    public ConditionFactory<Entity>.Instance getCondition() {
        return condition;
    }

    public void setCondition(ConditionFactory<Entity>.Instance condition) {
        this.condition = condition;
    }
}
