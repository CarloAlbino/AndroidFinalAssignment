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

public class ChooseAPetScreen extends Screen {


    private static Pixmap background;
    private static Pixmap titleImage;
    private static Pixmap char1ButtonImage;
    private static Pixmap char2ButtonImage;
    private static Pixmap char3ButtonImage;
    private static Pixmap backButtonNormal;
    private static Pixmap backButtonPressed;

    private Button char1Button;
    private Button char2Button;
    private Button char3Button;
    private Button backButton;


    public ChooseAPetScreen(Game game)
    {
        super(game);
        Graphics g = game.getGraphics();

        // Load the bitmaps
        background = g.newPixmap("MenuBackground.png", Graphics.PixmapFormat.RGB565);   // RGB565 non - transparent
        titleImage = g.newPixmap("ChooseRobotTitle.png", Graphics.PixmapFormat.ARGB4444);        // ARGB4444 transparent
        char1ButtonImage = g.newPixmap("CyboButton.png", Graphics.PixmapFormat.ARGB4444);
        char2ButtonImage = g.newPixmap("RoboButton.png", Graphics.PixmapFormat.ARGB4444);
        char3ButtonImage = g.newPixmap("DroiButton.png", Graphics.PixmapFormat.ARGB4444);
        backButtonNormal = g.newPixmap("BackButtonUnPressed.png", Graphics.PixmapFormat.ARGB4444);
        backButtonPressed = g.newPixmap("BackButtonPressed.png", Graphics.PixmapFormat.ARGB4444);

        // Get the background to screen ratio
        bgToScreenRatio = (float)g.getHeight() / (float)background.getHeight(); // Have this line in each screen constructor after background is set

        // Create buttons
        char1Button = new Button(g, char1ButtonImage, char1ButtonImage, 15, 33, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        char2Button = new Button(g, char2ButtonImage, char2ButtonImage, 15, char1Button.Bottom() + 3, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        char3Button = new Button(g, char3ButtonImage, char3ButtonImage, 15, char2Button.Bottom() + 3, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        backButton = new Button(g, backButtonNormal, backButtonPressed, 0, 100 - ((int)((float) backButtonNormal.getHeight()/(float)g.getHeight() * 100)), 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
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
                if(backButton.IsInBounds(event))
                {
                    game.setScreen(new TitleScreen(game));
                    return;
                }
                if(char1Button.IsInBounds(event))
                {
                    game.getData().SetPetChoice(0);
                    game.getData().SaveGame(game.getFileIO());
                    game.setScreen(new HomeScreen(game));
                    return;
                }
                if(char2Button.IsInBounds(event))
                {
                    game.getData().SetPetChoice(1);
                    game.getData().SaveGame(game.getFileIO());
                    game.setScreen(new HomeScreen(game));
                    return;
                }
                if(char3Button.IsInBounds(event))
                {
                    game.getData().SetPetChoice(2);
                    game.getData().SaveGame(game.getFileIO());
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
        g.drawPixmap(titleImage, 15, 7, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        char1Button.Draw();
        char2Button.Draw();
        char3Button.Draw();
        backButton.Draw();
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
