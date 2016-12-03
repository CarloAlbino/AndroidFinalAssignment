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

public class ChooseBattleScreen extends Screen {
    private static Pixmap background;
    private static Pixmap backButtonNormal;
    private static Pixmap backButtonPressed;

    private static Pixmap easyButtonImage;
    private static Pixmap mediumButtonImage;
    private static Pixmap hardButtonImage;
    private static Pixmap expertButtonImage;
    private static Pixmap releaseButtonImage;

    private Button backButton;

    private Button easyButton;
    private Button mediumButton;
    private Button hardButton;
    private Button expertButton;
    private Button releaseButton;

    public ChooseBattleScreen(Game game)
    {
        super(game);
        Graphics g = game.getGraphics();

        // Load the bitmaps
        background = g.newPixmap("level_selection3.png", Graphics.PixmapFormat.RGB565);
        backButtonNormal = g.newPixmap("BackButtonUnPressed.png", Graphics.PixmapFormat.ARGB4444);
        backButtonPressed = g.newPixmap("BackButtonPressed.png", Graphics.PixmapFormat.ARGB4444);

        easyButtonImage = g.newPixmap("easy_button.png", Graphics.PixmapFormat.ARGB4444);
        mediumButtonImage = g.newPixmap("medium_button.png", Graphics.PixmapFormat.ARGB4444);
        hardButtonImage = g.newPixmap("hard_button.png", Graphics.PixmapFormat.ARGB4444);
        expertButtonImage = g.newPixmap("expert_button.png", Graphics.PixmapFormat.ARGB4444);
        releaseButtonImage = g.newPixmap("release_button.png", Graphics.PixmapFormat.ARGB4444);

        // Get the background to screen ratio
        bgToScreenRatio = (float)g.getHeight() / (float)background.getHeight();

        // Create buttons
        backButton = new Button(g, backButtonNormal, backButtonPressed, 0, 100 - ((int)((float) backButtonNormal.getHeight()/(float)g.getHeight() * 100)), 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);

        easyButton = new Button(g, easyButtonImage, easyButtonImage, 35, 15, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        mediumButton = new Button(g, mediumButtonImage, mediumButtonImage, 35, easyButton.Bottom() + 2, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        hardButton = new Button(g, hardButtonImage, hardButtonImage, 35, mediumButton.Bottom() + 2, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        expertButton = new Button(g, expertButtonImage, expertButtonImage, 35, hardButton.Bottom() + 2, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        releaseButton = new Button(g, releaseButtonImage, releaseButtonImage, 35, expertButton.Bottom() + 2, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
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
                if (backButton.IsInBounds(event))
                {
                    backButton.Pressed(true);
                }
            }

            if(event.type == Input.TouchEvent.TOUCH_UP)
            {
                if(releaseButton.IsInBounds(event))
                {
                    game.setScreen(new ReleaseScreen(game));
                    return;
                }

                if(easyButton.IsInBounds(event))
                {
                    game.setScreen(new BattleScreen(game, 1));
                    return;
                }

                if(mediumButton.IsInBounds(event))
                {
                    game.setScreen(new BattleScreen(game, 2));
                    return;
                }

                if(hardButton.IsInBounds(event))
                {
                    game.setScreen(new BattleScreen(game, 3));
                    return;
                }

                if(expertButton.IsInBounds(event))
                {
                    game.setScreen(new BattleScreen(game, 4));
                    return;
                }


                if(backButton.IsInBounds(event))
                {
                    backButton.Pressed(false);
                    game.setScreen(new HomeScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(background, 0, 0, 100, 100, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight());

        backButton.Draw();
        easyButton.Draw();
        mediumButton.Draw();
        hardButton.Draw();
        expertButton.Draw();
        releaseButton.Draw();
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
