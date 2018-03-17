/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Utility;

/**
 *
 * @author satthuvdh
 */
public class Position {

    private int X;
    private int y;

    public Position(int X, int y) {
        this.X = X;
        this.y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return y;
    }

    public void setX(int X) {
        this.X = X;
    }

    public void setY(int y) {
        this.y = y;
    }
}
