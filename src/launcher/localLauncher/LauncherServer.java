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
import model.observable.ChessGame;

/**
 *
 * @author Flo
 */
public class LauncherServer {

    public static void main(String[] args) {

        ChessGame chessGame;
        ChessGameControler chessGameControler;
        chessGame = new ChessGame();
        chessGameControler = new ChessGameControler(chessGame);
        chessGameControler.initServer();
        LauncherGUI.main(args);

    }
}
