package com.megadoxs.megalib.mixin;

import com.megadoxs.megalib.util.DataType.Border;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.math.ColorHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(DrawContext.class)
public abstract class DrawContextMixin implements com.megadoxs.megalib.access.DrawContext {

    @Unique @Override
    public void megalib$drawBorder(int x, int y, int width, int height, Border[] borders) {
        DrawContext self = (DrawContext) (Object) this;

        // maybe find a way to draw in diagonals
        if (borders[0].should_render())
            self.fill(x, y, x + width, y + borders[0].width(), ColorHelper.Argb.getArgb(borders[0].alpha(), borders[0].red(), borders[0].green(), borders[0].blue()));
        if (borders[1].should_render())
            self.fill(x, y + height, x + width, y + height - borders[1].width(), ColorHelper.Argb.getArgb(borders[1].alpha(), borders[1].red(), borders[1].green(), borders[1].blue()));
        if (borders[2].should_render())
            self.fill(x, y, x + borders[2].width(), y + height, ColorHelper.Argb.getArgb(borders[2].alpha(), borders[2].red(), borders[2].green(), borders[2].blue()));
        if (borders[3].should_render())
            self.fill(x + width, y, x + width - borders[3].width(), y + height, ColorHelper.Argb.getArgb(borders[3].alpha(), borders[3].red(), borders[3].green(), borders[3].blue()));
    }

}
