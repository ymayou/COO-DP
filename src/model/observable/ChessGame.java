/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.observable;

import java.util.Observable;
import model.Couleur;
import model.Echiquier;

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
        setChanged();
        notifyObservers();
        return ech.move(xInit, yInit, xFinal, yFinal);
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
}
