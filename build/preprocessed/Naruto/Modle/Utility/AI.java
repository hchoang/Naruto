/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Utility;

import Naruto.Modle.Character.Movable;
import Naruto.Modle.Character.Naruto;

/**
 *
 * @author satthuvdh
 */
public interface AI {

    void move(Movable source, Movable object, int width, int height);
}
