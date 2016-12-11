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

public class ShopScreen extends Screen {

    private static Pixmap background;
    private static Pixmap backButtonNormal;
    private static Pixmap backButtonPressed;
    private static Pixmap title;
    private static Pixmap Apple;
    private static Pixmap Appletxt;
    private static Pixmap Cake;
    private static Pixmap Caketxt;
    private static Pixmap Smoothy;
    private static Pixmap Smoothytxt;
    private static Pixmap EnergyDrink;
    private static Pixmap EnergyDrinktxt;
    private static Pixmap currency;
    private static Pixmap BuyButtons;


    //private Button BuyButton;
    private Button backButton;
    private Button AppleButton;
    private Button CakeButton;
    private Button SmoothyButton;
    private Button EnergyDrinkButton;

    public static Pixmap font;
    public NumberDisplay levelDisplay;
    public NumberDisplay ApplePrice;
    public NumberDisplay CakePrice;
    public NumberDisplay SmoothyPrice;
    public NumberDisplay EnergyPrice;

    public ShopScreen(Game game)
    {
        super(game);
        Graphics g = game.getGraphics();

        // Load the bitmaps
        background = g.newPixmap("MenuBackground.png", Graphics.PixmapFormat.RGB565);
        backButtonNormal = g.newPixmap("BackButtonUnPressed.png", Graphics.PixmapFormat.ARGB4444);
        backButtonPressed = g.newPixmap("BackButtonPressed.png", Graphics.PixmapFormat.ARGB4444);
        BuyButtons = g.newPixmap("BuyButtons.png", Graphics.PixmapFormat.ARGB4444);
        title = g.newPixmap("StoreLogo.png", Graphics.PixmapFormat.ARGB4444);
        Appletxt = g.newPixmap("Appletxt.png", Graphics.PixmapFormat.ARGB4444);
        Caketxt = g.newPixmap("Caketxt.png", Graphics.PixmapFormat.ARGB4444);
        EnergyDrinktxt = g.newPixmap("EnergyDrinktxt.png", Graphics.PixmapFormat.ARGB4444);
        Smoothytxt = g.newPixmap("Smoothytxt.png", Graphics.PixmapFormat.ARGB4444);
        currency = g.newPixmap("RoboCurrency.png", Graphics.PixmapFormat.ARGB4444);
        Apple = g.newPixmap("Apple.png", Graphics.PixmapFormat.ARGB4444);
        Cake = g.newPixmap("Cake.png", Graphics.PixmapFormat.ARGB4444);
        EnergyDrink = g.newPixmap("EnergyDrink.png", Graphics.PixmapFormat.ARGB4444);
        Smoothy = g.newPixmap("Smoothy.png", Graphics.PixmapFormat.ARGB4444);

        font = g.newPixmap("numbersBlack.png", Graphics.PixmapFormat.ARGB4444);
        levelDisplay = new NumberDisplay(g, font, 18, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        ApplePrice = new NumberDisplay(g, font, 90, 26, g.getWidth(), g.getHeight(), bgToScreenRatio);
        CakePrice = new NumberDisplay(g, font, 90, 41, g.getWidth(), g.getHeight(), bgToScreenRatio);
        EnergyPrice = new NumberDisplay(g, font, 90, 56, g.getWidth(), g.getHeight(), bgToScreenRatio);
        SmoothyPrice = new NumberDisplay(g, font, 90, 71, g.getWidth(), g.getHeight(), bgToScreenRatio);
        // Get the background to screen ratio
        bgToScreenRatio = (float)g.getHeight() / (float)background.getHeight();

        // Create buttons
        backButton = new Button(g, backButtonNormal, backButtonPressed, 0, 100 - ((int)((float) backButtonNormal.getHeight()/(float)g.getHeight() * 100)), 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        AppleButton = new Button(g, BuyButtons, BuyButtons, 18, 26, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        CakeButton = new Button(g, BuyButtons, BuyButtons, 18, 41, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        EnergyDrinkButton = new Button(g, BuyButtons, BuyButtons, 18, 56, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        SmoothyButton = new Button(g, BuyButtons, BuyButtons, 18, 71, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);

        LoadNewBackgroundMusic("Audio/Music/Shop.ogg");
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

                if(AppleButton.IsInBounds(event))
                {
                    if(game.getData().GetMoney() >= 20) {
                        game.getData().AddItem1(1);
                        game.getData().AddMoney(-20);
                        game.getData().SaveGame(game.getFileIO());
                    }
                }
                if(CakeButton.IsInBounds(event))
                {
                    if(game.getData().GetMoney() >= 30) {
                        game.getData().AddItem2(1);
                        game.getData().AddMoney(-30);
                        game.getData().SaveGame(game.getFileIO());
                    }
                }
                if(EnergyDrinkButton.IsInBounds(event))
                {
                    if(game.getData().GetMoney() >= 50) {
                        game.getData().AddItem3(1);
                        game.getData().AddMoney(-50);
                        game.getData().SaveGame(game.getFileIO());
                    }

                }
                if(SmoothyButton.IsInBounds(event))
                {
                    if(game.getData().GetMoney() >= 75) {
                        game.getData().AddItem4(1);
                        game.getData().AddMoney(-75);
                        game.getData().SaveGame(game.getFileIO());
                    }
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(background, 0, 0, 100, 100, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight());
        AppleButton.Draw();
        CakeButton.Draw();
        EnergyDrinkButton.Draw();
        SmoothyButton.Draw();
        //g.drawPixmap(BuyButtons, 17, 25, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        //g.drawPixmap(BuyButtons, 17, 40, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        //g.drawPixmap(BuyButtons, 17, 55, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        //g.drawPixmap(BuyButtons, 17, 70, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(title, 0, 0, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(Apple, 0, 22, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(Appletxt, 38, 27, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(Cake, -8, 38, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(Caketxt, 38, 41, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(EnergyDrink, 3, 52, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(EnergyDrinktxt, 30, 57, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(Smoothy, 3, 63, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(Smoothytxt, 35, 72, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(currency, 0, 5, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(currency, 79, 27, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(currency, 79, 42, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(currency, 79, 57, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(currency, 79, 72, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);

        levelDisplay.Draw(Integer.toString(game.getData().GetMoney()), 3);
        ApplePrice.Draw(Integer.toString(20), 2);
        CakePrice.Draw(Integer.toString(30), 2);
        EnergyPrice.Draw(Integer.toString(50), 2);
        SmoothyPrice.Draw(Integer.toString(75), 2);
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
