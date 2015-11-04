/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Flo
 */
public class Emmeteur {
    private Socket s;
    private ObjectOutputStream output;
    
    public Emmeteur(Socket s) {
        this.s = s;
        try {
            this.output = new ObjectOutputStream(s.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Emmeteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendMessage(Object obj)
    {
        try {
            this.output.writeObject(obj);
        } catch (IOException ex) {
            Logger.getLogger(Emmeteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
