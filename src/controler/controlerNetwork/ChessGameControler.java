/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.controlerNetwork;
import Network.Emmeteur;
import controler.ChessGameControlers;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
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
    private InputStream input;
    private Thread reception;
    
    @Override
    public void run() {
        while(true)
        {
            try {
                this.input = this.s.getInputStream();
                try {
                    Object obj = new ObjectInputStream(this.input).readObject();
                    if (obj != null)
                    {
                        String[] result = ((String)obj).split("|");
                        game.move(Integer.parseInt(result[1]), Integer.parseInt(result[3]), Integer.parseInt(result[5]), Integer.parseInt(result[7]));
                        break;
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ChessGameControler.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException e) {
                System.err.println("aucun message");
            }
        }
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
            this.s = new Socket("127.0.0.1", 2009);
            this.emmeteur = new Emmeteur();
        } catch (IOException e) {
                System.err.println("Le port est déjà utilisé !");
        }
    }
    
    public void initClient()
    {
        try {
            this.s = new Socket("127.0.0.1", 2009);
            this.emmeteur = new Emmeteur();
            reception = new Thread(this);
            reception.start();
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
        this.game.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);
        
        String parser = initCoord.x + "|" + initCoord.y + "|" + finalCoord.x + "|" + finalCoord.y; 
        emmeteur.sendMessage(parser, s);
        
        reception = new Thread(this);
        reception.start();
        return true;
    }

    @Override
    public String getMessage() {
        return this.game.getMessage();
    }

    @Override
    public boolean isEnd() {
        return this.game.isEnd();
    }

    @Override
    public Couleur getColorCurrentPlayer() {
        return this.game.getColorCurrentPlayer();
    }
    
    public void closeReception()
    {
        if (reception!= null)
        {
            if (reception.isAlive())
                reception = null;
        }
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




