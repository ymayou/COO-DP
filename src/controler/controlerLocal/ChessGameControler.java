/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler.controlerLocal;

import controler.ChessGameControlers;
import java.util.List;
import model.Coord;
import model.Couleur;
import model.PieceIHM;
import model.observable.ChessGame;

/**
 *
 * @author You
 */
public class ChessGameControler implements ChessGameControlers{
    
    private final ChessGame game;
    
    public ChessGameControler()
    {
        this.game = new ChessGame();
    }
    
    public ChessGameControler(ChessGame game)
    {
        this.game = game;
    }
    
    public List<PieceIHM> getPiecesIHM(){
        return game.getPiecesIHM();
    }
    
    @Override
    public boolean move(Coord initCoord, Coord finalCoord) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //return this.game.move(initCoord.x, initCoord.y, finalCoord.x, finalCoord.y);
        return true;
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
    
}
