/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Item;

import java.io.IOException;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author satthuvdh
 */
public class EnergyRecover implements Item {

    private Image img;
    private Sprite sprite;

    public EnergyRecover() {
        try {
            img = Image.createImage("/enegy.gif");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        sprite = new Sprite(img);
        sprite.setPosition(240, 250);
    }

    public String getType() {
        return this.MANA;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
