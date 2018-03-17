/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.View;

import Naruto.Modle.GameEngine;
import Naruto.Modle.Utility.ScoreCompare;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author satthuvdh
 */
public class ScoreDisplayScreen extends Form implements CommandListener {

    private StringItem[] scores;
    private Display display;
    private GameEngine model;

    public ScoreDisplayScreen(Display display, GameEngine model) {
        super("High Score");
        this.model = model;
        this.display = display;
        ScoreCompare store = new ScoreCompare();
        String[] strs = store.getHighScoreString();
        scores = new StringItem[10];
        for (int i = 0; i < strs.length; i++) {
            if (strs[i] == null || strs[i].length() == 0) {
                scores[i] = new StringItem("", (i + 1) + ". ----------------------\n");
            } else {
                scores[i] = new StringItem("", (i + 1) + ". " + strs[i] + "\n");
            }
        }

        for (int i = 0; i < scores.length; i++) {
            append(scores[i]);
        }

        Command exit = new Command("Exit", Command.SCREEN, 0);
        addCommand(exit);
        setCommandListener(this);
    }

    public final void commandAction(Command c, Displayable d) {
        if (c.getLabel().equals("Exit")) {
            MainScreen mainScreen = new MainScreen(model, display);
            display.setCurrent(mainScreen);
        }
    }
}
