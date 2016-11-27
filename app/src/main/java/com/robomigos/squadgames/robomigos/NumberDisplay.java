package com.robomigos.squadgames.robomigos;

import com.framework.Graphics;
import com.framework.Pixmap;

/**
 * Created by Carlo Albino on 2016-11-27.
 */

public class NumberDisplay {
    private Graphics graphics;
    private Pixmap numberFont;
    private int left, top, right, bottom;
    private int screenLeft, screenTop, screenRight, screenBottom;
    private int srcX, srcY;
    private int srcWidth, srcHeight;
    private int screenWidth, screenHeight;
    private float bgRatio;
    private int charW;
    private int numberLength;
    private int fontSize;

    public NumberDisplay(Graphics GRAPHICS, Pixmap FONT, int LEFT, int TOP, int SRCX, int SRCY, int SCREENW, int SCREENH, float BGRATIO)
    {
        graphics = GRAPHICS;
        numberFont = FONT;
        left = LEFT;
        top = TOP;
        srcX = SRCX;
        srcY = SRCY;
        srcWidth = FONT.getWidth();
        srcHeight = FONT.getHeight();
        screenWidth = SCREENW;
        screenHeight = SCREENH;
        bgRatio = BGRATIO;
        SetBottomRight();
        ConvertToScreenCoord();
        charW = (int)Math.ceil((float)FONT.getWidth()/11.0f); //11 characters "0123456789."
        numberLength = 1;
        fontSize = 1;
    }

    // Helper functions
    public int Left() {return left;}
    public int Right() {return right;}
    public int Top() {return top;}
    public int Bottom() {return bottom;}
    public Pixmap GetImage() {return numberFont;}
    public int GetWidth()
    {
        return screenRight - screenLeft;
    }
    public int GetHeight()
    {
        return  screenBottom - screenTop;
    }

    // Draw the number onto the screen
    public void Draw(String number, int size)
    {
        fontSize = size;        //
        SetBottomRight();       // Update dimensions
        ConvertToScreenCoord(); //

        int len = number.length();
        numberLength = len;

        int numXPos = screenLeft;
        for(int i = 0; i < len; i++)
        {
            char character = number.charAt(i);

            if(character == ' ')    // If there is a space
            {
                numXPos += charW;       // charW is gotten in the constructor
                continue;
            }
            int newSrcX;
            int srcWidth;
            if(character == '.')    // If there is a period
            {
                newSrcX = charW*10;      // x10 because we need the length of the 10 previous characters
                srcWidth = charW/2;      // '.' is half the width of the number characters
            }
            else                    // If there is a number
            {
                newSrcX = (character - '0') * charW;
                srcWidth = charW;
            }

            graphics.drawNumber(numberFont, numXPos, screenTop, newSrcX, 0, srcWidth, srcHeight, fontSize);

            numXPos += srcWidth * fontSize;
        }
    }

    // Set the right and bottom bounds of the text in screen percentage units
    private void SetBottomRight()
    {
        right = (left + ((int)((float)(100 * srcWidth/ screenWidth) * bgRatio) * numberLength)) * fontSize;
        bottom = (top + (int)((float)(100 * srcHeight/ screenHeight) * bgRatio)) * fontSize;
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
