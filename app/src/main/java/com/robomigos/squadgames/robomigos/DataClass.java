package com.robomigos.squadgames.robomigos;

import android.app.Activity;
import android.content.res.AssetManager;

import com.framework.FileIO;
import com.framework.impl.AndroidFileIO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Carlo Albino on 2016-11-20.
 */

public class DataClass implements FileIO{

    private int petChoice;
    private int petLevel;       // don't need to save this
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
    private int maxHP;          // don't save this
    private int atkPower;       // don't save this

    public DataClass(AssetManager assets)
    {
        petChoice = 0; // If after load the petChoice is 0 then it is a new game.
        unlockedLevel = 1; // 1 is only easy unlocked, 5 is release unlocked.
        petLevel = 0;
        petExperience = 0;
        happinessLvl = 0.0f;
        hungerLvl = 0.0f;
        hp = 0;
        maxHP = 0;
        atkPower = 0;
        money = 0;
        numOfItem1 = 0;
        numOfItem2 = 0;
        numOfItem3 = 0;
        numOfItem4 = 0;
        numOfItem5 = 0;
        this.assets = assets;
        this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
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
    public void AddHP(float hpDiff) {
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
    public boolean LoadSave()
    {
        // Load a save file from a text doc
        return true;
    }

    public boolean SaveGame()
    {
        // Save the game here
        String saveString = "";
        saveString += petChoice + "_";
        saveString += petExperience + "_";
        saveString += money + "_";
        saveString += unlockedLevel + "_";
        saveString += numOfItem1 + "_";
        saveString += numOfItem2 + "_";
        saveString += numOfItem3 + "_";
        saveString += numOfItem4 + "_";
        saveString += numOfItem5 + "_";
        saveString += happinessLvl + "_";
        saveString += hungerLvl + "_";
        saveString += hp + "_";


        return true;
    }

    @Override
    public InputStream readAsset(String fileName) throws IOException {
        return null;
    }

    @Override
    public InputStream readFile(String fileName) throws IOException {
        return null;
    }

    @Override
    public OutputStream writeFile(String fileName) throws IOException {
        return null;
    }
}
