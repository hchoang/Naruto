/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.View;

import Naruto.Modle.GameEngine;
import javax.microedition.lcdui.*;

/**
 *
 * @author satthuvdh
 */
public class MainScreen extends List implements CommandListener {

    private GameEngine model;
    private Display display;

    public MainScreen(GameEngine model, Display display) {
        super("Naruto", List.IMPLICIT);
        this.model = model;
        this.display = display;
        append("Start Game", null);
        append("High Score", null);
        append("How to play", null);
        setCommandListener(this);
    }

    public void commandAction(Command c, Displayable d) {
        if (this.getSelectedIndex() == 0) {
            Game panel = new Game(model, display);
            display.setCurrent(panel);
            panel.setFullScreenMode(true);
            panel.start();
        } else if (this.getSelectedIndex() == 1) {
            ScoreDisplayScreen screen = new ScoreDisplayScreen(display, model);
            display.setCurrent(screen);


        } else if (this.getSelectedIndex() == 2) {
            HowToScreen screen = new HowToScreen(display, model);
            display.setCurrent(screen);
        }
    }
}
