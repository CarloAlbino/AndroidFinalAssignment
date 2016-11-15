package com.robomigos.squadgames.robomigos.TutorialClasses;

import com.framework.Input;
import com.framework.Pixmap;
import com.framework.Screen;
import com.framework.Game;
import com.framework.Graphics;
import com.robomigos.squadgames.robomigos.Button;
import com.robomigos.squadgames.robomigos.Vector2i;

import java.util.List;

/**
 * Created by Carlo Albino on 2016-11-06.
 */

public class MainMenuScreen extends Screen {
    private static Pixmap background;
    private static Pixmap playButtonImage;

    private Button playButton;
    private Vector2i playButtonPos;

    public MainMenuScreen(Game game)
    {
        super(game);
        Graphics g = game.getGraphics();

        // Load the bitmaps
        background = g.newPixmap("background.png", Graphics.PixmapFormat.RGB565);
        playButtonImage = g.newPixmap("ready.png", Graphics.PixmapFormat.ARGB4444);

        // Get the background to screen ratio
        bgToScreenRatio = (float)g.getHeight() / (float)background.getHeight();

        // Create a button
        playButton = new Button(g, playButtonImage, playButtonImage, 10, 25, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++)
        {
            Input.TouchEvent event = touchEvents.get(i);
            if(event.type == Input.TouchEvent.TOUCH_UP)
            {
                if(playButton.IsInBounds(event))
                {
                    game.setScreen(new GameScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(background, 0, 0, 100, 100, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight());
        playButton.Draw();
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
