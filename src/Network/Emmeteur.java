/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coord;

/**
 *
 * @author Flo
 */
public class Emmeteur {
    private ObjectOutputStream output;
    
    public Emmeteur() {
    }
    
    public void sendMessage(Object coord, Socket s)
    {
        try {
            this.output = new ObjectOutputStream(s.getOutputStream());
            this.output.writeObject(coord);
            this.output.flush();
        } catch (IOException ex) {
            Logger.getLogger(Emmeteur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
