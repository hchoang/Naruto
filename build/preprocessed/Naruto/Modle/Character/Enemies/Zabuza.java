/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Character.Enemies;

import Naruto.Modle.Item.*;
import Naruto.Modle.Skill.Jutsu;
import Naruto.Modle.Utility.AI;
import Naruto.Modle.Utility.EnemyAI;
import Naruto.Modle.Utility.Position;
import java.io.IOException;
import java.util.Random;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author satthuvdh
 */
public class Zabuza implements Enemy {

    private AI ai;
    private Sprite sprite;
    private Image img;
    private int currentHP;
    private boolean alive;
    private int state;
    private int frame;
    private int frameCount;

    public Zabuza() {
        try {
            img = Image.createImage("/zabuza.gif");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        sprite = new Sprite(img, 45, 60);
        this.ai = new EnemyAI();
        this.currentHP = 400;
        alive = true;
        frame = 4;
        frameCount = 0;
    }

    public Item getDropItemChance() {
        Random r = new Random();
        int i = r.nextInt(4);
        if (i == 0) {
            return new AbilityImprove();
        } else if (i == 1) {
            return new EnergyRecover();
        } else if (i == 2) {
            return new HealthRecovery();
        } else {
            return new SkillLearning();
        }
    }

    public int getDamage() {
        return 10;
    }

    public AI getAi() {
        return ai;
    }

    public int getScore() {
        return 150;
    }

    public Sprite getSprite() {
        return this.sprite;


    }

    public int getCurrentHP() {
        return this.currentHP;
    }

    public boolean isAlive() {
        return alive;
    }

    public int getSpeed() {
        return 3;
    }

    public void setState(int state) {
    }

    public int getState() {
        return this.state;
    }

    public void hit() {
    }

    public void walk(int direction) {
    }

    public int getMaxHP() {
        return 400;
    }

    public void setHP(int hp) {
    }

    public void setMaxHP(int maxHP) {
    }

    public Position getPosition() {
        return new Position(sprite.getX(), sprite.getY());
    }

    public void setPosition(Position position) {
    }

    public boolean checkFrameSkip() {
        if (frameCount == frame) {
            frameCount = 0;
            return true;
        } else {
            frameCount++;
            return false;
        }
    }

    public void cast(Jutsu jutsu) {
    }

    public void setMana(int mana) {
    }

    public int getMaxMana() {
        return 0;
    }

    public void setMaxMana(int maxMana) {
    }

    public int getCurrentMana() {
        return 0;
    }

    public void run() {
    }
}
