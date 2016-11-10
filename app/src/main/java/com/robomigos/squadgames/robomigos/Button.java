package com.robomigos.squadgames.robomigos;

import com.framework.Graphics;
import com.framework.Input;
import com.framework.Pixmap;

/**
 * Created by Carlo Albino on 2016-11-10.
 */

public class Button {

    private Graphics graphics;
    private Pixmap image;
    private int left, top, right, bottom;
    private int screenLeft, screenTop, screenRight, screenBottom;
    private int srcX, srcY;
    private int srcWidth, srcHeight;
    private int screenWidth, screenHeight;
    private float bgRatio;

    public Button(Graphics GRAPHICS, Pixmap IMAGE, int LEFT, int TOP, int SRCX, int SRCY, int SCREENW, int SCREENH, float BGRATIO)
    {
        graphics = GRAPHICS;
        image = IMAGE;
        left = LEFT;
        top = TOP;
        srcX = SRCX;
        srcY = SRCY;
        srcWidth = IMAGE.getWidth();
        srcHeight = IMAGE.getHeight();
        screenWidth = SCREENW;
        screenHeight = SCREENH;
        bgRatio = BGRATIO;
        SetBottomRight();
        ConvertToScreenCoord();
    }

    public int Left() {return left;}
    public int Right() {return right;}
    public int Top() {return top;}
    public int Bottom() {return bottom;}
    public Pixmap GetImage() {return image;}

    public int GetWidth()
    {
        return screenRight - screenLeft;
    }

    public int GetHeight()
    {
        return  screenBottom - screenTop;
    }
    
    public void Draw()
    {
        graphics.drawPixmap(image, left, top, srcX, srcY, srcWidth, srcHeight, screenWidth, screenHeight, bgRatio);
    }

    public void UpdatePosition(int x, int y)
    {
        left = x;
        top = y;
        SetBottomRight();
        ConvertToScreenCoord();
    }

    public boolean IsInBounds(Input.TouchEvent event)
    {
        if (event.x > screenLeft && event.x < screenRight - 1 &&
                event.y > screenTop && event.y < screenBottom - 1)
            return true;
        else
            return false;
    }

    private void SetBottomRight()
    {
        right = left + (int)((float)(100 * srcWidth/ screenWidth) * bgRatio);
        bottom = top + (int)((float)(100 * srcHeight/ screenHeight) * bgRatio);
    }

    private void ConvertToScreenCoord()
    {
        screenLeft = (int)((left * 0.01f) * screenWidth);
        screenRight = (int)((right * 0.01f) * screenWidth);
        screenTop = (int)((top * 0.01f) * screenHeight);
        screenBottom = (int)((bottom * 0.01f) * screenHeight);
    }

}
