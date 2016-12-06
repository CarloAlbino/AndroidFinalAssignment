package com.robomigos.squadgames.robomigos.Scenes;

import com.framework.Game;
import com.framework.Graphics;
import com.framework.Input;
import com.framework.Pixmap;
import com.framework.Screen;
import com.robomigos.squadgames.robomigos.AnimatedPixmap;
import com.robomigos.squadgames.robomigos.Button;
import com.robomigos.squadgames.robomigos.DisplayBar;
import com.robomigos.squadgames.robomigos.Enemy;

import java.util.List;
import java.util.Random;

/**
 * Created by Carlo Albino on 2016-11-14.
 */

public class BattleScreen extends Screen {

    public static Pixmap battleEnvironmentImage;
    public static Pixmap buttonBackgroundImage;
    public static Pixmap fireButtonImage;
    public static Pixmap leafButtonImage;
    public static Pixmap waterButtonImage;
    public static Pixmap runButtonImage;

    public Button fireButton;
    public Button leafButton;
    public Button waterButton;
    public Button runButton;

    public static Pixmap petImage[];
    public AnimatedPixmap player;

    public static Pixmap smlEnemy1;
    public static Pixmap smlEnemy2;
    public static Pixmap smlEnemy3;
    public static Pixmap smlEnemyBoss;
    public static Pixmap lrgEnemy1;
    public static Pixmap lrgEnemy2;
    public static Pixmap lrgEnemy3;
    public static Pixmap lrgEnemy4;
    public Enemy[] enemies;
    public int currentLevel;

    // Battle control
    private int currentEnemy;
    private float waitTime;
    private float currentTimer;
    private boolean step1;
    private boolean step2;
    boolean attackChosen;

    public static Pixmap barBackground;
    public static Pixmap barForeground;

    public static Pixmap[] attackIcons;
    private int currentPlayerAttack;
    private int currentEnemyAttack;

    public DisplayBar playerHealth;
    public DisplayBar enemyHealth;

    public BattleScreen(Game game, int level)
    {
        super(game);
        Graphics g = game.getGraphics();

        // Load the bitmaps
        battleEnvironmentImage = g.newPixmap("BattleScene/battleenvironment.png", Graphics.PixmapFormat.ARGB4444);
        buttonBackgroundImage = g.newPixmap("BattleScene/BattleBackground.png", Graphics.PixmapFormat.ARGB4444);
        fireButtonImage = g.newPixmap("BattleScene/FireButton.png", Graphics.PixmapFormat.ARGB4444);
        leafButtonImage = g.newPixmap("BattleScene/LeafButton.png", Graphics.PixmapFormat.ARGB4444);
        waterButtonImage = g.newPixmap("BattleScene/WaterButton.png", Graphics.PixmapFormat.ARGB4444);
        runButtonImage = g.newPixmap("BattleScene/RunButton.png", Graphics.PixmapFormat.ARGB4444);

        // Load the player bitmaps
        petImage = new Pixmap[3];
        petImage[1] = g.newPixmap("robotman.png", Graphics.PixmapFormat.ARGB4444);
        petImage[0] = g.newPixmap("frog.png", Graphics.PixmapFormat.ARGB4444);
        petImage[2] = g.newPixmap("cyborgbird.png", Graphics.PixmapFormat.ARGB4444);

        // Load the enemy bitmaps
        smlEnemy1 = g.newPixmap("BattleScene/bluecube.png", Graphics.PixmapFormat.ARGB4444);
        smlEnemy2 = g.newPixmap("BattleScene/greencube.png", Graphics.PixmapFormat.ARGB4444);
        smlEnemy3 = g.newPixmap("BattleScene/redcube.png", Graphics.PixmapFormat.ARGB4444);
        smlEnemyBoss = g.newPixmap("BattleScene/boss1.png", Graphics.PixmapFormat.ARGB4444);
        lrgEnemy1 = g.newPixmap("BattleScene/badguy1.png", Graphics.PixmapFormat.ARGB4444);
        lrgEnemy2 = g.newPixmap("BattleScene/badguy2.png", Graphics.PixmapFormat.ARGB4444);
        lrgEnemy3 = g.newPixmap("BattleScene/badguy3.png", Graphics.PixmapFormat.ARGB4444);
        lrgEnemy4 = g.newPixmap("BattleScene/badguy4.png", Graphics.PixmapFormat.ARGB4444);

        // Load Attack bitmaps
        attackIcons = new Pixmap[3];
        attackIcons[0] = g.newPixmap("BattleScene/FireIcon.png", Graphics.PixmapFormat.ARGB4444);
        attackIcons[1] = g.newPixmap("BattleScene/WaterIcon.png", Graphics.PixmapFormat.ARGB4444);
        attackIcons[2] = g.newPixmap("BattleScene/LeafIcon.png", Graphics.PixmapFormat.ARGB4444);
        // -1 is no attack
        currentPlayerAttack = -1;
        currentEnemyAttack = -1;

        // Load the healthbar bitmaps
        barBackground = g.newPixmap("BackBorderUI.png", Graphics.PixmapFormat.ARGB4444);
        barForeground = g.newPixmap("HealthBar.png", Graphics.PixmapFormat.ARGB4444);


        // Get the background to screen ratio
        bgToScreenRatio = (float)g.getHeight() / (float)buttonBackgroundImage.getHeight();

        // Create buttons
        fireButton = new Button(g, fireButtonImage, fireButtonImage, 52, 64, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        waterButton = new Button(g, waterButtonImage, waterButtonImage, 2, 64, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        leafButton = new Button(g, leafButtonImage, leafButtonImage, 2, waterButton.Bottom() + 3, 0, 0,g.getWidth(), g.getHeight(), bgToScreenRatio);
        runButton = new Button(g, runButtonImage, runButtonImage, 52, fireButton.Bottom() + 3, 0, 0,g.getWidth(), g.getHeight(), bgToScreenRatio);

        // Create Health bars
        playerHealth = new DisplayBar(g, barBackground, barForeground, 2, 32, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);
        enemyHealth = new DisplayBar(g, barBackground, barForeground, 52, 55, 0, 0, g.getWidth(), g.getHeight(), bgToScreenRatio);

        if(game.getData().GetPetChoice() == 1)
            player = new AnimatedPixmap(g, petImage[game.getData().GetPetChoice()], 6, 35, 12, 3, 256, 256, g.getWidth(), g.getHeight(), bgToScreenRatio);
        else
            player = new AnimatedPixmap(g, petImage[game.getData().GetPetChoice()], 6, 35, 12, 2, 256, 256, g.getWidth(), g.getHeight(), bgToScreenRatio);

        enemies = new Enemy[5];
        currentLevel = level;

        SetEnemies();

        currentEnemy = 0;
        waitTime = 1.55f;
        currentTimer = 0;
        step1 = false;
        step2 = false;
        attackChosen = false;
    }

    @Override
    public void update(float deltaTime) throws InterruptedException {
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
                if(!attackChosen) {
                    if (fireButton.IsInBounds(event)) {
                        ProcessResult(CheckResult(0));
                        attackChosen = true;
                        currentPlayerAttack = 0;
                    }

                    if (waterButton.IsInBounds(event)) {
                        ProcessResult(CheckResult(1));
                        attackChosen = true;
                        currentPlayerAttack = 1;
                    }

                    if (leafButton.IsInBounds(event)) {
                        ProcessResult(CheckResult(2));
                        attackChosen = true;
                        currentPlayerAttack = 2;
                    }

                    if (runButton.IsInBounds(event)) {
                        game.setScreen(new HomeScreen(game));
                        return;
                    }
                }
            }
        }

        if(attackChosen)
        {
            currentTimer += deltaTime;
            // Wait
            // Show Attacks

            // Wait

            // Player Animate?
            // Enemy Damage Anim

            // Wait
            if(currentTimer > 1 && !step1) {
                // Update enemy health bar
                enemyHealth.fillAmount = (float) enemies[currentEnemy].GetCurrentHP() / (float) enemies[currentEnemy].GetMaxHP();
                step1 = true;

            }
            // Wait

            // Enemy Animate?
            // Player Damage Anim

            // Wait
            if(currentTimer > 2 && !step2) {
                // Update player health bar
                playerHealth.fillAmount = (float) game.getData().GetHP() / (float) game.getData().GetMaxHP();
                step2 = true;
            }

            // Wait
            if(currentTimer > 3) {
                // Check for enemy death
                if (enemies[currentEnemy].GetCurrentHP() <= 0) {
                    if (currentEnemy < enemies.length - 1) {
                        game.getData().SetPetExperience(enemies[currentEnemy].GetExpGiven());
                        currentEnemy++;
                        enemyHealth.fillAmount = (float) enemies[currentEnemy].GetCurrentHP() / (float) enemies[currentEnemy].GetMaxHP();
                    } else {
                        game.setScreen(new WinScreen(game));
                        return;
                    }
                }

                // Check for player death
                if (game.getData().GetHP() <= 0) {
                    if (game.getData().GetNumOfItem1() > 0 ||
                            game.getData().GetNumOfItem2() > 0 ||
                            game.getData().GetNumOfItem3() > 0 ||
                            game.getData().GetNumOfItem4() > 0 ||
                            game.getData().GetNumOfItem5() > 0 ||
                            game.getData().GetMoney() > 200) {
                        game.setScreen(new LoseScreen(game));
                        return;
                    } else {
                        game.setScreen(new GameOverScreen(game));
                        return;
                    }
                }
                currentPlayerAttack = -1;
                currentEnemyAttack = -1;
                currentTimer = 0;
                step1 = false;
                step2 = false;
                attackChosen = false;
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        // Draw backgroud
        g.drawPixmap(battleEnvironmentImage, 0, 0, 0, 0, battleEnvironmentImage.getWidth(), battleEnvironmentImage.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        g.drawPixmap(buttonBackgroundImage, 0, 0, 100, 100, 0, 0, buttonBackgroundImage.getWidth(), buttonBackgroundImage.getHeight(), g.getWidth(), g.getHeight());

        // If the player has attacked display the attack image
        if(currentPlayerAttack > -1)
        {
            g.drawPixmap(attackIcons[currentPlayerAttack], 2, 5, 0, 0, buttonBackgroundImage.getWidth(), buttonBackgroundImage.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        }

        // If the player has attacked display the attack image
        if(currentEnemyAttack > -1)
        {
            g.drawPixmap(attackIcons[currentEnemyAttack], 60, 5, 0, 0, buttonBackgroundImage.getWidth(), buttonBackgroundImage.getHeight(), g.getWidth(), g.getHeight(), bgToScreenRatio);
        }

        // Draw player and enemy
        player.Draw(deltaTime);
        enemies[currentEnemy].Draw();

        // Draw health bars
        playerHealth.Draw();
        enemyHealth.Draw();

        // Draw buttons
        fireButton.Draw();
        leafButton.Draw();
        waterButton.Draw();
        runButton.Draw();
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

    private int CheckResult(int playerChoice)
    {
        Random rand = new Random();
        int enemyChoice = rand.nextInt(3);
        currentEnemyAttack = enemyChoice;

        if(playerChoice == enemyChoice)
        {
            // Tied
            return 0;
        }
        else if (playerChoice == 0 && enemyChoice == 1)
        {
            // enemy wins
            return 1;
        }
        else if(playerChoice == 1 && enemyChoice == 2)
        {
            // enemy wins
            return 1;
        }
        else if(playerChoice == 2 && enemyChoice == 0)
        {
            // enemy wins
            return 1;
        }
        else
        {
            // player wins
            return 2;
        }
    }

    private void ProcessResult(int result)
    {
        float playerMultiplier;
        float enemyMultiplier;
        if(result == 0)
        {
            // tie
            playerMultiplier = 1;
            enemyMultiplier = 1;
        }
        else if(result == 1)
        {
            // player loses
            playerMultiplier = 0.5f;
            enemyMultiplier = 1.5f;
        }
        else
        {
            // player wins
            playerMultiplier = 1.5f;
            enemyMultiplier = 0.5f;
        }
        // Damage the enemy first
        enemies[currentEnemy].Damage((int)((float)game.getData().GetAtkPower() * playerMultiplier));
        // If the enemy is not dead damage the player
        if(enemies[currentEnemy].GetCurrentHP() > 0) {
            game.getData().AddHP(-(int) ((float) enemies[currentEnemy].GetAttackPower() * enemyMultiplier));
        }
    }

    private void SetEnemies()
    {
        // Set enemies based on the selected difficulty
        switch (currentLevel)
        {
            case 1:
            {
                enemies[0] = new Enemy(game.getGraphics(), smlEnemy1, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 5, 2, 100);
                enemies[1] = new Enemy(game.getGraphics(), smlEnemy2, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 5, 2, 200);
                enemies[2] = new Enemy(game.getGraphics(), smlEnemy3, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 7, 3, 250);
                enemies[3] = new Enemy(game.getGraphics(), smlEnemy1, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 7, 4, 250);
                enemies[4] = new Enemy(game.getGraphics(), smlEnemy3, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 9, 5, 300);
            }
                break;
            case 2:
            {
                enemies[0] = new Enemy(game.getGraphics(), smlEnemy1, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 7, 5, 400);
                enemies[1] = new Enemy(game.getGraphics(), smlEnemy2, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 9, 5, 400);
                enemies[2] = new Enemy(game.getGraphics(), smlEnemy3, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 12, 7, 450);
                enemies[3] = new Enemy(game.getGraphics(), lrgEnemy2, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 14, 7, 550);
                enemies[4] = new Enemy(game.getGraphics(), lrgEnemy1, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 16, 8, 600);
            }
                break;
            case 3:
            {
                enemies[0] = new Enemy(game.getGraphics(), smlEnemy3, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 12, 6, 500);
                enemies[1] = new Enemy(game.getGraphics(), smlEnemy1, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 13, 8, 500);
                enemies[2] = new Enemy(game.getGraphics(), lrgEnemy1, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 15, 9, 550);
                enemies[3] = new Enemy(game.getGraphics(), lrgEnemy3, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 16, 10, 550);
                enemies[4] = new Enemy(game.getGraphics(), smlEnemyBoss, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 30, 5, 600);
            }
                break;
            case 4:
            {
                enemies[0] = new Enemy(game.getGraphics(), smlEnemyBoss, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 25, 5, 600);
                enemies[1] = new Enemy(game.getGraphics(), lrgEnemy1, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 16, 10, 650);
                enemies[2] = new Enemy(game.getGraphics(), lrgEnemy2, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 18, 11, 700);
                enemies[3] = new Enemy(game.getGraphics(), lrgEnemy3, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 19, 13, 750);
                enemies[4] = new Enemy(game.getGraphics(), lrgEnemy4, 68, 35, 0, 0, game.getGraphics().getWidth(), game.getGraphics().getHeight(), bgToScreenRatio, 25, 15, 800);
            }
                break;
            default:
                System.out.println("ERROR! Setting enemies failed.  Passed in level is incorrect.");
                break;
        }
    }
}
