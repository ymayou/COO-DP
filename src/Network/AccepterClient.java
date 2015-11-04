/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Flo
 */
public class AccepterClient implements Runnable{
    
    private ServerSocket ss = null;
    private Socket s = null;

    public AccepterClient(ServerSocket ss) {
        this.ss = ss;
    }
    
    @Override
    public void run() {
        while(true){
            try {
                s = ss.accept();
                System.out.println("Un joueur s'est connecté !");
                
            } catch (IOException ex) {
                Logger.getLogger(AccepterClient.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
}
