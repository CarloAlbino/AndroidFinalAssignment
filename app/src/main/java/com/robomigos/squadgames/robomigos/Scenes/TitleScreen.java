package com.robomigos.squadgames.robomigos.Scenes;

import com.framework.Input;
import com.framework.Pixmap;
import com.framework.Screen;
import com.framework.Game;
import com.framework.Graphics;
import com.robomigos.squadgames.robomigos.Button;
import com.robomigos.squadgames.robomigos.Scenes.CreditsScreen;
import com.robomigos.squadgames.robomigos.Scenes.InstructionsScreen;
import com.robomigos.squadgames.robomigos.Scenes.ReleaseScreen;

import java.util.List;

/**
 * Created by Carlo Albino on 2016-11-13.
 */

public class TitleScreen extends Screen {

    private static Pixmap background;
    private static Pixmap logo;
    private static Pixmap startButtonImage;
    private static Pixmap howToPlayButtonImage;
    private static Pixmap creditsButtonImage;

    private Button startButton;
    private Button howToButton;
    private Button creditsButton;


    public TitleScreen(Game game)
    {
        super(game);
        Graphics g = game.getGraphics();

        // Load the bitmaps
        background = g.newPixmap("MenuBackground.png", Graphics.PixmapFormat.RGB565);   // RGB565 non - transparent
        logo = g.newPixmap("RobomigosLogo.png", Graphics.PixmapFormat.ARGB4444);        // ARGB4444 transparent
        startButtonImage = g.newPixmap("StartButton.png", Graphics.PixmapFormat.ARGB4444);
        howToPlayButtonImage = g.newPixmap("HowToPlayButton.png", Graphics.PixmapFormat.ARGB4444);
        creditsButtonImage = g.newPixmap("CreditsButton.png", Graphics.PixmapFormat.ARGB4444);

        // Get the background to screen ratio
        bgToScreenRatio = (float)g.getHeight() / (float)background.getHeight(); // Have this line in each screen constructor after background is set

        // Create buttons
        startButton = new Button(g, startButtonImage, startButtonImage, 33, 50, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        howToButton = new Button(g, howToPlayButtonImage, howToPlayButtonImage, 33, 60, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        creditsButton = new Button(g, creditsButtonImage, creditsButtonImage, 33, 70, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
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
                if(startButton.IsInBounds(event))
                {
                    game.getData().LoadSave(game.getFileIO()); //  Loading the save file
                    if(game.getData().GetPetChoice() >= 0)   // Pet already been chosen
                    {
                        game.setScreen(new HomeScreen(game));
                    }
                    else                                    // Pet not yet chosen
                    {
                        game.setScreen(new ChooseAPetScreen(game));
                    }
                    return;
                }

                if(howToButton.IsInBounds(event))
                {
                    // Do stuff
                    game.setScreen(new InstructionsScreen(game));
                    return;
                }

                if(creditsButton.IsInBounds(event))
                {
                    game.setScreen(new CreditsScreen(game));
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(background, 0, 0, 100, 100, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight());
        g.drawPixmap(logo, 0, 7, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        startButton.Draw();
        howToButton.Draw();
        creditsButton.Draw();
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
