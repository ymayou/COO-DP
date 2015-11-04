/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Flo
 */
public class Server {
    private ServerSocket ss;

    public Server(ServerSocket ss) {
        this.ss = ss;
        acceptPlayers(2);
    }
    
    private void acceptPlayers(int nb){
        try {
            ss.accept();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
