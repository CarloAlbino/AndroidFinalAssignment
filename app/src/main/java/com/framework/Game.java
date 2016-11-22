package com.framework;

import com.robomigos.squadgames.robomigos.DataClass;

public interface Game
{
    public DataClass getData();
    public Input getInput();
    public FileIO getFileIO();
    public Graphics getGraphics();
    public Audio getAudio();
    public void setScreen(Screen screen);
    public Screen getCurrentScreen();
    public Screen getStartScreen();
}

