package com.robomigos.squadgames.robomigos;

import com.framework.Screen;
import com.framework.impl.AndroidGame;
import com.robomigos.squadgames.robomigos.Scenes.TitleScreen;

public class SimpleGame extends AndroidGame {

    @Override
    public Screen getStartScreen() {
        return new TitleScreen(this);
    }
}
