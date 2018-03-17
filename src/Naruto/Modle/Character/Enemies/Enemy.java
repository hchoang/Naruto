/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Character.Enemies;

import Naruto.Modle.Character.Ninja;
import Naruto.Modle.Item.Item;
import Naruto.Modle.Utility.AI;

/**
 *
 * @author satthuvdh
 */
public interface Enemy extends Ninja {

    Item getDropItemChance();

    int getDamage();

    AI getAi();

    int getScore();
}
