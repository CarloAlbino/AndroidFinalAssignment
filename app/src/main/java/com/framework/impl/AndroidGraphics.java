package com.framework.impl;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;

import com.framework.Graphics;
import com.framework.Pixmap;
import com.robomigos.squadgames.robomigos.Vector2i;

public class AndroidGraphics implements Graphics {
    AssetManager assets;
    Bitmap frameBuffer; // represents our artificial framebuffer
    Canvas canvas;		// use to draw to the artificial framebuffer
    Paint paint;		// needed for drawing
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();

    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer) {
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
    }

    @Override
    public Pixmap newPixmap(String fileName, PixmapFormat format) {
        InputStream in = null;
        Bitmap bitmap = null;
        try {
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            if (bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
        } catch (IOException e) {
            throw new RuntimeException("Couldn't load bitmap from asset '" + fileName + "'");
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
        }

        return new AndroidPixmap(bitmap, format);
    }

    @Override
    public void clear(int color) {
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8, (color & 0xff));
    }

    @Override
    public void drawPixel(int x, int y, int color) {
        paint.setColor(color);
        canvas.drawPoint(x, y, paint);
    }

    @Override
    public void drawLine(int x, int y, int x2, int y2, int color) {
        paint.setColor(color);
        canvas.drawLine(x, y, x2, y2, paint);
    }

    @Override
    public void drawRect(int x, int y, int width, int height, int color) {
        paint.setColor(color);
        paint.setStyle(Style.FILL);
        canvas.drawRect(x, y, x + width - 1, y + width - 1, paint);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX  + srcWidth - 1;
        srcRect.bottom = srcY + srcHeight - 1;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth - 1;
        dstRect.bottom = y + srcHeight - 1;

        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, srcRect, dstRect, null);
    }


    // Modified by Carlo - 2016
    // Used to display an image to scale with the screen and with a preserved aspect ratio
    // pixmap is the bitmap, image to display
    // left and top are the positions of the bitmap on the screen
    // srcX and srcY are the start position of what to draw in the bitmap
    // srcWidth and srcHeight are the dimensions of the bitmap
    // screenW and screenH are the dimensions of the screen
    // bgRatio is the ratio of the background image size to the screen size
    @Override
    public void drawPixmap(Pixmap pixmap, int left, int top, int srcX, int srcY, int srcWidth, int srcHeight, int screenW, int screenH, float bgRatio) {
        // Choosing the area of the bitmap to draw
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX  + srcWidth - 1;
        srcRect.bottom = srcY + srcHeight - 1;

        // Set the area of the screen that the bitmap will be displayed to
        Vector2i topLeft = ConvertPrctToScreenCoord(left, top, screenW, screenH);
        Vector2i botRight = ConvertPrctToScreenCoord(left + (int)((float)(100 * srcWidth/screenW) * bgRatio), top + (int)((float)(100 * srcHeight/screenH) * bgRatio), screenW, screenH);
        dstRect.left = topLeft.X;
        dstRect.top = topLeft.Y;
        dstRect.right = botRight.X;
        dstRect.bottom = botRight.Y;

        // Draw the image
        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, srcRect, dstRect, null);
    }
    // Modified by Carlo - 2016
    // Used to display an image stretched to the given dimension
    // pixmap is the bitmap, image to display
    // left, top, right, bottom are the boundaries of the bitmap on the screen
    // srcX and srcY are the start position of what to draw in the bitmap
    // srcWidth and srcHeight are the dimensions of the bitmap
    // screenW and screenH are the dimensions of the screen
    @Override
    public void drawPixmap(Pixmap pixmap, int left, int top, int right, int bottom, int srcX, int srcY, int srcWidth, int srcHeight, int screenW, int screenH) {
        // Choosing the area of the bitmap to draw
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX  + srcWidth - 1;
        srcRect.bottom = srcY + srcHeight - 1;

        // Set the area of the screen that the bitmap will be displayed to
        Vector2i topLeft = ConvertPrctToScreenCoord(left, top, screenW, screenH);
        Vector2i botRight = ConvertPrctToScreenCoord(right, bottom, screenW, screenH);
        dstRect.left = topLeft.X;
        dstRect.top = topLeft.Y;
        dstRect.right = botRight.X;
        dstRect.bottom = botRight.Y;

        // Draw the image
        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, srcRect, dstRect, null);
    }
    
    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y) {
        canvas.drawBitmap(((AndroidPixmap)pixmap).bitmap, x, y, null);
    }

    @Override
    public void drawNumber(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight, int multiplier) {
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX  + srcWidth - 1;
        srcRect.bottom = srcY + srcHeight - 1;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + ((srcWidth - 1) * multiplier);
        dstRect.bottom = y + ((srcHeight - 1) * multiplier);

        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, srcRect, dstRect, null);
    }

    @Override
    public int getWidth() {
        return frameBuffer.getWidth();
    }

    @Override
    public int getHeight() {
        return frameBuffer.getHeight();
    }

    private Vector2i ConvertPrctToScreenCoord(int x, int y, int screenW, int screenH)
    {
        Vector2i screenCoords = new Vector2i();

        screenCoords.X = (int)((x * 0.01f) * screenW);
        screenCoords.Y = (int)((y * 0.01f) * screenH);

        return  screenCoords;
    }
}