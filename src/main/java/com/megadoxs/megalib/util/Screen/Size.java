package com.megadoxs.megalib.util.Screen;

public record Size(String unit, int width, int height) {

    // using both these function I can eventually accept more then two unit types
    public int getHeight(int parentHeight){
        if (unit.equals("percents"))
            return parentHeight*this.height/100;
        else
            return height;
    }

    public int getWidth(int parentWidth){
        if (unit.equals("percents"))
            return parentWidth*this.width/100;
        else
            return width;
    }

}
