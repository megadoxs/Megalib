package com.megadoxs.megalib.util.Screen;

import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.narration.NarrationMessageBuilder;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

// I really wish I didn't have to send an empty text here
public class MegalibTextureWidget extends ClickableWidget implements MegalibWidgetVisibility {
    private final Identifier texture;
    private final int textureWidth;
    private final int textureHeight;

    private ConditionFactory<Entity>.Instance visibilityCondition;
    private Boolean visible = true;

    public MegalibTextureWidget(int x, int y, int width, int height, Identifier texture, int textureWidth, int textureHeight){
        super(x, y, width, height, Text.of(""));
        this.texture = texture;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    public MegalibTextureWidget(int width, int height, Identifier texture, int textureWidth, int textureHeight){
        super(0, 0, width, height, Text.of(""));
        this.texture = texture;
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
    }

    protected void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        if (this.visible)
            context.drawTexture(this.texture, this.getX(), this.getY(), this.getWidth(), this.getHeight(), 0.0F, 0.0F, this.getWidth(), this.getHeight(), this.textureWidth, this.textureHeight);
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
    protected void appendClickableNarrations(NarrationMessageBuilder builder) {

    }
}
