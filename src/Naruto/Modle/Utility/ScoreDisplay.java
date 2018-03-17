/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Utility;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author satthuvdh
 */
public class ScoreDisplay {

    private int score;

    public ScoreDisplay(int score) {
        this.score = score;
    }

    public final void draw(Graphics g, int screenWidth, int screenHeight) {

        int oldColor = g.getColor();
        g.setColor(0x000000);
        g.drawString(String.valueOf(score), screenWidth - 70, 5, 0);
        g.setColor(oldColor);
    }
    
    
}
