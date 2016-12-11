package com.robomigos.squadgames.robomigos;

import android.app.Activity;

import com.framework.FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


/**
 * Created by Carlo Albino on 2016-11-20.
 */

public class DataClass{

    private int petChoice;
    private int petLevel;
    private int petExperience;
    private int money;
    private int unlockedLevel;
    private int numOfItem1;
    private int numOfItem2;
    private int numOfItem3;
    private int numOfItem4;
    private int numOfItem5;
    private float happinessLvl;
    private float hungerLvl;
    private int hp;
    private int maxHP;
    private int atkPower;

    public DataClass()
    {
        petChoice = -1; // If after load the petChoice is -1 then it is a new game.
        unlockedLevel = 1; // 1 is only easy unlocked, 5 is release unlocked.
        petLevel = 0;
        petExperience = 0;
        happinessLvl = 1.0f;
        hungerLvl = 0.0f;
        hp = 10;
        maxHP = 10;
        atkPower = 6;
        money = 100000;
        numOfItem1 = 2;
        numOfItem2 = 2;
        numOfItem3 = 2;
        numOfItem4 = 2;
        numOfItem5 = 2;
    }

    public int GetPetChoice() {return petChoice;}
    public int GetPetLevel() {return petLevel;}
    public int GetPetExperience() {return petExperience;}
    public int GetMoney() {return money;}
    public int GetUnlockedLevel() {return unlockedLevel;}
    public int GetNumOfItem1() {return numOfItem1;}
    public int GetNumOfItem2() {return numOfItem2;}
    public int GetNumOfItem3() {return numOfItem3;}
    public int GetNumOfItem4() {return numOfItem4;}
    public int GetNumOfItem5() {return numOfItem5;}
    public float GetHappiness() {return happinessLvl;}
    public float GetHunger() {return hungerLvl;}
    public int GetHP() {return hp;}
    public int GetMaxHP() {return maxHP;}
    public int GetAtkPower() {return atkPower;}

    ////////////////////////////////
    // Manipulating Game Settings //
    ////////////////////////////////
    // This should only be called when pet is chosen
    // Set to 0 when the pet is release.
    public void SetPetChoice(int choice) {petChoice = choice;}
    // This should be called after beating a group of enemies,
    // only set if the current unlocked number is smaller that the number it will be.
    public void SetUnlockLevel(int level) {unlockedLevel = level;}

    /////////////////////////////////////
    // Manipulating the Player's Stats //
    /////////////////////////////////////
    // Experience added after each battle
    public void SetPetExperience(int newExp) {
        petExperience += Math.abs(newExp);
        // Level Up system goes here.
        // Max HP raising goes here too.
        // AtkPower gets set here as well.
        int neededEXP;
        switch(petLevel)
        {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                neededEXP = 200;
                break;
            case 6:
            case 7:
            case 8:
            case 9:
                neededEXP = 400;
                break;
            case 10:
            case 11:
            case 12:
                neededEXP = 700;
                break;
            case 13:
            case 14:
            case 15:
                neededEXP = 1000;
                break;
            case 16:
                neededEXP = 1500;
                break;
            case 17:
                neededEXP = 2000;
                break;
            case 18:
                neededEXP = 2500;
                break;
            case 19:
                neededEXP = 4000;
                break;
            default:
                // Max Level
                neededEXP = -1;
                break;
        }

        while(neededEXP >= 0 && petExperience > neededEXP)
        {
            // Update level and stats
            petLevel++;
            maxHP += 3;
            hp += 3;
            atkPower += 2;
            petExperience = petExperience - neededEXP;
        }
    }
    // Home and battle affects the pets happiness
    public void AddHappiness(float happinessDiff) {
        happinessLvl += happinessDiff;
        if(happinessLvl > 1.0f)
        {
            happinessLvl = 1.0f;
        }

        if(happinessLvl < 0.0f)
        {
            happinessLvl = 0.0f;
        }
    }
    // Used in home after time, after battle the pet gets hungry
    public void AddHunger(float hungerDiff) {
        hungerLvl += hungerDiff;
        if(hungerLvl > 1.0f)
        {
            hungerLvl = 1.0f;
        }
        if(hungerLvl < 0.0f)
        {
            hungerLvl = 0.0f;
        }
    }
    // Used for potions and during battles
    public void AddHP(int hpDiff) {
        hp += hpDiff;
        if(hp > maxHP)
        {
            hp = maxHP;
        }
        if(hp < 0)
        {
            hp = 0;
        }
    }
    // Pass in negative number to lower the money positive to increase
    public void AddMoney(int moneyDiff) {
        money += moneyDiff;
        if(money < 0)
        {
            money = 0;
        }
    }

    ////////////////////////////
    // Manipulating Inventory //
    ////////////////////////////
    // Pass a positive number to add, pass a negative number to subtract, same for all items.
    public void AddItem1(int itemDiff) {
        numOfItem1 += itemDiff;
        if(numOfItem1 < 0)
        {
            numOfItem1 = 0;
        }
    }
    public void AddItem2(int itemDiff) {
        numOfItem2 += itemDiff;
        if(numOfItem2 < 0)
        {
            numOfItem2 = 0;
        }
    }
    public void AddItem3(int itemDiff) {
        numOfItem3 += itemDiff;
        if(numOfItem3 < 0)
        {
            numOfItem3 = 0;
        }
    }
    public void AddItem4(int itemDiff) {
        numOfItem4 += itemDiff;
        if(numOfItem4 < 0)
        {
            numOfItem4 = 0;
        }
    }
    public void AddItem5(int itemDiff) {
        numOfItem5 += itemDiff;
        if(numOfItem5 < 0)
        {
            numOfItem5 = 0;
        }
    }

    ////////////////////////////////
    // Manipulating the Save File //
    ////////////////////////////////
    // Call LoadSave(game.getFileIO()) to load, should only load at the beginning of the game.
    public void LoadSave(FileIO saveFile)
    {
        // Load a save file from a text doc
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(saveFile.readFile(".robomigos")));
            petChoice = Integer.parseInt(in.readLine());
            petExperience = Integer.parseInt(in.readLine());
            petLevel = Integer.parseInt(in.readLine());
            money = Integer.parseInt(in.readLine());
            unlockedLevel = Integer.parseInt(in.readLine());
            numOfItem1 = Integer.parseInt(in.readLine());
            numOfItem2 = Integer.parseInt(in.readLine());
            numOfItem3 = Integer.parseInt(in.readLine());
            numOfItem4 = Integer.parseInt(in.readLine());
            numOfItem5 = Integer.parseInt(in.readLine());
            happinessLvl = Float.parseFloat(in.readLine());
            hungerLvl = Float.parseFloat(in.readLine());
            hp = Integer.parseInt(in.readLine());
            maxHP = Integer.parseInt(in.readLine());
            atkPower = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            // :( It's ok we have defaults
        } catch (NumberFormatException e) {
            // :/ It's ok, defaults save our day
        } finally {
            try {
                if (in != null)
                    in.close();
            } catch (IOException e) {
            }
        }
    }

    // Call LoadSave(game.getFileIO()) to load, should be called when leaving inventory/shop,
    // when leaving home, when leaving battle, when battle complete (lose or win), when release,
    // on level up.
    public void SaveGame(FileIO saveFile)
    {
        // Save the game here
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(saveFile.writeFile(".robomigos")));
            out.write(Integer.toString(petChoice));
            out.write(Integer.toString(petExperience));
            out.write(Integer.toString(petLevel));
            out.write(Integer.toString(money));
            out.write(Integer.toString(unlockedLevel));
            out.write(Integer.toString(numOfItem1));
            out.write(Integer.toString(numOfItem2));
            out.write(Integer.toString(numOfItem3));
            out.write(Integer.toString(numOfItem4));
            out.write(Integer.toString(numOfItem5));
            out.write(Float.toString(happinessLvl));
            out.write(Float.toString(hungerLvl));
            out.write(Integer.toString(hp));
            out.write(Integer.toString(maxHP));
            out.write(Integer.toString(atkPower));
        } catch (IOException e) {
        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException e) {
            }
        }
    }
}
