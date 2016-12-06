package com.robomigos.squadgames.robomigos;

import com.framework.Graphics;
import com.framework.Pixmap;

/**
 * Created by Carlo Albino on 2016-11-30.
 */

public class AnimatedPixmap extends UIElement{

    private Pixmap spriteSheet;
    private int fps;
    private int frameWidth;
    private int frameHeight;

    private int currentFrame;
    private float currentTime;
    private int numOfFrames;

    public AnimatedPixmap(Graphics g, Pixmap sprites, int l, int t, int FPS, int numFrames, int frameW, int frameH, int screenW, int screenH, float ratio)
    {
        graphics = g;
        spriteSheet = sprites;
        left = l;
        top = t;
        fps = FPS;
        frameWidth = frameW;
        frameHeight = frameH;
        sourceX = 0;
        sourceY = 0;
        sourceWidth = sprites.getWidth();
        sourceHeight = sprites.getHeight();
        screenWidth = screenW;
        screenHeight = screenH;
        bgRatio = ratio;
        SetBottomRight();
        ConvertToScreenCoord();

        currentFrame = 0;
        currentTime = 0.0f;
        numOfFrames = numFrames;
    }

    @Override
    public void Draw() {
        System.out.println("Wrong Draw method called on the Animated Pixmap");
    }

    public void Draw(float deltaTime)
    {
        CountFrames(deltaTime);
        int curFrameX = 0;
        int curFrameY = 0;
        int numOfFramesX = sourceWidth / frameWidth;
        int numOfFramesY = sourceHeight / frameHeight;

        // Get the coordinates for the current frame of the animation.
        for(int iteratorX = 0; iteratorX < numOfFramesX; iteratorX++)
        {
            curFrameX = frameWidth * iteratorX;
            for(int iteratorY = 0; iteratorY < numOfFramesY; iteratorY++)
            {
                if((iteratorX * numOfFramesX + iteratorY) == currentFrame)
                {
                    curFrameY = frameHeight * iteratorY;
                    break;
                }
            }
        }
        // Draw the new frame.
        graphics.drawPixmap(spriteSheet, left, top, curFrameX, curFrameY, frameWidth, frameHeight, screenWidth, screenHeight, bgRatio);
    }

    // Check to see if a frame has passed and at which frame the animation is currently on.
    private void CountFrames(float deltaTime)
    {
        currentTime += deltaTime;

        if(currentTime > 1.0f/(float)fps)
        {
            currentFrame++;
            currentTime = 0;
            if(currentFrame >= numOfFrames)
            {
                currentFrame = 0;
            }
        }
    }
}
