/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Character;

import Naruto.Modle.Item.Item;
import Naruto.Modle.Item.SkillLearning;
import Naruto.Modle.Skill.Jutsu;
import Naruto.Modle.Utility.Position;

import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author satthuvdh
 */
public class Naruto implements Ninja, LearnJutsuAble, Powerupable, ConsumeItemAble {

    private Image img;
    private Sprite sprite;
    private int maxHP;
    private int currentHP;
    private int damage;
    private int currentMana;
    private int maxMana;
    private Jutsu learnedJutsu;
    private int state;
    private boolean alive;
    private int frame;
    private int frameCount;
    private boolean falling;
    private int transform;

    public int getTransform() {
        return transform;
    }

    public void setTransform(int transform) {
        this.transform = transform;
    }

    public Naruto() {
        try {
            this.img = Image.createImage("/naruto.gif");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        sprite = new Sprite(img, 41, 62);
        this.maxHP = 100;
        this.maxMana = 100;

        this.currentHP = 100;
        this.currentMana = 100;

        this.learnedJutsu = null;
        this.state = STAND;
        this.alive = true;
        setPosition(new Position(100, 225));
        this.damage = 15;
        frame = 1;
        frameCount = 0;
        falling = false;
        transform = Sprite.TRANS_NONE;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setHP(int hp) {
        this.currentHP = hp;
        if (hp < 0) {
            alive = false;
        }
    }

    public Position getPosition() {
        Position position = new Position(sprite.getX(), sprite.getY());
        return position;
    }

    public void setMana(int mana) {
        this.currentMana = mana;
    }

    public int getCurrentMana() {
        return this.currentMana;
    }

    public int getMaxHP() {
        return this.maxHP;
    }

    public void learn(Jutsu jutsu) {
        this.learnedJutsu = jutsu;
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public int getCurrentDamage() {
        if (sprite.getFrame() == 14) {
            return damage;
        } else {
            return 0;
        }
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public int getMaxMana() {
        return this.maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void walk(int direction) {
        try {
            this.img = Image.createImage("/narutowalk.gif");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        sprite.setImage(img, 37, 64);

        if (direction == Ninja.RIGHT) {
            sprite.setTransform(Sprite.TRANS_NONE);
            this.setTransform(Sprite.TRANS_NONE);
            setState(WALK_RIGHT);
            this.sprite.setPosition(100, 225);
        } else {
            sprite.setTransform(Sprite.TRANS_MIRROR);
            this.setTransform(Sprite.TRANS_MIRROR);
            setState(WALK_LEFT);
            this.sprite.setPosition(100, 225);
        }

    }

    public void stand() {
        try {
            this.img = Image.createImage("/naruto.gif");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        sprite.setImage(img, 41, 62);
        setState(STAND);
    }

    public void jump() {
        try {
            this.img = Image.createImage("/narutojump.gif");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        sprite.setImage(img, 81, 68);
        setState(UP);
    }

    public void sit() {
        try {
            this.img = Image.createImage("/narutosit.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        sprite.setImage(img, 36, 62);
        setState(DOWN);
    }

    public void hit() {
        try {
            this.img = Image.createImage("/narutohit.gif");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        sprite.setImage(img, 60, 59);
        setState(HIT);
    }

    public void consumeItem(Item item) {
        if (item.getType().equals(Item.AP)) {

            maxHP += 10;


            maxMana += 10;

        } else if (item.getType().equals(Item.HP)) {
            if (currentHP + 10 < maxHP) {
                currentHP += 10;
            } else {
                currentHP = maxHP;
            }
        } else if (item.getType().equals(Item.MANA)) {
            if (currentMana + 10 < maxMana) {
                currentMana += 10;
            } else {
                currentMana = maxMana;
            }
        } else {
            this.learnedJutsu = ((SkillLearning) item).getJutsu();
        }
    }

    public void cast(Jutsu jutsu) {
        this.sprite.setImage(jutsu.getImage(), jutsu.getFrameWidth(), jutsu.getFrameHeight());
    }

    public void run() {
    }

    public int getSpeed() {
        return 3;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public int getFrameCount() {
        return this.frameCount;
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }

    public void increaseFrameCount() {
        frameCount++;
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

    public void animate() {
        if (getState() == UP) {
            if (getSprite().getFrame() == 7) {
                stand();
            } else {

                if (getSprite().getY() > 185 && !isFalling()) {
                    getSprite().setFrame(0);
                    getSprite().move(0, -3);
                } else {
                    setFalling(true);
                    if (getSprite().getY() < 215) {
                        getSprite().setFrame(2);
                        getSprite().move(0, 3);
                    } else if (getSprite().getY() < 225) {
                        getSprite().setFrame(3);
                        getSprite().move(0, 3);
                    } else if (getSprite().getY() <= 225) {

                        getSprite().nextFrame();

                    }

                }

            }

        } else {
            setFalling(false);
            if (getState() == Ninja.HIT && getSprite().getFrame() == 14) {
                stand();
            }

            getSprite().nextFrame();
            setFrameCount(0);
        }
    }
}
