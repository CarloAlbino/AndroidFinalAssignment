package com.framework;

public abstract class Screen {
	
    protected final Game game;
    protected float bgToScreenRatio;

    public Screen(Game game)
    {
        this.game = game;
        this.bgToScreenRatio = 1;
    }

    public abstract void update(float deltaTime) throws InterruptedException;
    public abstract void present(float deltaTime);
    public abstract void pause();
    public abstract void resume();
    public abstract void dispose();

    protected boolean inBounds(Input.TouchEvent event,
                             int x, int y,
                             int width, int height) {
        if (event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
    }

}


