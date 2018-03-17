/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Utility;

import Naruto.Modle.Character.Enemies.Enemy;
import Naruto.Modle.Character.Movable;
import Naruto.Modle.Character.Naruto;
import Naruto.Modle.Character.Ninja;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author satthuvdh
 */
public class EnemyAI implements AI {

    public void move(Movable source, Movable object, int width, int height) {
        Enemy ninja = (Enemy) source;
        Naruto naruto = (Naruto) object;
        int nX = naruto.getSprite().getX();
        int nY = naruto.getSprite().getY();

        int eX = ninja.getSprite().getX();
        int eY = ninja.getSprite().getY();
        if (naruto.getState() == Ninja.WALK_RIGHT || (naruto.getState() == Ninja.UP && naruto.getTransform() == Sprite.TRANS_NONE)) {
            ninja.getSprite().move(-naruto.getSpeed(), 0);
        } else if (naruto.getState() == Ninja.WALK_LEFT) {
            ninja.getSprite().move(naruto.getSpeed(), 0);
        }


        if (ninja.getSprite().getX() < naruto.getSprite().getX() + naruto.getSprite().getWidth() && ninja.getSprite().getX() + ninja.getSprite().getWidth() > naruto.getSprite().getX()) {
            if ((ninja.getSprite().getX()) < naruto.getSprite().getX()) {
               
                ninja.getSprite().setTransform(Sprite.TRANS_MIRROR);
                ninja.hit();
                
            } else if (ninja.getSprite().getX() <= (naruto.getSprite().getX() + naruto.getSprite().getWidth())) {
                
                ninja.getSprite().setTransform(Sprite.TRANS_NONE);
                ninja.hit();
                
            }

        } 
        else {
            if (naruto.getSprite().getX() <= ninja.getSprite().getX()) {
                ninja.getSprite().move(0 - ninja.getSpeed(), 0);
                //ninja.walk(Ninja.WALK_LEFT);
            } else {

                ninja.getSprite().move(ninja.getSpeed(), 0);
                ninja.walk(Ninja.WALK_RIGHT);
            }
        }

    }
}
