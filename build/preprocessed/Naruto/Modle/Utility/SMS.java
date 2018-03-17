/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Utility;

import java.io.IOException;
import javax.microedition.io.Connector;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

/**
 *
 * @author satthuvdh
 */
public class SMS extends Thread {

    private String number;
    private int score;
    private MessageConnection conn;

    public SMS(String number, int score) {
        this.number = number;
        this.score = score;

    }

    public final void run() {
        try {
            String port = "555559";
            String recipient = "+" + number;
            String protocol = "sms://" + ":" + port;
            String add = "sms://" + recipient;
            conn = (MessageConnection) Connector.open(protocol);
            TextMessage txt = (TextMessage) conn.newMessage(MessageConnection.TEXT_MESSAGE);
            txt.setAddress(add);
            String sendText = "My new high score: " + score;
            txt.setPayloadText(sendText);
            conn.send(txt);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (IOException ex) {
                System.out.println("Cannot close the connection !");
            }
        }
    }
}
