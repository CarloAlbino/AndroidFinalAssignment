package com.robomigos.squadgames.robomigos;

import com.framework.Graphics;
import com.framework.Pixmap;

/**
 * Created by Carlo Albino on 2016-11-27.
 */

public class NumberDisplay extends UIElement {

    private Pixmap numberFont;
    private int charW;
    private int numberLength;
    private int fontSize;

    public NumberDisplay(Graphics GRAPHICS, Pixmap FONT, int LEFT, int TOP, int SCREENW, int SCREENH, float BGRATIO) {
        graphics = GRAPHICS;
        numberFont = FONT;
        left = LEFT;
        top = TOP;
        sourceX = 0;
        sourceY = 0;
        sourceWidth = FONT.getWidth();
        sourceHeight = FONT.getHeight();
        screenWidth = SCREENW;
        screenHeight = SCREENH;
        bgRatio = BGRATIO;
        SetBottomRight();
        ConvertToScreenCoord();
        charW = (int) Math.ceil((float) FONT.getWidth() / 11.0f); //11 characters "0123456789."
        numberLength = 1;
        fontSize = 1;
    }

    @Override
    public void Draw() {
        Draw("000", 1);
    }

    // Draw the number onto the screen
    public void Draw(String number, int size) {
        fontSize = size;        //
        SetBottomRight();       // Update dimensions
        ConvertToScreenCoord(); //

        int len = number.length();
        numberLength = len;

        int numXPos = screenLeft;
        for (int i = 0; i < len; i++) {
            char character = number.charAt(i);

            if (character == ' ')    // If there is a space
            {
                numXPos += charW;       // charW is gotten in the constructor
                continue;
            }
            int newSrcX;
            int srcWidth;
            if (character == '.')    // If there is a period
            {
                newSrcX = charW * 10;      // x10 because we need the length of the 10 previous characters
                srcWidth = charW / 2;      // '.' is half the width of the number characters
            } else                    // If there is a number
            {
                newSrcX = (character - '0') * charW;
                srcWidth = charW;
            }

            graphics.drawNumber(numberFont, numXPos, screenTop, newSrcX, 0, srcWidth, sourceHeight, fontSize);

            numXPos += srcWidth * fontSize;
        }
    }

    // Set the right and bottom bounds of the text in screen percentage units
    @Override
    protected void SetBottomRight() {
        right = (left + ((int) ((float) (100 * sourceWidth / screenWidth) * bgRatio) * numberLength)) * fontSize;
        bottom = (top + (int) ((float) (100 * sourceHeight / screenHeight) * bgRatio)) * fontSize;
    }
}
