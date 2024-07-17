package com.megadoxs.megalib.util.Screen;

import com.megadoxs.megalib.util.DataType.Size;
import io.github.apace100.apoli.power.factory.condition.ConditionFactory;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;

public class MegalibDisplayWidget extends MegalibTextWidget {

    private final String command;
    private boolean size = false; // tells if a size was hardCoded

    public MegalibDisplayWidget(Text message, TextRenderer textRenderer, String command) {
        super(message, textRenderer);
        this.command = command;
    }

    // will update the size to be able to display the text if not hardCoded
    public void update(){
        if (!size) // the -1 is because this methods returns the width with text shadow
            setWidth(getTextRenderer().getWidth(getMessage()) - 1 + (border != null && border[2] .should_render() ? border[2].width() : 0) + + (border != null && border[3] .should_render() ? border[3].width() : 0));
    }

    @Override
    public void setSize(Size size, int width, int height){
        super.setSize(size, width, height);
        this.size = true;
    }

    public String getCommand() {
        return command;
    }
}
