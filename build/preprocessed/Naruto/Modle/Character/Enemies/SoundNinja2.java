/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Character.Enemies;

import Naruto.Modle.Character.Ninja;
import Naruto.Modle.Item.*;
import Naruto.Modle.Skill.Jutsu;
import Naruto.Modle.Skill.NarutoRendan;
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
public class SoundNinja2 implements Enemy {

    private int frame;
    private AI ai;
    private Image img;
    private Sprite sprite;
    private boolean alive;
    private int currentHP;
    private int maxHP;
    private int score;
    private int frameCount;
    private int state;

    public SoundNinja2() {
        try {
            img = Image.createImage("/enemy2.gif");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ai = new EnemyAI();
        sprite = new Sprite(img, 37, 47);
        setPosition(new Position(240, 230));
        alive = true;
        maxHP = 100;
        currentHP = 100;
        score = 75;
        frame = 1;
        frameCount = 0;

    }

    public Item getDropItemChance() {

        Random r = new Random();
        int chance = r.nextInt(1000);
        if (chance < 30) {
            return new AbilityImprove();
        } else if (chance > 100 && chance < 175) {
            return new SkillLearning();
        } else if (chance > 200 && chance < 300) {
            return new HealthRecovery();
        } else if (chance > 300 && chance < 400) {
            return new EnergyRecover();
        } else {
            return null;
        }
    }

    public int getPoint() {
        return 30;
    }

    public int getDamage() {
        if (sprite.getFrame() == 10) {
            return 5;
        } else {
            return 0;
        }
    }

    public AI getAi() {
        return ai;
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
        return 1;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public void setHP(int hp) {
        this.currentHP = hp;
        if (currentHP <= 0) {
            alive = false;
        }
    }

    public void setMaxHP(int maxHP) {
    }

    public Position getPosition() {
        return new Position(sprite.getX(), sprite.getY());
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

    public int getScore() {
        return this.score;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public int getFrameCount() {
        return frameCount;
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    public void increaseFrameCount() {
        frameCount++;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void walk(int direction) {
        try {
            img = Image.createImage("/enemy2.gif");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        int x = sprite.getX();
        int y = sprite.getY();
        sprite.setImage(img, 37, 47);

        if (direction == WALK_RIGHT) {
            sprite.setTransform(Sprite.TRANS_MIRROR);
            sprite.setPosition(x, y);
        } else {
            sprite.setTransform(Sprite.TRANS_NONE);
            sprite.setPosition(x, y);
        }


    }

    public void hit() {
        try {
            img = Image.createImage("/enemy2hit.gif");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        sprite.setImage(img, 76, 56);
        setState(Ninja.HIT);

    }

    public void setPosition(Position position) {
        this.sprite.setPosition(position.getX(), position.getY());
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

    public int getState() {
        return state;
    }
}
