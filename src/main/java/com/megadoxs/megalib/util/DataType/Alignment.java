package com.megadoxs.megalib.util.DataType;

public enum Alignment {
    TOP_LEFT {
        @Override
        public int getX(int x, int parentWidth, int width) {
            return x;
        }

        @Override
        public int getY(int y, int parentHeight, int height) {
            return y;
        }

        @Override
        public int getEndX(int x, int parentWidth, int width) {
            return x + width;
        }

        @Override
        public int getEndY(int y, int parentHeight, int height) {
            return y + height;
        }
    },
    TOP {
        @Override
        public int getX(int x, int parentWidth, int width) {
            return x + (parentWidth - width) / 2;
        }

        @Override
        public int getY(int y, int parentHeight, int height) {
            return y;
        }

        @Override
        public int getEndX(int x, int parentWidth, int width) {
            return x + parentWidth - (parentWidth - width) / 2;
        }

        @Override
        public int getEndY(int y, int parentHeight, int height) {
            return y + height;
        }
    },
    TOP_RIGHT {
        @Override
        public int getX(int x, int parentWidth, int width) {
            return x + parentWidth - width;
        }

        @Override
        public int getY(int y, int parentHeight, int height) {
            return y;
        }

        @Override
        public int getEndX(int x, int parentWidth, int width) {
            return x + parentWidth;
        }

        @Override
        public int getEndY(int y, int parentHeight, int height) {
            return y + height;
        }
    },
    CENTER_LEFT {
        @Override
        public int getX(int x, int parentWidth, int width) {
            return x;
        }

        @Override
        public int getY(int y, int parentHeight, int height) {
            return y + (parentHeight - height) / 2;
        }

        @Override
        public int getEndX(int x, int parentWidth, int width) {
            return x + width;
        }

        @Override
        public int getEndY(int y, int parentHeight, int height) {
            return y + parentHeight - (parentHeight - height) / 2;
        }
    },
    CENTER {
        @Override
        public int getX(int x, int parentWidth, int width) {
            return x + (parentWidth - width) / 2;
        }

        @Override
        public int getY(int y, int parentHeight, int height) {
            return y + (parentHeight - height) / 2;
        }

        @Override
        public int getEndX(int x, int parentWidth, int width) {
            return x + parentWidth - (parentWidth - width) / 2;
        }

        @Override
        public int getEndY(int y, int parentHeight, int height) {
            return y + parentHeight - (parentHeight - height) / 2;
        }
    },
    CENTER_RIGHT {
        @Override
        public int getX(int x, int parentWidth, int width) {
            return x + parentWidth - width;
        }

        @Override
        public int getY(int y, int parentHeight, int height) {
            return y + (parentHeight - height) / 2;
        }

        @Override
        public int getEndX(int x, int parentWidth, int width) {
            return x;
        }

        @Override
        public int getEndY(int y, int parentHeight, int height) {
            return y + parentHeight - (parentHeight - height) / 2;
        }
    },
    BOTTOM_LEFT {
        @Override
        public int getX(int x, int parentWidth, int width) {
            return x;
        }

        @Override
        public int getY(int y, int parentHeight, int height) {
            return y + parentHeight - height;
        }

        @Override
        public int getEndX(int x, int parentWidth, int width) {
            return x + width;
        }

        @Override
        public int getEndY(int y, int parentHeight, int height) {
            return y + parentHeight;
        }
    },
    BOTTOM {
        @Override
        public int getX(int x, int parentWidth, int width) {
            return x + (parentWidth - width) / 2;
        }

        @Override
        public int getY(int y, int parentHeight, int height) {
            return y + parentHeight - height;
        }

        @Override
        public int getEndX(int x, int parentWidth, int width) {
            return x + parentWidth - (parentWidth - width) / 2;
        }

        @Override
        public int getEndY(int y, int parentHeight, int height) {
            return y + parentHeight;
        }
    },
    BOTTOM_RIGHT {
        @Override
        public int getX(int x, int parentWidth, int width) {
            return x + parentWidth - width;
        }

        @Override
        public int getY(int y, int parentHeight, int height) {
            return y + parentHeight - height;
        }

        @Override
        public int getEndX(int x, int parentWidth, int width) {
            return x + parentWidth;
        }

        @Override
        public int getEndY(int y, int parentHeight, int height) {
            return y + parentHeight;
        }
    };

    public abstract int getX(int x, int parentWidth, int width);
    public abstract int getY(int y, int parentHeight, int height);
    public abstract int getEndX(int x, int parentWidth, int width);
    public abstract int getEndY(int y, int parentHeight, int height);
}
