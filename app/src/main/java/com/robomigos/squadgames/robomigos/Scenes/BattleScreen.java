package com.robomigos.squadgames.robomigos.Scenes;

import com.framework.Game;
import com.framework.Graphics;
import com.framework.Input;
import com.framework.Pixmap;
import com.framework.Screen;
import com.robomigos.squadgames.robomigos.Button;

import java.util.List;

/**
 * Created by Carlo Albino on 2016-11-14.
 */

public class BattleScreen extends Screen {

    public static Pixmap buttonBackgroundImage;
    public static Pixmap fireButtonImage;
    public static Pixmap leafButtonImage;
    public static Pixmap waterButtonImage;
    public static Pixmap runButtonImage;

    public Button fireButton;
    public Button leafButton;
    public Button waterButton;
    public Button runButton;

    public BattleScreen(Game game)
    {
        super(game);
        Graphics g = game.getGraphics();

        // Load the bitmaps
        buttonBackgroundImage = g.newPixmap("BattleBackground.png", Graphics.PixmapFormat.ARGB4444);
        fireButtonImage = g.newPixmap("FireButton.png", Graphics.PixmapFormat.ARGB4444);
        leafButtonImage = g.newPixmap("LeafButton.png", Graphics.PixmapFormat.ARGB4444);
        waterButtonImage = g.newPixmap("WaterButton.png", Graphics.PixmapFormat.ARGB4444);
        runButtonImage = g.newPixmap("RunButton.png", Graphics.PixmapFormat.ARGB4444);

        // Get the background to screen ratio
        bgToScreenRatio = (float)g.getHeight() / (float)buttonBackgroundImage.getHeight();

        // Create buttons
        fireButton = new Button(g, fireButtonImage, fireButtonImage, 52, 60, fireButtonImage.getWidth(), fireButtonImage.getHeight(),g.getWidth(), g.getWidth(), bgToScreenRatio);
        waterButton = new Button(g, waterButtonImage, waterButtonImage, 2, 60, waterButtonImage.getWidth(), waterButtonImage.getHeight(),g.getWidth(), g.getWidth(), bgToScreenRatio);
        leafButton = new Button(g, leafButtonImage, leafButtonImage, 52, waterButton.Bottom() +10, leafButtonImage.getWidth(), leafButtonImage.getHeight(),g.getWidth(), g.getWidth(), bgToScreenRatio);
        runButton = new Button(g, runButtonImage, runButtonImage, 52, fireButton.Bottom() + 10, runButtonImage.getWidth(), runButtonImage.getHeight(),g.getWidth(), g.getWidth(), bgToScreenRatio);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++)
        {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_DOWN)
            {

            }

            if(event.type == Input.TouchEvent.TOUCH_UP)
            {
                if(runButton.IsInBounds(event))
                {
                    game.setScreen(new HomeScreen(game));
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(buttonBackgroundImage, 0, 0, 100, 100, 0, 0, buttonBackgroundImage.getWidth(), buttonBackgroundImage.getHeight(), g.getWidth(), g.getHeight());

        fireButton.Draw();
        leafButton.Draw();
        waterButton.Draw();
        runButton.Draw();
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
}
