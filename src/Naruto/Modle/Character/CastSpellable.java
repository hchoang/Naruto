/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Character;

import Naruto.Modle.Skill.Jutsu;

/**
 *
 * @author satthuvdh
 */
public interface CastSpellable {

    void cast(Jutsu jutsu);

    void setMana(int mana);

    int getMaxMana();

    void setMaxMana(int maxMana);

    int getCurrentMana();
}
