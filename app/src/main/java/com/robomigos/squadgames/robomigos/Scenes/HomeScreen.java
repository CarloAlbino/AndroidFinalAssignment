package com.robomigos.squadgames.robomigos.Scenes;

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
// <- Josh did this :D
public class HomeScreen extends Screen {

    //Button Images
    private static Pixmap background;
    public static Pixmap buttonBackgroundImage;
    public static Pixmap battleButtonImage;
    public static Pixmap quitButtonImage;
    public static Pixmap shopButtonImage;
    public static Pixmap inventoryButtonImage;

    //Mood Images
    public static Pixmap angryFaceImage;
    public static Pixmap annoyedFaceImage;
    public static Pixmap happyFaceImage;


    //Bar images for Happy,Hunger and Health
    public static Pixmap backBarBorderImage;
    public static Pixmap happinessBarImage;
    public static Pixmap healthBarImage;
    public static Pixmap hungerBarImage;
    public static Pixmap dojoImage;

    //Level box, TopUI,background and BottomUI
    public static Pixmap levelBoxImage;
    public static Pixmap topUIImage;
    public static Pixmap bottomUIImage;
    public static Pixmap homeBackGroundImage;

    //Level number
    public static Pixmap font;
    public NumberDisplay levelDisplay;


    //Robot Images
    public static Pixmap cybordBird;
    public static Pixmap robotMan;
    public static Pixmap robotFrog;
    public static Pixmap  robotPoop;


//Buttons

    public Button battleButton;
    public Button shopButton;
    public Button inventoryButton;
    public Button quitButton;



    public HomeScreen(Game game)
    {
        super(game);
        Graphics g = game.getGraphics();

        // Load the bitmaps
        backBarBorderImage =   g.newPixmap("Healthbar_Border.png", Graphics.PixmapFormat.RGB565);

        //Bar Images
        happinessBarImage =   g.newPixmap("Happybar_Pink.png", Graphics.PixmapFormat.RGB565);
        healthBarImage =   g.newPixmap("Healthbar_Yellow.png", Graphics.PixmapFormat.RGB565);
        hungerBarImage =  g.newPixmap("Hungerbar_Orange.png", Graphics.PixmapFormat.RGB565);
        levelBoxImage = g.newPixmap("LevelBox.png", Graphics.PixmapFormat.RGB565);
        background =  g.newPixmap("HomeBackgroundUI.png", Graphics.PixmapFormat.RGB565);
        dojoImage = g.newPixmap("Dojo.png",Graphics.PixmapFormat.RGB565);


        //faces images
        angryFaceImage = g.newPixmap("AngryFace.png", Graphics.PixmapFormat.RGB565);
        happyFaceImage =  g.newPixmap("HappyFace.png", Graphics.PixmapFormat.RGB565);
        annoyedFaceImage =  g.newPixmap("AnnoyedFace.png", Graphics.PixmapFormat.RGB565);

        //Button Images
        battleButtonImage = g.newPixmap("Battle.png",Graphics.PixmapFormat.ARGB4444);
        quitButtonImage = g.newPixmap("Quit.png",Graphics.PixmapFormat.ARGB4444);
        shopButtonImage =g.newPixmap("Shop.png",Graphics.PixmapFormat.ARGB4444);
        inventoryButtonImage =g.newPixmap("Inventory.png",Graphics.PixmapFormat.ARGB4444);


        //Robot references to pixmap
        cybordBird = g.newPixmap("cyborgbird.png",Graphics.PixmapFormat.RGB565);
        robotMan = g.newPixmap("robotman.png",Graphics.PixmapFormat.RGB565);
        robotFrog = g.newPixmap("frog.png",Graphics.PixmapFormat.RGB565);
        robotPoop = g.newPixmap("poop.png",Graphics.PixmapFormat.RGB565);


        // Number font
        font = g.newPixmap("numbersBlack.png", Graphics.PixmapFormat.ARGB4444);

        // Get the background to screen ratio
        bgToScreenRatio = (float)g.getHeight() / (float)background.getHeight();

        // Create buttons
         battleButton = new Button(g, battleButtonImage, battleButtonImage, 15, 75, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
         quitButton = new Button(g, quitButtonImage, quitButtonImage, 15, 90, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        inventoryButton = new Button(g, inventoryButtonImage, inventoryButtonImage, 60, 90, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        shopButton = new Button(g, shopButtonImage, shopButtonImage, 60, 75, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);

        // Create number display
        levelDisplay = new NumberDisplay(g, font, 74, 12, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
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

            //QuitButton
            if(event.type == Input.TouchEvent.TOUCH_UP)
            {
                if(quitButton.IsInBounds(event))
                {
                    game.setScreen(new TitleScreen(game));
                    return;
                }
            }
            //Battle screen
            if(event.type == Input.TouchEvent.TOUCH_UP)
            {
                if(battleButton.IsInBounds(event))
                {
                    game.setScreen(new ChooseBattleScreen(game));
                    return;
                }
            }
            //Shop screen
            if(event.type == Input.TouchEvent.TOUCH_UP)
            {
                if(shopButton.IsInBounds(event))
                {
                    game.setScreen(new ShopScreen(game));
                    return;
                }
            }
            //Inventory screen
            if(event.type == Input.TouchEvent.TOUCH_UP)
            {
                if(inventoryButton.IsInBounds(event))
                {
                    game.setScreen(new InventoryScreen(game));
                    return;
                }
            }


        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        //Backgrounds
        g.drawPixmap(dojoImage, 0, 0, 100, 100, 0, 0, dojoImage.getWidth(), dojoImage.getHeight(), g.getWidth(), g.getHeight());
        g.drawPixmap(background, 0, 0, 100, 100, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight());

        //Health
        g.drawPixmap(backBarBorderImage, 12, 4, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(healthBarImage, 12, 4, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);

        //Happiness
        g.drawPixmap(backBarBorderImage, 12, 10, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(happinessBarImage, 12, 10, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);

        //Hunger
        g.drawPixmap(backBarBorderImage, 12, 16, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(hungerBarImage, 12, 16, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);

        //level box
        g.drawPixmap(levelBoxImage, 73, 11, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        levelDisplay.Draw(Integer.toString(game.getData().GetPetLevel()), 3);

        //Emotions
        g.drawPixmap(happyFaceImage, 60, 6, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);

        //Character
        g.drawPixmap(cybordBird, 40, 35, 0, 0, 256, 256, g.getWidth(), g.getHeight(), bgToScreenRatio);

        //Buttons being drawed
        battleButton.Draw();
        quitButton.Draw();
        inventoryButton.Draw();
        shopButton.Draw();

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
