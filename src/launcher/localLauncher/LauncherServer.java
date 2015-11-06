/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.localLauncher;

import controler.controlerNetwork.ChessGameControler;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import model.observable.ChessGame;
import vue.ChessGameGUI;

/**
 *
 * @author Flo
 */
public class LauncherServer {

    public static void main(String[] args) {

        ChessGame chessGame = new ChessGame();
        final ChessGameControler chessGameControler = new ChessGameControler(chessGame);
        chessGameControler.initServer();
        
        ChessGameGUI viewCmd = new ChessGameGUI(chessGameControler);
        chessGame.addObserver(viewCmd);
        
        JFrame frame = viewCmd;
        frame.setTitle("JOUEUR BLANC");
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                chessGameControler.closeReception();
                System.exit(0);
            }
        });
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }
}
