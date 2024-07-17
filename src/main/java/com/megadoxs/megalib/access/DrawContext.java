package com.megadoxs.megalib.access;

import com.megadoxs.megalib.util.DataType.Border;

// will probably rename that interface cause it annoying to have it named the same thing as the class for imports
public interface DrawContext {
    void megalib$drawBorder(int x, int y, int width, int height, Border[] borders);
}
