package com.robomigos.squadgames.robomigos;

import com.framework.Graphics;
import com.framework.Pixmap;

/**
 * Created by Carlo Albino on 2016-11-30.
 */

public class DisplayBar extends UIElement {

    private Pixmap barBackground;
    private Pixmap barForeground;

    public float fillAmount;

    public DisplayBar(Graphics g, Pixmap background, Pixmap forground, int l, int t, int srcX, int srcY, int screenW, int screenH, float ratio)
    {
        graphics = g;
        barBackground = background;
        barForeground = forground;
        left = l;
        top = t;
        sourceX = srcX;
        sourceY = srcY;
        sourceWidth = background.getWidth();
        sourceHeight = background.getHeight();
        screenWidth = screenW;
        screenHeight = screenH;
        bgRatio = ratio;
        SetBottomRight();
        ConvertToScreenCoord();

        fillAmount = 1.0f;
    }

    @Override
    public void Draw() {
        DrawBackground();;
        DrawFill();
    }

    private void DrawBackground()
    {
        graphics.drawPixmap(barBackground, left, top, sourceX, sourceY, sourceWidth, sourceHeight, screenWidth, screenHeight, bgRatio);
    }

    private void DrawFill()
    {
        int newSourceW;
        if(fillAmount >= 1.0f)
        {
            fillAmount = 1.0f;
        }else if(fillAmount <= 0)
        {
            fillAmount = 0;
        }
        newSourceW = (int)((float)sourceWidth * fillAmount);

        graphics.drawPixmap(barForeground, left, top, sourceX, sourceY, newSourceW, sourceHeight, screenWidth, screenHeight, bgRatio);
    }
}

