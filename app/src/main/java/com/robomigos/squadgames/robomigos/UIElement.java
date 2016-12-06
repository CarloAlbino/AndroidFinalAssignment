package com.robomigos.squadgames.robomigos;

import com.framework.Graphics;
import com.framework.Pixmap;

/**
 * Created by Carlo Albino on 2016-11-30.
 */

public abstract class UIElement {

    protected Graphics graphics;          // Reference to graphics
    protected int left, top, right, bottom;   // The bounds of the button in screen percentage
    protected int screenLeft, screenTop, screenRight, screenBottom;   // The bounds of the button in pixels
    protected int sourceX, sourceY;             // The start point of the button's image on the PNG (used for sprite sheets)
    protected int sourceWidth, sourceHeight;    // The width and height of the image on the PNG
    protected int screenWidth, screenHeight;  // Screen dimensions
    protected float bgRatio;              // The background to screen ratio

    // Helper functions
    public int Left() {return left;}
    public int Right() {return right;}
    public int Top() {return top;}
    public int Bottom() {return bottom;}
    public int GetWidth()
    {
        return screenRight - screenLeft;
    }
    public int GetHeight()
    {
        return  screenBottom - screenTop;
    }

    public abstract void Draw();

    // Set the right and bottom bounds of the button in screen percentage units
    protected void SetBottomRight()
    {
        right = left + (int)((float)(100 * sourceWidth / screenWidth) * bgRatio);
        bottom = top + (int)((float)(100 * sourceHeight / screenHeight) * bgRatio);
    }

    // Convert the screen percentage units of the bounds to screen coordinates
    protected void ConvertToScreenCoord()
    {
        screenLeft = (int)((left * 0.01f) * screenWidth);
        screenRight = (int)((right * 0.01f) * screenWidth);
        screenTop = (int)((top * 0.01f) * screenHeight);
        screenBottom = (int)((bottom * 0.01f) * screenHeight);
    }

}
