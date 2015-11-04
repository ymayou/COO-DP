/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Flo
 */
public class Recepteur implements Runnable {

    private Socket socket = null;
    private ObjectInputStream input;

    public Recepteur(Socket socket) {
        this.socket = socket;
        try {
            this.input = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Recepteur.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erreur de lecture");
        }
    }

    @Override
    public void run() {
        try {
            Object obj = input.readObject();
            System.out.println("read ok");
        } catch (IOException e) {
            System.err.println("Le serveur distant s'est déconnecté !");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Recepteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
