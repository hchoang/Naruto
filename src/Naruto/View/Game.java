/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.View;

import Naruto.Modle.Character.Enemies.Enemy;
import Naruto.Modle.Character.Ninja;
import Naruto.Modle.GameEngine;
import Naruto.Modle.Utility.StatusDisplay;
import java.io.IOException;
import java.util.Random;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.LayerManager;

/**
 *
 * @author satthuvdh
 */
public class Game extends GameCanvas implements Runnable {

    private final static int ENEMY_DELAY = 1000;
    private final static int BOSS_DELAY = 90000;
    private final static int ITEM_DELAY = 1000;
    private LayerManager layerManager;
    private GameEngine model;
    private Display display;
    private StatusDisplay status;

    public Game(GameEngine model, Display display) {
        super(false);
        layerManager = new LayerManager();
        this.display = display;
        this.model = model;
        model.setScreen(getWidth(), getHeight());
        status = new StatusDisplay(model.getNaruto());


    }

    public void start() {
        Thread t = new Thread(this);

        t.start();

    }

    public void run() {
        Random r = new Random();
        int spamDelay = ENEMY_DELAY;
        long timeCreate = System.currentTimeMillis();
        while (model.isAlive()) {

            model.move();
            model.collision();
            long currentTime = System.currentTimeMillis();

            if (model.createEnemy(currentTime - timeCreate, ENEMY_DELAY)) {
                timeCreate = currentTime;
                spamDelay = r.nextInt(ENEMY_DELAY);
            }

            if (model.createBoss(currentTime - timeCreate, BOSS_DELAY)) {
                timeCreate = currentTime;
                spamDelay = r.nextInt(BOSS_DELAY);
            }

            if (model.createItem(currentTime - timeCreate, ITEM_DELAY)) {
                timeCreate = currentTime;
                spamDelay = r.nextInt(ITEM_DELAY);
            }
            try {
                if (model.getNaruto().getState() != Ninja.UP && model.getNaruto().getState() != Ninja.HIT) {
                    checkKey();
                }

            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

            graphicUpdate();


            try {
                Thread.sleep(33);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            Graphics g = getGraphics();
            g.setColor(255, 255, 255);
            g.fillRect(0, 0, getWidth(), getHeight());
            layerManager.paint(g, 0, 0);
            status.draw(g, getWidth(), getHeight());
            model.drawScore(g, getWidth(), getHeight());
            flushGraphics();
        }

        SubmitScore screen = new SubmitScore(display, model.getScore(), model);
        display.setCurrent(screen);
    }

    private void graphicUpdate() {
        layerManager.append(model.getNaruto().getSprite());



        for (int i = 0; i < model.getEnemies().size(); i++) {
            layerManager.append(((Enemy) model.getEnemies().elementAt(i)).getSprite());
        }
        for (int i = 0; i < model.getItem().size(); i++) {
            layerManager.append(((Enemy) model.getItem().elementAt(i)).getSprite());
        }

        for (int i = 0; i < model.getBackground().size(); i++) {
            layerManager.append(((Background) model.getBackground().elementAt(i)).getSprite());
        }


    }

    public void checkKey() throws IOException, InterruptedException {
        int pressed = getKeyStates();
        if (pressed == LEFT_PRESSED) {
            model.getNaruto().walk(Ninja.LEFT);
        } else if (pressed == RIGHT_PRESSED) {
            model.getNaruto().walk(Ninja.RIGHT);
        } else if (pressed == DOWN_PRESSED) {
            model.getNaruto().sit();
        } else if (pressed == UP_PRESSED) {
            model.getNaruto().jump();
        } else if (pressed == FIRE_PRESSED) {
            model.getNaruto().hit();
        } else {
            model.getNaruto().stand();
        }


    }
}
