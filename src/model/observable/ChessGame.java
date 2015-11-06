/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.observable;

import java.util.List;
import java.util.Observable;
import model.Couleur;
import model.Echiquier;
import model.PieceIHM;
import model.Pieces;

/**
 *
 * @author You
 */
public class ChessGame extends Observable
{
    private Echiquier ech;
    public ChessGame ()
    {
        ech = new Echiquier();
    }
    @Override
    public String toString()
    {
        return ech.toString();
    }
    public boolean move (int xInit, int yInit, int xFinal, int yFinal)
    {
        boolean ret = false;
        
        if(ech.isMoveOk(xInit, yInit, xFinal, yFinal)){
            ech.move(xInit, yInit, xFinal, yFinal);
            ech.switchJoueur();
            ret=true;
            
        } 
        setChanged();
        notifyObservers(ech.getPiecesIHM());
        return ret;
        
    }
    public boolean isEnd()
    {
        setChanged();
        notifyObservers();
        return ech.isEnd();
    }
    public String getMessage()
    {
        return ech.getMessage();
    }
    public Couleur getColorCurrentPlayer()
    {
        return ech.getColorCurrentPlayer();
    }
    
    public List<PieceIHM> getPiecesIHM(){
	return ech.getPiecesIHM();
    }
}
