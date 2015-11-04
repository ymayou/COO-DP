/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.controlerNetwork;
import Network.Emmeteur;
import Network.Recepteur;
import controler.ChessGameControlers;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coord;
import model.Couleur;
import model.PieceIHM;
import model.observable.ChessGame;

/**
 *
 * @author Flo
 */
public class ChessGameControler implements ChessGameControlers, Runnable {

    private final ChessGame game;
    private ServerSocket ss;
    private Socket s;
    private Emmeteur emmeteur;
    private Recepteur recepteur;
    
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ChessGameControler() {
        this.game = new ChessGame();
    }
    
    public void initServer(){
        try {
            this.ss = new ServerSocket(2009, 2, InetAddress.getByAddress(new byte[]{127,0,0,1}));
            System.out.println("Le serveur est à l'écoute du port "+ss.getLocalPort());
            
            Thread thAccept = new Thread(new AccepterClient(ss));
            thAccept.start();
            
        } catch (IOException e) {
                System.err.println("Le port est déjà utilisé !");
        }
    }
    
    public void initClient()
    {
        try {
            s = new Socket("127.0.0.1", 2009);
            Thread thClient = new Thread(new Recepteur(s));
            thClient.start();
            emmeteur = new Emmeteur(s);
        } catch (IOException ex) {
            Logger.getLogger(ChessGameControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ChessGameControler(ChessGame game) {
        this.game = game;
    }

    public List<PieceIHM> getPiecesIHM() {
        return game.getPiecesIHM();
    }

    @Override
    public boolean move(Coord initCoord, Coord finalCoord) {
        emmeteur.sendMessage("yacine");
        return this.game.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);
    }

    @Override
    public String getMessage() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this.game.getMessage();
    }

    @Override
    public boolean isEnd() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this.game.isEnd();
    }

    @Override
    public Couleur getColorCurrentPlayer() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this.game.getColorCurrentPlayer();
    }
    
    private class AccepterClient implements Runnable
    {
        
        ServerSocket ss;
        
        public AccepterClient(ServerSocket ss)
        {
            this.ss = ss;
        }

        @Override
        public void run() {
            while(true)
            {
                try {
                    s = ss.accept();
                    System.out.println("nouvelle connexion");
                } catch (IOException ex) {
                    Logger.getLogger(ChessGameControler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}




