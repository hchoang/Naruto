/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Item;

import Naruto.Modle.Skill.Jutsu;
import Naruto.Modle.Skill.KageBunshinNoJutsu;
import Naruto.Modle.Skill.NarutoRendan;
import java.io.IOException;
import java.util.Random;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author satthuvdh
 */
public class SkillLearning implements Item {

    private Jutsu jutsu;
    private Image img;
    private Sprite sprite;

    public SkillLearning() {
        try {
            img = Image.createImage("/jutsu.gif");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        sprite = new Sprite(img);
        sprite.setPosition(240, 250);
        this.jutsu = type();
    }

    public Jutsu type() {
        Random r = new Random();
        int i = r.nextInt(2);
        if (i == 0) {
            return new KageBunshinNoJutsu();
        } else {
            return new NarutoRendan();
        }
    }

    public Jutsu getJutsu() {
        return jutsu;
    }

    public void setJutsu(Jutsu jutsu) {
        this.jutsu = jutsu;
    }

    public String getType() {
        return this.SL;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
