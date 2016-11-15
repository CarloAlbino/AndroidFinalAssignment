package com.robomigos.squadgames.robomigos.TutorialClasses;

import com.framework.Game;
import com.framework.Graphics;
import com.framework.Input;
import com.framework.Pixmap;
import com.framework.Screen;
import com.robomigos.squadgames.robomigos.Button;
import com.robomigos.squadgames.robomigos.TutorialClasses.MainMenuScreen;

import java.util.List;
import java.util.Random;

/**
 * Created by Carlo Albino on 2016-11-06.
 */

public class GameScreen extends Screen {

    private static final float UPDATE_BLOB_TIME = 6.0f;

    private static Pixmap background;
    private static Pixmap blobImage;
    private static Pixmap numbers;

    private int blobXPos;
    private int blobYPos;

    private Button blob;

    private int oldScore;
    private String score = "0";

    private float timePassed;

    private Random random = new Random();
    private Graphics g = null;

    public GameScreen(Game game)
    {
        super(game);

        g = game.getGraphics();
        background = g.newPixmap("background.png", Graphics.PixmapFormat.RGB565);

        bgToScreenRatio = g.getHeight() / background.getHeight();

        blobImage = g.newPixmap("stain1.png", Graphics.PixmapFormat.ARGB4444);
        numbers = g.newPixmap("numbers.png", Graphics.PixmapFormat.ARGB4444);

        blobXPos = 0;
        blobXPos = 0;

        blob = new Button(g, blobImage, blobImage, blobXPos, blobYPos, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(background, 0, 0, 100, 100, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight());
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++)
        {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_DOWN)
            {
                if(blob.IsInBounds(event))
                {
                    oldScore++;
                    score = "" + oldScore;
                    timePassed = 7.0f;
                }
                else
                {
                    game.setScreen(new MainMenuScreen(game));
                    return;
                }
            }
        }

        timePassed += deltaTime;
        if(timePassed > UPDATE_BLOB_TIME)
        {
            blobXPos = random.nextInt(g.getWidth() - blob.GetWidth());
            blobYPos = random.nextInt(g.getHeight() - blob.GetHeight());

            blobXPos = (int)(100.0f * ((float)blobXPos / (float)g.getWidth()));
            blobYPos = (int)(100.0f * ((float)blobYPos / (float)g.getHeight()));

            timePassed = 0;
        }
    }

    @Override
    public void present(float deltaTime) {
        g.drawPixmap(background, 0, 0, 100, 100, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight());

        blob.UpdatePosition(blobXPos, blobYPos);
        blob.Draw();

        drawText(g, score, g.getWidth() / 2 - score.length() * 20 / 2, g.getHeight() - 42);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    public void drawText(Graphics g, String line, int x, int y)
    {
        int len = line.length();
        for(int i = 0; i < len; i++)
        {
            char character = line.charAt(i);

            if(character == ' ')
            {
                x += 20;
                continue;
            }
            int srcX;
            int srcWidth;
            if(character == '.')
            {
                srcX = 200;
                srcWidth = 10;
            }
            else
            {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }

            g.drawPixmap(numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }
}
