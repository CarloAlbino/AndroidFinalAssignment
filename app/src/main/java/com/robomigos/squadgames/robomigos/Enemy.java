package com.robomigos.squadgames.robomigos;

import com.framework.Graphics;
import com.framework.Pixmap;

/**
 * Created by Carlo Albino on 2016-12-02.
 */

public class Enemy extends UIElement{
    private Pixmap sprite;
    private int maxHP;
    private int HP;
    private int atk;
    private int expGiven;

    private float animTime;
    private float atkTime;
    private float damageTime;

    public Enemy(Graphics g, Pixmap s, int l, int t, int srcX, int srcY, int screenW, int screenH, float ratio,
                 int hp, int a, int exp)
    {
        graphics = g;
        sprite = s;
        left = l;
        top = t;
        sourceX = srcX;
        sourceY = srcY;
        sourceWidth = sprite.getWidth();
        sourceHeight = sprite.getHeight();
        screenWidth = screenW;
        screenHeight = screenH;
        bgRatio = ratio;
        SetBottomRight();
        ConvertToScreenCoord();

        maxHP = hp;
        HP = maxHP;
        atk = a;
        expGiven = exp;

        animTime = 0;
        atkTime = 0.5f;  // Attack animation takes 0.5 seconds
        damageTime = 0.25f; // Damage animation takes 0.25 seconds
    }

    public int GetMaxHP()
    {
        return maxHP;
    }

    public int GetCurrentHP()
    {
        return HP;
    }

    public int GetAttackPower()
    {
        return atk;
    }

    public int GetExpGiven()
    {
        return expGiven;
    }

    public void Damage(int damage)
    {
        HP -= Math.abs(damage);
        if(HP < 0)
        {
            HP = 0;
        }
    }

    public void AtkAnimation(float DeltaTime, boolean attacking)
    {
        if(attacking) {
            if (animTime < atkTime) {
                left = left - 2;
                SetBottomRight();
                ConvertToScreenCoord();
            } else {
                left = left + 2;
                animTime = 0;
            }

            animTime += DeltaTime;
        }
    }

    public void DamageAnimation(float DeltaTime, boolean takingDamage)
    {
        if(takingDamage) {
            if (animTime < damageTime * 6) {
                if(animTime < damageTime) {
                    sourceX = sourceWidth;
                    sourceY = sourceHeight;
                }else if(animTime < damageTime * 2)
                {
                    sourceX = 0;
                    sourceY = 0;
                }else if(animTime < damageTime * 3)
                {
                    sourceX = sourceWidth;
                    sourceY = sourceHeight;
                }else if(animTime < damageTime * 4)
                {
                    sourceX = 0;
                    sourceY = 0;
                }else if(animTime < damageTime * 5)
                {
                    sourceX = sourceWidth;
                    sourceY = sourceHeight;
                }else
                {
                    sourceX = 0;
                    sourceY = 0;
                }

            } else {
                sourceX = 0;
                sourceY = 0;
                animTime = 0;
            }

            animTime += DeltaTime;
        }
    }

    @Override
    public void Draw() {
        graphics.drawPixmap(sprite, left, top, sourceX, sourceY, sourceWidth, sourceHeight,
                screenWidth, screenHeight, bgRatio);
    }
}
