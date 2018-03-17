/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Naruto.Modle.GameEngine;
import Naruto.View.MainScreen;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

/**
 * @author satthuvdh
 */
public class Run extends MIDlet {

    private Display display;
    private GameEngine model;
    public final static int STAND = 1;
    public final static int WALK = 2;
    public final static int SIT = 3;
    public final static int JUMP = 4;

    public void startApp() {
        display = Display.getDisplay(this);
        model = new GameEngine(display);
        MainScreen ms = new MainScreen(model, display);
        display.setCurrent(ms);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
