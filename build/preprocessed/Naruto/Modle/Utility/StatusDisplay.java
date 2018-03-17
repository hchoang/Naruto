/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Utility;

import Naruto.Modle.Character.Injurable;
import Naruto.Modle.Character.Ninja;
import javax.microedition.lcdui.Graphics;

/**
 *
 * @author satthuvdh
 */
public class StatusDisplay {
    
    private Ninja inj;
    private int maxHP;
    private int currentHP;
    private int maxMana;
    private int currentMana;
    int paddingTop = 10;
    int paddingLeft = 20;
    int height = 5;
    int color;
    
    public StatusDisplay(Ninja inj) {
        color = 0xFF0000;
        this.inj = inj;
    }
    
    public final void draw(Graphics g, int screenWidth, int screenHeight) {
        update();
        
        g.setColor(0x000000);
        g.drawRect(paddingTop - 1, paddingLeft - 1, screenWidth / 2 + 1, height + 1);
        g.setColor(247, 17, 17);
        g.fillRect(paddingTop, paddingLeft, screenWidth / 2 * currentHP / maxHP, height);
        g.setColor(247, 17, 17);
        
        
        g.setColor(0x000000);
        g.drawRect(paddingTop - 1 , paddingLeft - 1+20, screenWidth / 2 + 1, height + 1);
        g.setColor(17, 52, 247);
        g.fillRect(paddingTop , paddingLeft+20, screenWidth / 2 * currentMana / maxMana, height);
        g.setColor(247, 17, 17);
    }
    
    private void update() {
        maxHP = inj.getMaxHP();
        currentHP = inj.getCurrentHP();
        maxMana = inj.getMaxMana();
        currentMana = inj.getCurrentMana();
    }
}
