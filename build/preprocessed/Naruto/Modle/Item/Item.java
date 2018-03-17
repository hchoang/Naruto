/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Item;

import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author satthuvdh
 */
public interface Item {

    String AP = "AbilityImprove";
    String MANA = "EnegyRecover";
    String HP = "HeathRecovery";
    String SL = "SkillLearning";

    String getType();
    
    Sprite getSprite();
    
}
