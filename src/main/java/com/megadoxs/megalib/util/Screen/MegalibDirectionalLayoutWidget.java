package com.megadoxs.megalib.util.Screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.widget.LayoutWidget;
import net.minecraft.client.gui.widget.Positioner;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.Util;

import java.util.function.Consumer;

@Environment(EnvType.CLIENT)
public class MegalibDirectionalLayoutWidget implements LayoutWidget {
    private final MegalibGridWidget grid;
    private final DisplayAxis axis;
    private int currentIndex;

    public MegalibDirectionalLayoutWidget(DisplayAxis axis, int maxWidth, int maxHeight, boolean overflow) {
        this(0, 0, axis, maxWidth, maxHeight, overflow);
    }

    public MegalibDirectionalLayoutWidget(int x, int y, DisplayAxis axis, int maxWidth, int maxHeight, boolean overflow) {
        this.currentIndex = 0;
        this.grid = new MegalibGridWidget(x, y, maxWidth, maxHeight, overflow);
        this.axis = axis;
    }

    public MegalibDirectionalLayoutWidget spacing(int spacing) {
        this.axis.setSpacing(this.grid, spacing);
        return this;
    }

    public Positioner copyPositioner() {
        return this.grid.copyPositioner();
    }

    public Positioner getMainPositioner() {
        return this.grid.getMainPositioner();
    }

    public <T extends Widget> T add(T widget, Positioner positioner) {
        return this.axis.add(this.grid, widget, this.currentIndex++, positioner);
    }

    public <T extends Widget> T add(T widget) {
        return this.add(widget, this.copyPositioner());
    }

    public <T extends Widget> T add(T widget, Consumer<Positioner> callback) {
        return this.axis.add(this.grid, widget, this.currentIndex++, (Positioner) Util.make(this.copyPositioner(), callback));
    }

    public void forEachElement(Consumer<Widget> consumer) {
        this.grid.forEachElement(consumer);
    }

    public void refreshPositions() {
        this.grid.refreshPositions();
    }

    public int getWidth() {
        return this.grid.getWidth();
    }

    public int getHeight() {
        return this.grid.getHeight();
    }

    public void setX(int x) {
        this.grid.setX(x);
    }

    public void setY(int y) {
        this.grid.setY(y);
    }

    public int getX() {
        return this.grid.getX();
    }

    public int getY() {
        return this.grid.getY();
    }

    public void setDimensions(int width, int height){
        this.grid.setDimensions(width, height);
    }

    @Environment(EnvType.CLIENT)
    public static enum DisplayAxis {
        HORIZONTAL,
        VERTICAL;

        private DisplayAxis() {
        }

        void setSpacing(MegalibGridWidget grid, int spacing) {
            switch (this) {
                case HORIZONTAL -> grid.setColumnSpacing(spacing);
                case VERTICAL -> grid.setRowSpacing(spacing);
            }

        }

        public <T extends Widget> T add(MegalibGridWidget grid, T widget, int index, Positioner positioner) {
            Widget var10000;
            switch (this) {
                case HORIZONTAL -> var10000 = grid.add(widget, 0, index, positioner);
                case VERTICAL -> var10000 = grid.add(widget, index, 0, positioner);
                default -> throw new IncompatibleClassChangeError();
            }

            return (T) var10000; // confused why I have to cast here.
        }
    }
}
