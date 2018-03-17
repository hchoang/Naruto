/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Character;

import Naruto.Modle.Utility.Position;

/**
 *
 * @author satthuvdh
 */
public interface Movable {

    Position getPosition();

    void setPosition(Position position);

    boolean checkFrameSkip();
}
