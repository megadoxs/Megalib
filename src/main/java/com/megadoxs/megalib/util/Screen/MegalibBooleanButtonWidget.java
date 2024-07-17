package com.megadoxs.megalib.util.Screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;

public class MegalibBooleanButtonWidget extends MegalibButtonWidget{

    private boolean value;
    private final Text message;

    protected MegalibBooleanButtonWidget(int x, int y, int width, int height, Text message, PressAction onPress, NarrationSupplier narrationSupplier) {
        super(x, y, width, height, Text.of(message.getString() + ": false"), onPress, narrationSupplier);
        value = false;
        this.message = message;
    }

    // this is kinda getting dumb
    public static MegalibBooleanButtonWidget.Builder evenBetterBuilder(Text message, PressAction onPress) {
        return new MegalibBooleanButtonWidget.Builder(message, onPress);
    }

    public void updateValue() {
        // think this can be simplified
        value = !value;
        setMessage(Text.of(message.getString() + ": " + value));
    }

    public boolean getValue(){
        return value;
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

        public MegalibBooleanButtonWidget.Builder position(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public MegalibBooleanButtonWidget.Builder width(int width) {
            this.width = width;
            return this;
        }

        public MegalibBooleanButtonWidget.Builder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public MegalibBooleanButtonWidget.Builder dimensions(int x, int y, int width, int height) {
            return this.position(x, y).size(width, height);
        }

        public MegalibBooleanButtonWidget.Builder tooltip(@Nullable Tooltip tooltip) {
            this.tooltip = tooltip;
            return this;
        }

        public MegalibBooleanButtonWidget.Builder narrationSupplier(NarrationSupplier narrationSupplier) {
            this.narrationSupplier = narrationSupplier;
            return this;
        }

        public MegalibBooleanButtonWidget build() {
            MegalibBooleanButtonWidget megalibBooleanButtonWidgetWidget = new MegalibBooleanButtonWidget(this.x, this.y, this.width, this.height, this.message, this.onPress, this.narrationSupplier);
            megalibBooleanButtonWidgetWidget.setTooltip(this.tooltip);
            return megalibBooleanButtonWidgetWidget;
        }
    }
}
