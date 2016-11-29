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

        // Get the background to screen ratio
        bgToScreenRatio = (float)g.getHeight() / (float)background.getHeight();

        // Create buttons
        backButton = new Button(g, backButtonNormal, backButtonPressed, 0, 100 - ((int)((float) backButtonNormal.getHeight()/(float)g.getHeight() * 100)), 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);

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
        g.drawPixmap(BuyButtons, 17, 25, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(BuyButtons, 17, 40, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(BuyButtons, 17, 55, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(BuyButtons, 17, 70, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(title, 0, 0, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(Apple, 0, 22, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(Appletxt, 38, 27, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(Cake, -8, 38, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(Caketxt, 38, 41, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(EnergyDrink, 3, 52, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(EnergyDrinktxt, 30, 57, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(Smoothy, 3, 63, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(Smoothytxt, 35, 72, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(currency, 0, 0, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(currency, 79, 27, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(currency, 79, 42, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(currency, 79, 57, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(currency, 79, 72, 0, 0, background.getWidth(), background.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
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
