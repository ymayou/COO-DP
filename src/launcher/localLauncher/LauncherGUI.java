/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package launcher.localLauncher;

import controler.controlerLocal.ChessGameControler;
import javax.swing.JFrame;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import launcher.test;
import model.observable.ChessGame;
import vue.ChessGameGUI;

/**
 *
 * @author florian.garcia
 */
public class LauncherGUI {
    public static boolean DEBUG = false;
    
    public static void main(String[] args){
        ChessGame chessGame;
        ChessGameControler chessGameControler;		

        chessGame = new ChessGame();	
        chessGameControler = new ChessGameControler(chessGame);
        
        

        ChessGameGUI viewCmd = new ChessGameGUI(chessGameControler);	
        chessGame.addObserver(viewCmd);
        
        JFrame frame = viewCmd;
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(!DEBUG);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}
