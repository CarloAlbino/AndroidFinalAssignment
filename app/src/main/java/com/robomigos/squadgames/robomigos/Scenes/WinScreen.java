package com.robomigos.squadgames.robomigos.Scenes;

import com.framework.Game;
import com.framework.Graphics;
import com.framework.Input;
import com.framework.Pixmap;
import com.framework.Screen;
import com.robomigos.squadgames.robomigos.Button;
import com.robomigos.squadgames.robomigos.NumberDisplay;
import com.robomigos.squadgames.robomigos.Scenes.TitleScreen;

import java.util.List;

/**
 * Created by Carlo Albino on 2016-11-14.
 */

public class WinScreen extends Screen {
    private static Pixmap background;
    private static Pixmap backButtonNormal;
    private static Pixmap backButtonPressed;
    private static Pixmap bonusExpImage;
    private static Pixmap moneyGainedImage;
    private static Pixmap numbers;
    private static Pixmap plusSymbol;
    private static Pixmap moneySymbol;

    private Button backButton;
    private NumberDisplay bonusExpDisplay;
    private NumberDisplay moneyGainedDisplay;

    private int bonusExp;
    private int moneyGained;

    public WinScreen(Game game, int gameDifficulty)
    {
        super(game);
        Graphics g = game.getGraphics();

        // Load the bitmaps
        background = g.newPixmap("WinScreenBackground.png", Graphics.PixmapFormat.RGB565);
        backButtonNormal = g.newPixmap("BackButtonUnPressed.png", Graphics.PixmapFormat.ARGB4444);
        backButtonPressed = g.newPixmap("BackButtonPressed.png", Graphics.PixmapFormat.ARGB4444);
        bonusExpImage = g.newPixmap("WinScene/BonusExp.png", Graphics.PixmapFormat.ARGB4444);
        moneyGainedImage = g.newPixmap("WinScene/MoneyGained.png", Graphics.PixmapFormat.ARGB4444);
        numbers = g.newPixmap("numbersBlack.png", Graphics.PixmapFormat.ARGB4444);
        plusSymbol = g.newPixmap("WinScene/PlusSign.png", Graphics.PixmapFormat.ARGB4444);
        moneySymbol = g.newPixmap("RoboCurrency.png", Graphics.PixmapFormat.ARGB4444);


        // Get the background to screen ratio
        bgToScreenRatio = (float)g.getHeight() / (float)background.getHeight();

        // Create buttons
        backButton = new Button(g, backButtonNormal, backButtonPressed, 0, 100 - ((int)((float) backButtonNormal.getHeight()/(float)g.getHeight() * 100)), 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);

        // Create number displays
        bonusExpDisplay = new NumberDisplay(g, numbers, 35, 60, g.getWidth(), g.getHeight(), bgToScreenRatio);
        moneyGainedDisplay = new NumberDisplay(g, numbers, 35, 80, g.getWidth(), g.getHeight(), bgToScreenRatio);

        switch(gameDifficulty)
        {
            case 1:
                bonusExp = 400;
                moneyGained = 100;
                break;
            case 2:
                bonusExp = 600;
                moneyGained = 250;
                break;
            case 3:
                bonusExp = 800;
                moneyGained = 450;
                break;
            case 4:
                bonusExp = 1500;
                moneyGained = 800;
                break;
            default:
                System.out.println("Wrong level passed into win screen.");
                bonusExp = 400;
                moneyGained = 100;
                break;
        }
        //Unlock the next level
        if(gameDifficulty == game.getData().GetUnlockedLevel())
        {
            if(gameDifficulty < 5) {
                game.getData().SetUnlockLevel(gameDifficulty + 1);
            }
        }


        // Add the EXP and Money to the player data
        game.getData().SetPetExperience(bonusExp);
        game.getData().AddMoney(moneyGained);
        game.getData().AddHappiness(+0.2f);
        // Save the game
        game.getData().SaveGame(game.getFileIO());
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
                if(backButton.IsInBounds(event))
                {
                    backButton.Pressed(true);
                }
            }

            if(event.type == Input.TouchEvent.TOUCH_UP)
            {
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

        bonusExpDisplay.Draw(Integer.toString(bonusExp), 3);
        moneyGainedDisplay.Draw(Integer.toString(moneyGained), 3);

        g.drawPixmap(bonusExpImage, 20, 55, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(moneyGainedImage, 15, 75, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(plusSymbol, 20, 62, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(plusSymbol, 20, 82, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(moneySymbol, 67, 82, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
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
