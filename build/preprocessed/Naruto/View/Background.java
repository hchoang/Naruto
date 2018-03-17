/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.View;

import Naruto.Modle.Utility.Position;
import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author satthuvdh
 */
public class Background {

    private Image img;
    private Sprite sprite;
    private boolean isFirst;
    int frame;
    int frameCount;

    public Background(int stage, boolean first) {
        if (stage == 1) {

            try {
                img = Image.createImage("/background.gif");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            sprite = new Sprite(img);

            if (first) {
                setPosition(0, 0);
            } else {

                setPosition(sprite.getWidth(), 0);
            }

        } else {
            try {
                img = Image.createImage("/background2.gif");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            sprite = new Sprite(img);
            if (first) {
                sprite.setPosition(0, 0);
            } else {
                sprite.setPosition(sprite.getWidth(), 0);
            }
        }
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setPosition(int x, int y) {
        sprite.setPosition(x, y);
    }

    public Position getPosition() {
        return new Position(sprite.getX(), sprite.getY());
    }
}
