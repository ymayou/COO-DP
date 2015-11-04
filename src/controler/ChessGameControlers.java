/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import model.Coord;
import model.Couleur;

/**
 *
 * @author You
 */
public interface ChessGameControlers {
    public boolean move(Coord initCoord, Coord finalCoord);
    public String getMessage();	
    public boolean isEnd();	
    public Couleur getColorCurrentPlayer(); 
    
    
}
