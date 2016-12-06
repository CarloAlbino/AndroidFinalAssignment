package com.robomigos.squadgames.robomigos;

import com.framework.Graphics;
import com.framework.Input;
import com.framework.Pixmap;

/**
 * Created by Carlo Albino on 2016-11-10.
 */

public class Button extends UIElement {

    private Pixmap imageNormal;         // Image of the button in a normal state
    private Pixmap imagePressed;        // Image of the button in a pressed state
    private boolean isPressed;          // Is the button pressed?

    public Button(Graphics g, Pixmap normal, Pixmap pressed, int l, int t, int srcX, int srcY, int screenW, int screenH, float ratio)
    {
        graphics = g;
        imageNormal = normal;
        imagePressed = pressed;
        left = l;
        top = t;
        sourceX = srcX;
        sourceY = srcY;
        sourceWidth = normal.getWidth();
        sourceHeight = normal.getHeight();
        screenWidth = screenW;
        screenHeight = screenH;
        bgRatio = ratio;
        SetBottomRight();
        ConvertToScreenCoord();

        isPressed = false;
    }

    // Helper functions
    public Pixmap GetImage() {return imageNormal;}

    // Draw the button onto the screen
    public void Draw()
    {
        if(isPressed)   // If the button is held down
        {
            graphics.drawPixmap(imagePressed, left, top, sourceX, sourceY, sourceWidth, sourceHeight, screenWidth, screenHeight, bgRatio);
        }
        else            // If the button is normal
        {
            graphics.drawPixmap(imageNormal, left, top, sourceX, sourceY, sourceWidth, sourceHeight, screenWidth, screenHeight, bgRatio);
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

    // Pass in if the button is pressed or not
    public void Pressed(boolean b)
    {
        isPressed = b;
    }
}
