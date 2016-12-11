package com.robomigos.squadgames.robomigos.Scenes;

import android.media.Image;
import android.view.Display;

import com.framework.Input;
import com.framework.Pixmap;
import com.framework.Screen;
import com.framework.Game;
import com.framework.Graphics;
import com.robomigos.squadgames.robomigos.AnimatedPixmap;
import com.robomigos.squadgames.robomigos.Button;
import com.robomigos.squadgames.robomigos.DisplayBar;
import com.robomigos.squadgames.robomigos.NumberDisplay;

import java.util.List;
import java.util.Random;

/**
 * Created by Carlo Albino on 2016-11-14.
 */
// <- Josh did this :D
public class HomeScreen extends Screen {

    //Button Images
    private static Pixmap background;
    public static Pixmap battleButtonImage;
    public static Pixmap quitButtonImage;
    public static Pixmap shopButtonImage;
    public static Pixmap inventoryButtonImage;
    public static Pixmap poopImage;

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



    //Buttons
    public Button battleButton;
    public Button shopButton;
    public Button inventoryButton;
    public Button quitButton;
    public Button robotPoop;


    //Bars
    public DisplayBar healthBar;
    public DisplayBar fullnessBar;
    public DisplayBar happinessBar;

    // Animated Character
    public AnimatedPixmap[] character;
    public AnimatedPixmap poop;



    //Array List for poop(Random Spawning)
    public List<Button> m_poop;

    //Array list for Different states of emotions
    //public List<Image> m_Emotions;


    public HomeScreen(Game game)
    {
        super(game);
        Graphics g = game.getGraphics();

        // Load the bitmaps
        backBarBorderImage =   g.newPixmap("BackBorderUI.png", Graphics.PixmapFormat.RGB565);

        //Bar Images
        happinessBarImage =   g.newPixmap("HappinessBar.png", Graphics.PixmapFormat.RGB565);
        healthBarImage =   g.newPixmap("HealthBar.png", Graphics.PixmapFormat.RGB565);
        hungerBarImage =  g.newPixmap("HungerBar.png", Graphics.PixmapFormat.RGB565);
        levelBoxImage = g.newPixmap("LevelBox.png", Graphics.PixmapFormat.RGB565);
        background =  g.newPixmap("HomeScreen.png", Graphics.PixmapFormat.RGB565);



        //faces images
        happyFaceImage =  g.newPixmap("SmileyFace.png", Graphics.PixmapFormat.RGB565);
        //angryFaceImage = g.newPixmap("Angry.png", Graphics.PixmapFormat.RGB565);
        //annoyedFaceImage =  g.newPixmap("AnnoyedFace.png", Graphics.PixmapFormat.RGB565);

        //Button Images
        battleButtonImage = g.newPixmap("Battle.png",Graphics.PixmapFormat.ARGB4444);
        quitButtonImage = g.newPixmap("Quit.png",Graphics.PixmapFormat.ARGB4444);
        shopButtonImage =g.newPixmap("Shop.png",Graphics.PixmapFormat.ARGB4444);
        inventoryButtonImage =g.newPixmap("Inventory.png",Graphics.PixmapFormat.ARGB4444);
        poopImage = g.newPixmap("poop.png", Graphics.PixmapFormat.ARGB4444);


        //Robot references to pixmap
        cybordBird = g.newPixmap("cyborgbird.png",Graphics.PixmapFormat.RGB565);
        robotMan = g.newPixmap("robotman.png",Graphics.PixmapFormat.RGB565);
        robotFrog = g.newPixmap("frog.png",Graphics.PixmapFormat.RGB565);

        // Number font
        font = g.newPixmap("numbersBlack.png", Graphics.PixmapFormat.ARGB4444);

        // Get the background to screen ratio
        bgToScreenRatio = (float)g.getHeight() / (float)background.getHeight();


        // Create buttons
        battleButton = new Button(g, battleButtonImage, battleButtonImage, 5, 72, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        quitButton = new Button(g, quitButtonImage, quitButtonImage, 5, 88, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        inventoryButton = new Button(g, inventoryButtonImage, inventoryButtonImage, 58, 87, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        shopButton = new Button(g, shopButtonImage, shopButtonImage, 58, 72, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);

       //Poop Button
        robotPoop = new Button(g, poopImage, poopImage, 65, 45, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);

        // Display Bars
        healthBar = new DisplayBar(g, backBarBorderImage, healthBarImage, 12, 4, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        //fullnessBar = new DisplayBar(g, backBarBorderImage, healthBarImage, 20, 4, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        //happinessBar =new DisplayBar(g, backBarBorderImage, healthBarImage, 28, 4, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        levelDisplay = new NumberDisplay(g, font, 77, 14, g.getWidth(), g.getHeight(), bgToScreenRatio);

        // Draw animated characters (This is the Array list of the characters)
        character[2] = new AnimatedPixmap(g, cybordBird, 15, 47, 8, 2, 256, 256, g.getWidth(), g.getHeight(), bgToScreenRatio);
        character[0] = new AnimatedPixmap(g, robotFrog, 15, 47, 8, 2, 256, 256, g.getWidth(), g.getHeight(), bgToScreenRatio);
        character[1] = new AnimatedPixmap(g, robotMan, 15, 47, 8, 3, 256, 256, g.getWidth(), g.getHeight(), bgToScreenRatio);


        LoadNewBackgroundMusic("Audio/Music/Home.ogg");
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
                    if(game.getData().GetHP() > 0) {
                        game.setScreen(new ChooseBattleScreen(game));
                        return;
                    }
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


            //Poop pickup
            if(event.type == Input.TouchEvent.TOUCH_UP)
            {
                for(int j = 0; j < m_poop.size(); ++j)
               {

                   if(m_poop.get(j).IsInBounds(event))
                   {
                    //anything related to pressing
                       // input code here
                       m_poop.remove(i);
                       break;

                   }

               }

            }



        }



        //keep count of how much time has passed



        /*if()

        //random number code
        Random rand = new Random();
        int ranNum = rand.nextInt(80-25)+25;
        Button temp = new Button(game.getGraphics(), poopImage, poopImage, ranNum, 45, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio);

        //adding to the list
        m_poop.add(temp);
        */

        //The health Bar
       // healthBar.fillAmount = deltaTime; // For testing the health bar

        //Making the bars work by setting how much the bar is full

        //Fill Amount Bars Code
        healthBar.fillAmount = game.getData().GetHP()/game.getData().GetMaxHP();
        happinessBar.fillAmount = game.getData().GetHappiness();
        fullnessBar.fillAmount = game.getData().GetHunger();




    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        //Backgrounds
        //g.drawPixmap(dojoImage, 0, 0, 100, 100, 0, 0, dojoImage.getWidth(), dojoImage.getHeight(), g.getWidth(), g.getHeight());
        g.drawPixmap(background, 0, 0, 100, 100, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight());

        //Drawing the bars
        //g.drawPixmap(backBarBorderImage, 12, 4, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        //g.drawPixmap(healthBarImage, 12, 4, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        healthBar.Draw();
        happinessBar.Draw();
        fullnessBar.Draw();



        //Happiness
       // g.drawPixmap(backBarBorderImage, 12, 10, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        //g.drawPixmap(happinessBarImage, 12, 10, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);

        //Hunger
        //g.drawPixmap(backBarBorderImage, 12, 16, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        //g.drawPixmap(hungerBarImage, 12, 16, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);

        //level box
        g.drawPixmap(levelBoxImage, 70, 11, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        levelDisplay.Draw(Integer.toString(game.getData().GetPetLevel()), 3);

        //Emotions
        g.drawPixmap(happyFaceImage, 1, 15, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);

        //Character
        character[game.getData().GetPetChoice()].Draw(deltaTime);


        //Buttons being drawn
        battleButton.Draw();
        quitButton.Draw();
        inventoryButton.Draw();
        shopButton.Draw();

        //For loop here to draw
        for(int k = 0; k < m_poop.size(); ++k)
        {

            m_poop.get(k).Draw();

        }

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
