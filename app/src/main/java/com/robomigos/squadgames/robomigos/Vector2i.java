package com.robomigos.squadgames.robomigos;

/**
 * Created by Carlo Albino on 2016-11-10.
 */

public class Vector2i {
    public int X, Y;

    public Vector2i()
    {
        X = 0;
        Y = 0;
    }

    public Vector2i(int x, int y)
    {
        X = x;
        Y = y;
    }

    public Vector2i(float x, float y)
    {
        X = (int)x;
        Y = (int)y;
    }
}
