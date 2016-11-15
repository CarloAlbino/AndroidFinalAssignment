package com.robomigos.squadgames.robomigos.Scenes;

import com.framework.Input;
import com.framework.Pixmap;
import com.framework.Screen;
import com.framework.Game;
import com.framework.Graphics;
import com.robomigos.squadgames.robomigos.Button;

import java.util.List;

/**
 * Created by Carlo Albino on 2016-11-14.
 */

public class ReleaseScreen extends Screen {

    private static Pixmap background;
    private static Pixmap yesButtonImage;
    private static Pixmap noButtonImage;

    private Button yesButton;
    private Button noButton;

    public ReleaseScreen(Game game)
    {
        super(game);
        Graphics g = game.getGraphics();

        // Load the bitmaps
        background = g.newPixmap("ReleaseScreenBackground.png", Graphics.PixmapFormat.RGB565);   // RGB565 non - transparent
        yesButtonImage = g.newPixmap("YesButton.png", Graphics.PixmapFormat.ARGB4444);        // ARGB4444 transparent
        noButtonImage = g.newPixmap("NoButton.png", Graphics.PixmapFormat.ARGB4444);

        // Get the background to screen ratio
        bgToScreenRatio = (float)g.getHeight() / (float)background.getHeight(); // Have this line in each screen constructor after background is set

        // Create buttons
        yesButton = new Button(g, yesButtonImage, yesButtonImage, 30, 50, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        noButton = new Button(g, noButtonImage, noButtonImage, 30, yesButton.Bottom() + 5, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);

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
                if(noButton.IsInBounds(event))
                {
                    // Do stuff
                    game.setScreen(new TitleScreen(game));
                    return;
                }

            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(background, 0, 0, 100, 100, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight());
        yesButton.Draw();
        noButton.Draw();
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
