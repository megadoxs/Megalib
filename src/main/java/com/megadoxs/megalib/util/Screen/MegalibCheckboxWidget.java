package com.megadoxs.megalib.util.Screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.apace100.apoli.power.factory.action.ActionFactory;
import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.screen.narration.NarrationPart;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.PressableWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Function;

public class MegalibCheckboxWidget extends PressableWidget implements MegalibActionWidget, MegalibWidgetVisibility {
    private static final Identifier SELECTED_HIGHLIGHTED_TEXTURE = new Identifier("widget/checkbox_selected_highlighted");
    private static final Identifier SELECTED_TEXTURE = new Identifier("widget/checkbox_selected");
    private static final Identifier HIGHLIGHTED_TEXTURE = new Identifier("widget/checkbox_highlighted");
    private static final Identifier TEXTURE = new Identifier("widget/checkbox");
    private static final int TEXT_COLOR = 14737632;
    private static final int field_47105 = 4;
    private static final int field_47106 = 8;
    private boolean checked;
    private final MegalibCheckboxWidget.Callback callback;

    private ConditionFactory<Entity>.Instance visibilityCondition;
    private Boolean visible = true;

    private Function<Widget, ActionFactory<Entity>.Instance> action;

    MegalibCheckboxWidget(int x, int y, Text message, TextRenderer textRenderer, boolean checked, MegalibCheckboxWidget.Callback callback) {
        super(x, y, getSize(textRenderer) + 4 + textRenderer.getWidth(message), getSize(textRenderer), message);
        this.checked = checked;
        this.callback = callback;
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

    public static MegalibCheckboxWidget.Builder builder(Text text, TextRenderer textRenderer, Callback callback) {
        return new MegalibCheckboxWidget.Builder(text, textRenderer, callback);
    }

    private static int getSize(TextRenderer textRenderer) {
        Objects.requireNonNull(textRenderer);
        return 9 + 8;
    }

    public void onPress() {
        this.checked = !this.checked;
        this.callback.onValueChange(this, this.checked);
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void appendClickableNarrations(NarrationMessageBuilder builder) {
        builder.put(NarrationPart.TITLE, this.getNarrationMessage());
        if (this.active) {
            if (this.isFocused()) {
                builder.put(NarrationPart.USAGE, Text.translatable("narration.checkbox.usage.focused"));
            } else {
                builder.put(NarrationPart.USAGE, Text.translatable("narration.checkbox.usage.hovered"));
            }
        }

    }

    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        if (visible) {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            RenderSystem.enableDepthTest();
            TextRenderer textRenderer = minecraftClient.textRenderer;
            context.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
            RenderSystem.enableBlend();
            Identifier identifier;
            if (this.checked) {
                identifier = this.isFocused() ? SELECTED_HIGHLIGHTED_TEXTURE : SELECTED_TEXTURE;
            } else {
                identifier = this.isFocused() ? HIGHLIGHTED_TEXTURE : TEXTURE;
            }

            int i = getSize(textRenderer);
            int j = this.getX() + i + 4;
            int var10000 = this.getY() + (this.height >> 1);
            Objects.requireNonNull(textRenderer);
            int k = var10000 - (9 >> 1);
            context.drawGuiTexture(identifier, this.getX(), this.getY(), i, i);
            context.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            context.drawTextWithShadow(textRenderer, this.getMessage(), j, k, 14737632 | MathHelper.ceil(this.alpha * 255.0F) << 24);
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

    @Environment(EnvType.CLIENT)
    public interface Callback {
        void onValueChange(MegalibCheckboxWidget checkbox, boolean checked);
    }

    @Environment(EnvType.CLIENT)
    public static class Builder {
        private final Text message;
        private final TextRenderer textRenderer;
        private int x = 0;
        private int y = 0;
        private MegalibCheckboxWidget.Callback callback;
        private boolean checked;
        @Nullable
        private Tooltip tooltip;

        Builder(Text message, TextRenderer textRenderer, Callback callback) {
            this.callback = callback;
            this.checked = false;
            this.tooltip = null;
            this.message = message;
            this.textRenderer = textRenderer;
        }

        public MegalibCheckboxWidget.Builder pos(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public MegalibCheckboxWidget.Builder callback(MegalibCheckboxWidget.Callback callback) {
            this.callback = callback;
            return this;
        }

        public MegalibCheckboxWidget.Builder checked(boolean checked) {
            this.checked = checked;
            return this;
        }

        public MegalibCheckboxWidget.Builder tooltip(Tooltip tooltip) {
            this.tooltip = tooltip;
            return this;
        }

        public MegalibCheckboxWidget build() {
            MegalibCheckboxWidget MegalibCheckboxWidget = new MegalibCheckboxWidget(this.x, this.y, this.message, this.textRenderer, this.checked, this.callback);
            MegalibCheckboxWidget.setTooltip(this.tooltip);
            return MegalibCheckboxWidget;
        }
    }
}
