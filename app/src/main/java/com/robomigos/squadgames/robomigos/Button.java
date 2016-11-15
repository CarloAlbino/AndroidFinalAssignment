package com.robomigos.squadgames.robomigos;

import com.framework.Graphics;
import com.framework.Input;
import com.framework.Pixmap;

/**
 * Created by Carlo Albino on 2016-11-10.
 */

public class Button {

    private Graphics graphics;
    private Pixmap imageNormal;
    private Pixmap imagePressed;
    private int left, top, right, bottom;
    private int screenLeft, screenTop, screenRight, screenBottom;
    private int srcX, srcY;
    private int srcWidth, srcHeight;
    private int screenWidth, screenHeight;
    private float bgRatio;
    private boolean isPressed;

    public Button(Graphics GRAPHICS, Pixmap iNormal, Pixmap iPressed, int LEFT, int TOP, int SRCX, int SRCY, int SCREENW, int SCREENH, float BGRATIO)
    {
        graphics = GRAPHICS;
        imageNormal = iNormal;
        imagePressed = iPressed;
        left = LEFT;
        top = TOP;
        srcX = SRCX;
        srcY = SRCY;
        srcWidth = iNormal.getWidth();
        srcHeight = iNormal.getHeight();
        screenWidth = SCREENW;
        screenHeight = SCREENH;
        bgRatio = BGRATIO;
        SetBottomRight();
        ConvertToScreenCoord();

        isPressed = false;
    }

    public Button(Graphics GRAPHICS, Pixmap iNormal, Pixmap iPressed, int LEFT, int TOP, int RIGHT, int bottomAdd, int SRCX, int SRCY, int SCREENW, int SCREENH)
    {
        graphics = GRAPHICS;
        imageNormal = iNormal;
        imagePressed = iPressed;
        left = LEFT;
        top = TOP;
        right = RIGHT;
        bottom = top + bottomAdd;
        srcX = SRCX;
        srcY = SRCY;
        srcWidth = iNormal.getWidth();
        srcHeight = iNormal.getHeight();
        screenWidth = SCREENW;
        screenHeight = SCREENH;
        bgRatio = 1;
        ConvertToScreenCoord();

        isPressed = false;
    }

    // Helper functions
    public int Left() {return left;}
    public int Right() {return right;}
    public int Top() {return top;}
    public int Bottom() {return bottom;}
    public Pixmap GetImage() {return imageNormal;}
    public int GetWidth()
    {
        return screenRight - screenLeft;
    }
    public int GetHeight()
    {
        return  screenBottom - screenTop;
    }

    // Draw the button onto the screen
    public void Draw()
    {
        if(isPressed)
        {
            graphics.drawPixmap(imagePressed, left, top, srcX, srcY, srcWidth, srcHeight, screenWidth, screenHeight, bgRatio);
        }
        else
        {
            graphics.drawPixmap(imageNormal, left, top, srcX, srcY, srcWidth, srcHeight, screenWidth, screenHeight, bgRatio);
        }
    }

    public void Draw(int r, int b)
    {
        right = r;
        bottom = b;
        ConvertToScreenCoord();
        if(isPressed)
        {
            graphics.drawPixmap(imagePressed, left, top, right, bottom, srcX, srcY, srcWidth, srcHeight, screenWidth, screenHeight);
        }
        else
        {
            graphics.drawPixmap(imageNormal, left, top, right, bottom, srcX, srcY, srcWidth, srcHeight, screenWidth, screenHeight);
        }
    }

    // Update the position of the button, this can be used to create a moving button
    // or to reuse the same button on different parts of the screen
    public void UpdatePosition(int x, int y)
    {
        left = x;
        top = y;
        SetBottomRight();
        ConvertToScreenCoord();
    }

    // Check if a touch is in the button's bounds
    public boolean IsInBounds(Input.TouchEvent event)
    {
        if (event.x > screenLeft && event.x < screenRight - 1 &&
                event.y > screenTop && event.y < screenBottom - 1)
            return true;
        else
            return false;
    }

    public void Pressed(boolean b)
    {
        isPressed = b;
    }

    // Set the right and bottom bounds of the button in screen percentage units
    private void SetBottomRight()
    {
        right = left + (int)((float)(100 * srcWidth/ screenWidth) * bgRatio);
        bottom = top + (int)((float)(100 * srcHeight/ screenHeight) * bgRatio);
    }

    // Convert the screen percentage units of the bounds to screen coordinates
    private void ConvertToScreenCoord()
    {
        screenLeft = (int)((left * 0.01f) * screenWidth);
        screenRight = (int)((right * 0.01f) * screenWidth);
        screenTop = (int)((top * 0.01f) * screenHeight);
        screenBottom = (int)((bottom * 0.01f) * screenHeight);
    }

}
