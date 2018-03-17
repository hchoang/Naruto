/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Naruto.Modle.Utility;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author satthuvdh
 */
public class Web extends Thread {

    private int score;

    public Web(int score) {
        this.score = score;

    }

    public final void connection() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            HttpConnection con = (HttpConnection) Connector.open("https://mekong.rmit.edu.vn/~s3275199/MAD/index.html");
            DataInputStream inputStream = (DataInputStream) con.openDataInputStream();
            byte[] buff = new byte[256];

            while (true) {
                int rd = inputStream.read(buff, 0, 256);
                if (rd == -1) {
                    break;
                }
                bos.write(buff, 0, rd);
            }
            bos.flush();
            buff = bos.toByteArray();
            System.out.println(new String(buff));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public final void run() {
        try {
            HttpConnection con = (HttpConnection) Connector.open("https://mekong.rmit.edu.vn/~s3269900/MAD/uploadScore.php");
            con.setRequestMethod(con.POST);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            String params = "Score:" + score;
            OutputStream out = con.openOutputStream();
            out.write(params.getBytes());
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
