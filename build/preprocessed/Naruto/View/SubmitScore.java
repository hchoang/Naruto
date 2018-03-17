/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.View;

import Naruto.Modle.GameEngine;
import Naruto.Modle.Utility.SMS;
import Naruto.Modle.Utility.ScoreCompare;
import Naruto.Modle.Utility.Web;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author satthuvdh
 */
public class SubmitScore extends Form implements CommandListener {

    private StringItem scoreGain;
    private Display display;
    private TextField phoneNumber;
    private int score;
    private Command command;
    private GameEngine model;

    public SubmitScore(Display display, int score, GameEngine model) {
        super("Submit Score");
        this.model = model;
        this.display = display;
        this.score = score;

        scoreGain = new StringItem("", "Your score is " + score + "\n", StringItem.PLAIN);

        phoneNumber = new TextField("Enter phone number(leave blank if do not want to send score)", "", 10, TextField.PHONENUMBER);


        append(scoreGain);
        append(phoneNumber);

        command = new Command("Exit", Command.SCREEN, 0);
        addCommand(command);
        setCommandListener(this);
    }

    public final void commandAction(Command c, Displayable d) {
        if (c == command) {
            ScoreCompare store = new ScoreCompare();
            store.saveScore(score);
            Web webService = new Web(score);
            webService.start();

            if (!(phoneNumber.getString().equals(""))) {
                SMS sms = new SMS(phoneNumber.getString(), score);
                sms.start();

            }

            MainScreen mainScreen = new MainScreen(model, display);
            display.setCurrent(mainScreen);

        }
    }
}
