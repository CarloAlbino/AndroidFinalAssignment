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

    private float currentFrame;
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

        // Set the coordinates in the sprite sheet for the current frame.
        for(int iteratorX = 0; iteratorX < numOfFramesX; iteratorX++)
        {
            for(int iteratorY = 0; iteratorY < numOfFramesY; iteratorY++)
            {
                if(iteratorX * (iteratorY + 1) == currentFrame)
                {
                    curFrameX = frameWidth * iteratorX;
                    curFrameY = frameHeight * iteratorY;
                    break;
                }
            }
        }

        graphics.drawPixmap(spriteSheet, left, top, curFrameX, curFrameY, frameWidth, frameHeight, screenWidth, screenHeight, bgRatio);
    }

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

        System.out.println("Current Time: " + currentTime + ". Current Frame: " + currentFrame + ". 1.0f/fps: " + 1.0f/(float)fps + ".");
    }
}
