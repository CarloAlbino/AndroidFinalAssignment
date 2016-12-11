package com.framework;

public abstract class Screen {
	
    protected final Game game;
    protected float bgToScreenRatio;

    protected static Music bgMusic;

    public Screen(Game game)
    {
        this.game = game;
        this.bgToScreenRatio = 1;

        // Load music
        if(bgMusic != null) {
            if (!bgMusic.isPlaying()) {
                bgMusic = game.getAudio().newMusic("Audio/Music/Title.ogg");
                bgMusic.setLooping(true);
                bgMusic.play();
            }
        }else {
            bgMusic = game.getAudio().newMusic("Audio/Music/Title.ogg");
            bgMusic.setLooping(true);
            bgMusic.play();
        }

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

    protected void LoadNewBackgroundMusic(String fileName)
    {
        bgMusic.stop();
        bgMusic.dispose();
        bgMusic = game.getAudio().newMusic(fileName);
        bgMusic.setLooping(true);
        bgMusic.play();
    }

}


