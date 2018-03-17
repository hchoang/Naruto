/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.View;

import Naruto.Modle.GameEngine;
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
public class HowToScreen extends Form implements CommandListener {

    private Display display;
    private GameEngine model;
    private Command exit;
    private StringItem stringItem;

    public HowToScreen(Display display, GameEngine model) {
        super("How To Play");
        this.model = model;
        this.display = display;


        stringItem = new StringItem("", "- Controls:\n"
                + "- Navigation Key to control main charactor\n"
                + "- Press middle button to hit\n"
                + "- Press 1 to cast special technique\n"
                + "Item:\n"
                + "- There are some special item on the way to improve ability of charactor\n");

        append(stringItem);
        exit = new Command("Exit", Command.SCREEN, 0);
        addCommand(exit);
        setCommandListener(this);
    }

    public final void commandAction(Command c, Displayable d) {
        if (c == exit) {
            MainScreen mainScreen = new MainScreen(model, display);
            display.setCurrent(mainScreen);
        }
    }
}
