package com.robomigos.squadgames.robomigos.Scenes;

import com.framework.Game;
import com.framework.Graphics;
import com.framework.Input;
import com.framework.Pixmap;
import com.framework.Screen;

import java.util.List;

/**
 * Created by Carlo Albino on 2016-11-14.
 */

public class BattleScreen extends Screen {

    public static Pixmap buttonBackgroundImage;
   //public static Pixmap fireButtonImage;
   //public static Pixmap leafButtonImage;
   //public static Pixmap waterButtonImage;
    public static Pixmap backButtonImage;

    public BattleScreen(Game game)
    {
        super(game);
        Graphics g = game.getGraphics();

        // Load the bitmaps

        // Get the background to screen ratio
        //bgToScreenRatio = (float)g.getHeight() / (float)background.getHeight();

        // Create buttons

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

            if(event.type == Input.TouchEvent.TOUCH_UP)
            {

            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

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
