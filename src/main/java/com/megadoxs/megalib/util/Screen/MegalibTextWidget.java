package com.megadoxs.megalib.util.Screen;

import com.megadoxs.megalib.util.DataType.Alignment;
import com.megadoxs.megalib.util.DataType.Border;
import com.megadoxs.megalib.util.DataType.Size;
import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.TextWidget;
import net.minecraft.entity.Entity;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.OrderedText;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.util.Language;

import java.util.Objects;

public class MegalibTextWidget extends TextWidget implements MegalibWidgetVisibility{

    // text height is always 7, 8 if draw with shadow
    // use const if I add the possibility to choose to use text shadow or not

    protected Border[] border;
    protected Alignment alignment = Alignment.CENTER; //default alignment to center

    private ConditionFactory<Entity>.Instance visibilityCondition;
    private Boolean visible = true;

    public MegalibTextWidget(Text message, TextRenderer textRenderer) {
        super(textRenderer.getWidth(message) - 1, 7, message, textRenderer);
    }

    public MegalibTextWidget(int x, int y, int width, int height, Text message, TextRenderer textRenderer) {
        super(x, y, width, height, message, textRenderer);
    }

    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float delta) {
        if (visible){
            Text text = this.getMessage();
            TextRenderer textRenderer = this.getTextRenderer();
            int i = this.getWidth();
            int j = textRenderer.getWidth(text) - 1;

            int x = alignment.getX(this.getX() + (border != null && border[2].should_render() ? border[2].width() : 0), this.getWidth() - (border != null && border[2].should_render() ? border[2].width() : 0) - (border != null && border[3].should_render() ? border[3].width() : 0), textRenderer.getWidth(text));
            Objects.requireNonNull(textRenderer);

            int y = alignment.getY(this.getY() + (border != null && border[0].should_render() ? border[0].width() : 0), this.getHeight() - (border != null && border[0].should_render() ? border[0].width() : 0) - (border != null && border[1].should_render() ? border[1].width() : 0), 7); // text height seems to be always 9
            OrderedText orderedText = j > i ? this.trim(text, i) : text.asOrderedText();

            // maybe give the choice to have text shadow or not activated
            context.drawText(textRenderer, orderedText, x, y, this.getTextColor(), false);

            // use megalib$drawBorder to set a border
            if (border != null)
                ((com.megadoxs.megalib.access.DrawContext) context).megalib$drawBorder(this.getX(), this.getY(), width, height, border);
        }
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

    public void setBorder(Border[] border){
        this.border = border;
        // adds border width to widget height
        this.height += (border != null && border[0].should_render() ? border[0].width() : 0) + (border != null && border[1].should_render() ? border[1].width() : 0);
        this.width += (border != null && border[2].should_render() ? border[2].width() : 0) + (border != null && border[3].should_render() ? border[3].width() : 0);
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public void setSize(Size size, int width, int height){
        setHeight(size.getHeight(height));
        setWidth(size.getWidth(width));
    }

    // trims the text to only what will be visible
    // probably can be replaced by the TextRenderer trim method
    private OrderedText trim(Text text, int width) {
        TextRenderer textRenderer = this.getTextRenderer();
        StringVisitable stringVisitable = textRenderer.trimToWidth(text, width - textRenderer.getWidth(ScreenTexts.ELLIPSIS));
        return Language.getInstance().reorder(StringVisitable.concat(stringVisitable, ScreenTexts.ELLIPSIS));
    }
}
