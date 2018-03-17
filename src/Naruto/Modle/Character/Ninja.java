/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Character;

import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author satthuvdh
 */
public interface Ninja extends Injurable, Movable, CastSpellable, Runnable {

    int UP = 1;
    int RIGHT = 2;
    int LEFT = 5;
    int DOWN = 6;
    int STAND = 8;
    int WALK_LEFT = 9;
    int WALK_RIGHT = 3;
    int HIT = 4;

    Sprite getSprite();

    int getCurrentHP();

    boolean isAlive();

    int getSpeed();

    void setState(int state);

    int getState();

    void hit();

    void walk(int direction);
}
