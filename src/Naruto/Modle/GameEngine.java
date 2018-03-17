/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle;

import Naruto.Modle.Character.Enemies.Enemy;
import Naruto.Modle.Character.Enemies.SoundNinja1;
import Naruto.Modle.Character.Enemies.SoundNinja2;
import Naruto.Modle.Character.Enemies.Zabuza;
import Naruto.Modle.Character.Naruto;
import Naruto.Modle.Character.Ninja;
import Naruto.Modle.Item.*;
import Naruto.View.Background;
import Naruto.Modle.Utility.ScoreDisplay;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author satthuvdh
 */
public class GameEngine {

    private Naruto naruto;
    private int score;
    private Vector enemies;
    private Vector items;
    private Vector background;
    private ScoreDisplay scoreDisplay;
    private Display display;
    private int width;
    private int height;
    private int countBackground;

    public GameEngine(Display display) {
        score = 0;
        this.display = display;
        this.naruto = new Naruto();
        enemies = new Vector();
        items = new Vector();
        background = new Vector();

        Background bg = new Background(1, true);

        background.addElement(bg);
        countBackground = 1;

//        statusBar = new StatusBar();
//        scoreDisplay = new ScoreDisplay(score);
    }

    public ScoreDisplay getScoreDisplay() {
        return scoreDisplay;
    }

    public Naruto getNaruto() {
        return this.naruto;
    }

    public boolean isAlive() {
        return naruto.isAlive();
    }

    public int getScore() {
        return score;
    }

    public Vector getBackground() {
        return background;
    }

    public Vector getEnemies() {
        return enemies;
    }

    public Vector getItem() {
        return items;
    }

    public void move() {
        if (naruto.getFrameCount() == naruto.getFrame()) {
            naruto.animate();
        } else {
            naruto.increaseFrameCount();
        }

        for (int i = 0; i < enemies.size(); i++) {
            if (((Enemy) enemies.elementAt(i)).getSprite().getX() + ((Enemy) enemies.elementAt(i)).getSprite().getWidth() < 0) {
                enemies.removeElementAt(i);
                i--;
            } else {
                if (((Enemy) enemies.elementAt(i)).checkFrameSkip()) {
                    ((Enemy) enemies.elementAt(i)).getSprite().nextFrame();
                }
                ((Enemy) enemies.elementAt(i)).getAi().move(((Enemy) enemies.elementAt(i)), naruto, width, height);
            }
        }

        for (int i = 0; i < items.size(); i++) {
            Item it = (Item) items.elementAt(i);
            if (((Item) items.elementAt(i)).getSprite().getX() < 0) {
                items.removeElementAt(i);
                i--;
            }
            if (naruto.getState() == Ninja.WALK_RIGHT) {
                it.getSprite().move(-naruto.getSpeed(), 0);
            } else if (naruto.getState() == Ninja.WALK_LEFT) {
                if (it.getSprite().getX() < 0) {
                    it.getSprite().move(naruto.getSpeed(), 0);
                } else {
                    naruto.getSprite().move(-naruto.getSpeed(), 0);
                }

            }
        }
        for (int i = 0; i < background.size(); i++) {
            Background bg = (Background) background.elementAt(i);

            if (bg.getSprite().getX() <= 0 && background.size() < 2) {
                background.addElement(new Background(2, false));
            }

            if (bg.getSprite().getX() <= 0 - bg.getSprite().getWidth()) {

                background.removeElementAt(i);
                i--;
            }

            if (naruto.getState() == Ninja.WALK_RIGHT) {
                bg.getSprite().move(-naruto.getSpeed(), 0);
            } else if (naruto.getState() == Ninja.WALK_LEFT) {
                if (bg.getSprite().getX() < 0) {
                    bg.getSprite().move(naruto.getSpeed(), 0);
                } else {
                    naruto.getSprite().move(-naruto.getSpeed(), 0);
                }

            }
            if (naruto.getState() == Ninja.UP) {
                if (naruto.getTransform() == Sprite.TRANS_NONE) {
                    bg.getSprite().move(-naruto.getSpeed(), 0);
                } else {
                    if (bg.getSprite().getX() < 0) {
                        bg.getSprite().move(naruto.getSpeed(), 0);
                    }
                }
            }

        }
    }

    public boolean createEnemy(long l, int spamDelay) {

        if (l > spamDelay && enemies.size() < 3) {
            Enemy enemy = null;
            Random r = new Random();

            int time = r.nextInt(500);
            if (time < 250) {
                enemy = new SoundNinja1();

            } else {
                enemy = new SoundNinja2();

            }
            enemies.addElement(enemy);
            return true;
        } else {
            return false;
        }
    }

    public boolean createItem(long l, int spamDelay) {
        if (l > spamDelay && items.size() < 3) {
            Item it = null;
            Random r = new Random();

            int time = r.nextInt(500);
            if (time < 100) {
                it = new AbilityImprove();

            } else if (time < 200) {
                it = new HealthRecovery();

            } else if (time < 300) {
                it = new EnergyRecover();
            } else {
                it = new SkillLearning();
            }
            //items.addElement(it);
            return true;
        } else {
            return false;
        }
    }

    public void collision() {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy enemy = (Enemy) enemies.elementAt(i);
            if (naruto.getState() == Ninja.HIT) {
                if (enemy.getSprite().collidesWith(naruto.getSprite(), true)) {
                    enemy.setHP(enemy.getCurrentHP() - naruto.getCurrentDamage());
                }
                if (!enemy.isAlive()) {
                    remove(enemy);
                    i--;
                }
            }
            if (enemy.getState() == Ninja.HIT) {
                if (enemy.getSprite().collidesWith(naruto.getSprite(), true)) {
                    naruto.setHP(naruto.getCurrentHP() - enemy.getDamage());
                }
            }
        }

        for (int i = 0; i < items.size(); i++) {
            Item it = (Item) items.elementAt(i);
            if (naruto.getSprite().collidesWith(it.getSprite(), true)) {
                naruto.consumeItem(it);
                items.removeElementAt(i);
                i--;
            }



        }
    }

    public void setScreen(int width, int height) {
        this.width = width;
        this.height = height;

    }

    public void remove(Enemy enemy) {


        enemies.removeElement(enemy);
        enemy.getSprite().setVisible(false);


        this.score += enemy.getScore();

    }

    public boolean createBoss(long l, int spamDelay) {
        if (l > spamDelay && enemies.size() < 3) {

            Random r = new Random();


            Enemy enemy = new Zabuza();


            enemies.addElement(enemy);
            return true;
        } else {
            return false;
        }
    }

    public final void drawScore(Graphics g, int screenWidth, int screenHeight) {

        g.setColor(255, 255, 255);
        g.drawString("Total score:" + score, screenWidth - 75, 6, 0);
    }
}
