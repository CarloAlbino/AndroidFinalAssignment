package com.robomigos.squadgames.robomigos.Scenes;

import android.widget.Switch;

import com.framework.Input;
import com.framework.Pixmap;
import com.framework.Screen;
import com.framework.Game;
import com.framework.Graphics;
import com.robomigos.squadgames.robomigos.Button;
import com.robomigos.squadgames.robomigos.NumberDisplay;

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

    private static Pixmap numbers;
    private NumberDisplay currentLevel;
    private NumberDisplay neededEXP;

    public ChooseBattleScreen(Game game)
    {
        super(game);
        Graphics g = game.getGraphics();

        // Load the bitmaps
        background = g.newPixmap("ChooseScene/ChooseDiffBackground.png", Graphics.PixmapFormat.RGB565);
        backButtonNormal = g.newPixmap("BackButtonUnPressed.png", Graphics.PixmapFormat.ARGB4444);
        backButtonPressed = g.newPixmap("BackButtonPressed.png", Graphics.PixmapFormat.ARGB4444);

        easyButtonImage = g.newPixmap("ChooseScene/EasyButton.png", Graphics.PixmapFormat.ARGB4444);
        mediumButtonImage = g.newPixmap("ChooseScene/MediumButton.png", Graphics.PixmapFormat.ARGB4444);
        hardButtonImage = g.newPixmap("ChooseScene/HardButton.png", Graphics.PixmapFormat.ARGB4444);
        expertButtonImage = g.newPixmap("ChooseScene/ExpertButton.png", Graphics.PixmapFormat.ARGB4444);
        releaseButtonImage = g.newPixmap("ChooseScene/ReleaseButton.png", Graphics.PixmapFormat.ARGB4444);

        // Get the background to screen ratio
        bgToScreenRatio = (float)g.getHeight() / (float)background.getHeight();

        // Create buttons
        backButton = new Button(g, backButtonNormal, backButtonPressed, 0, 100 - ((int)((float) backButtonNormal.getHeight()/(float)g.getHeight() * 100)), 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);

        easyButton = new Button(g, easyButtonImage, easyButtonImage, 3, 26, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        mediumButton = new Button(g, mediumButtonImage, mediumButtonImage, easyButton.Right() + 4, 26, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        hardButton = new Button(g, hardButtonImage, hardButtonImage, 3, easyButton.Bottom() + 4, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        expertButton = new Button(g, expertButtonImage, expertButtonImage, easyButton.Right() + 4, easyButton.Bottom() + 4, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        releaseButton = new Button(g, releaseButtonImage, releaseButtonImage, 2, expertButton.Bottom() + 5, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);

        // Numbers
        numbers = g.newPixmap("numbersBlack.png", Graphics.PixmapFormat.ARGB4444);
        currentLevel = new NumberDisplay(g, numbers, 20, 15, g.getWidth(), g.getHeight(), bgToScreenRatio);
        neededEXP = new NumberDisplay(g, numbers, 62, 15, g.getWidth(), g.getHeight(), bgToScreenRatio);

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
                switch(game.getData().GetUnlockedLevel()) {
                    case 5:
                        if (releaseButton.IsInBounds(event)) {
                            game.setScreen(new ReleaseScreen(game));
                            return;
                        }
                    case 4:
                        if (expertButton.IsInBounds(event)) {
                            game.setScreen(new BattleScreen(game, 4));
                            return;
                        }
                    case 3:
                        if (hardButton.IsInBounds(event)) {
                            game.setScreen(new BattleScreen(game, 3));
                            return;
                        }
                    case 2:
                        if (mediumButton.IsInBounds(event)) {
                            game.setScreen(new BattleScreen(game, 2));
                            return;
                        }
                    case 1:
                    default:
                        if (easyButton.IsInBounds(event)) {
                            game.setScreen(new BattleScreen(game, 1));
                            return;
                        }
                        break;
                }

                if (backButton.IsInBounds(event)) {
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

        switch(game.getData().GetUnlockedLevel())
        {
            case 5:
                releaseButton.Draw();
            case 4:
                expertButton.Draw();
            case 3:
                hardButton.Draw();
            case 2:
                mediumButton.Draw();
            case 1:
            default:
                easyButton.Draw();
                break;
        }

        // For testing
        /*releaseButton.Draw();
        expertButton.Draw();
        hardButton.Draw();
        mediumButton.Draw();
        easyButton.Draw();*/

        currentLevel.Draw(Integer.toString(game.getData().GetPetLevel()), 2);
        neededEXP.Draw(Integer.toString(game.getData().GetNeededExp()), 2);
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
