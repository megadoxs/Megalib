package com.megadoxs.megalib.util.Screen;

import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.client.gui.widget.Positioner;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.WrapperWidget;
import net.minecraft.util.math.Divider;

import java.util.Iterator;

//public class Grid extends GridWidget {
//
//    private int maxWidth;
//    private int maxHeight;
//
//    public Grid(){
//        super();
//    }
//
//    public Grid(int x, int y, int maxWidth, int maxHeight) {
//        super(x, y);
//        this.maxWidth = maxWidth;
//        this.maxHeight = maxHeight;
//    }
//
//    public void refreshPositions() {
//        super.refreshPositions();
//        int i = 0;
//        int j = 0;
//
//        Element element;
//        for(Iterator var3 = this.grids.iterator(); var3.hasNext(); j = Math.max(element.getColumnEnd(), j)) {
//            element = (Element)var3.next();
//            i = Math.max(element.getRowEnd(), i);
//        }
//
//        int[] is = new int[j + 1];
//        int[] js = new int[i + 1];
//        Iterator var5 = this.grids.iterator();
//
//        int k;
//        int l;
//        int m;
//        while(var5.hasNext()) {
//            Element element2 = (Element)var5.next();
//            k = element2.getHeight() - (element2.occupiedRows - 1) * this.rowSpacing;
//            Divider divider = new Divider(k, element2.occupiedRows);
//
//            for(l = element2.row; l <= element2.getRowEnd(); ++l) {
//                js[l] = Math.max(js[l], divider.nextInt());
//            }
//
//            l = element2.getWidth() - (element2.occupiedColumns - 1) * this.columnSpacing;
//            Divider divider2 = new Divider(l, element2.occupiedColumns);
//
//            for(m = element2.column; m <= element2.getColumnEnd(); ++m) {
//                is[m] = Math.max(is[m], divider2.nextInt());
//            }
//        }
//
//        int[] ks = new int[j + 1];
//        int[] ls = new int[i + 1];
//        ks[0] = 0;
//
//        for(k = 1; k <= j; ++k) {
//            ks[k] = ks[k - 1] + is[k - 1] + this.columnSpacing;
//        }
//
//        ls[0] = 0;
//
//        for(k = 1; k <= i; ++k) {
//            ls[k] = ls[k - 1] + js[k - 1] + this.rowSpacing;
//        }
//
//        Iterator var17 = this.grids.iterator();
//
//        while(var17.hasNext()) {
//            Element element3 = (Element)var17.next();
//            l = 0;
//
//            int n;
//            for(n = element3.column; n <= element3.getColumnEnd(); ++n) {
//                l += is[n];
//            }
//
//            l += this.columnSpacing * (element3.occupiedColumns - 1);
//            element3.setX(this.getX() + ks[element3.column], l);
//            n = 0;
//
//            for(m = element3.row; m <= element3.getRowEnd(); ++m) {
//                n += js[m];
//            }
//
//            n += this.rowSpacing * (element3.occupiedRows - 1);
//            element3.setY(this.getY() + ls[element3.row], n);
//        }
//
//        this.width = ks[j] + is[j];
//        this.height = ls[i] + js[i];
//    }
//
//    private static class Element extends WrapperWidget.WrappedElement {
//        final int row;
//        final int column;
//        final int occupiedRows;
//        final int occupiedColumns;
//
//        Element(Widget widget, int row, int column, int occupiedRows, int occupiedColumns, Positioner positioner) {
//            super(widget, positioner.toImpl());
//            this.row = row;
//            this.column = column;
//            this.occupiedRows = occupiedRows;
//            this.occupiedColumns = occupiedColumns;
//        }
//
//        public int getRowEnd() {
//            return this.row + this.occupiedRows - 1;
//        }
//
//        public int getColumnEnd() {
//            return this.column + this.occupiedColumns - 1;
//        }
//    }
//}
