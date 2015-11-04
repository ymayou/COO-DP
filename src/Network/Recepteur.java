/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Flo
 */
public class Recepteur implements Runnable {

    private BufferedReader in;
    private Object message = null;

    public Recepteur(BufferedReader in, Object mess) {
        this.in = in;
        this.message = mess;
    }

    @Override
    public void run() {
        while (true) {
            try {
                message = in.read();
                System.out.println("Message recu : " + message.toString());
            } catch (IOException ex) {
                Logger.getLogger(Recepteur.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
