package com.megadoxs.megalib.util.DataType;

import io.github.apace100.apoli.Apoli;
import net.minecraft.client.MinecraftClient;

//might make it a class again since I have some case were it doesn't need to be defined, or at least all values have to
public record Size(Unit unit, int width, int height) {

    public enum Unit {
        PERCENTS,
        PIXELS
    }

    // using both these function I can eventually accept more than two unit types
    public int getHeight(int parentHeight){
        if (unit.equals(Unit.PERCENTS)) {
            if (this.height >= 100)
                return parentHeight;
            else
                return parentHeight * this.height / 100;
        }
        else
            return this.height;
    }

    public int getWidth(int parentWidth){
        if (unit.equals(Unit.PERCENTS)) {
            if (this.width >= 100)
                return parentWidth;
            else
                return parentWidth * this.width / 100;
        }
        else
            return this.width;
    }

}
