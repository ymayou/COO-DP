/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.localLauncher;

import controler.controlerNetwork.ChessGameControler;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static launcher.localLauncher.LauncherGUI.DEBUG;
import model.observable.ChessGame;
import vue.ChessGameGUI;

/**
 *
 * @author Flo
 */
public class LauncherServer {

    public static void main(String[] args) {

        try {
            ChessGame chessGame;
            ChessGameControler chessGameControler;
            
            ServerSocket ss = new ServerSocket(2606,2,InetAddress.getByAddress(new byte[]{127,0,0,1}));
            chessGame = new ChessGame();
            chessGameControler = new ChessGameControler(chessGame);
            chessGameControler.initServer(ss);
            LauncherGUI.main(args);
        } catch (IOException ex) {
            Logger.getLogger(LauncherServer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
