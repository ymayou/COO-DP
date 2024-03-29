/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import controler.ChessGameControlers;
import controler.controlerNetwork.ChessGameControler;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import model.Coord;
import model.Couleur;
import model.PieceIHM;
import model.observable.ChessGame;
import tools.ChessImageProvider;
import tools.ChessPieceImage;
import tools.ChessPiecePos;

/**
 *
 * @author florian.garcia
 */
public class ChessGameGUI extends JFrame implements MouseListener, MouseMotionListener, Observer {

    private ChessGameControler controler;
    

    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;

    int xInit;
    int yInit;
    int xFinal;
    int yFinal;

    public ChessGameGUI(ChessGameControlers controler) {
        this.controler = (ChessGameControler) controler;

        Dimension boardSize = new Dimension(800, 800);

//  Use a Layered Pane for this this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

//Add a chess board to the Layered Pane 
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0) {
                square.setBackground(i % 2 == 0 ? Color.black : Color.white);
            } else {
                square.setBackground(i % 2 == 0 ? Color.white : Color.black);
            }
        }

//Add a few pieces to the board
        List<PieceIHM> listePiece = ((ChessGameControler) controler).getPiecesIHM();

        for (PieceIHM p : listePiece) {
            List<Coord> listCoord = p.getList();
            for (Coord c : listCoord) {
                JLabel piece = new JLabel(new ImageIcon(ChessImageProvider.getImageFile(p.getTypePiece(), p.getCouleur())));
                int pos = (8 * c.y) + c.x;
                JPanel panel = (JPanel) chessBoard.getComponent(pos);
                panel.add(piece);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());

        if (c instanceof JLabel) {

            Point parentLocation = c.getParent().getLocation();
            xAdjustment = parentLocation.x - e.getX();
            yAdjustment = parentLocation.y - e.getY();
            chessPiece = (JLabel) c;
            chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
            chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
            layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);

            xInit = chessPiece.getX() / 100;
            yInit = chessPiece.getY() / 100;

            for (int i = 0; i < 800; i = i + 100) {
                for (int j = 0; j < 800; j = j + 100) {
                    Component p1 = chessBoard.findComponentAt(i, j);
                    if (p1 instanceof JLabel) {
                        p1 = p1.getParent();
                    }
                    if (controler.isMoveOk(xInit, yInit, i / 100, j / 100)) {
                        p1.setBackground(Color.red);
                        p1.repaint();
                    } else if(xInit == i / 100 && yInit == j / 100){
                        p1.setBackground(Color.blue);
                        p1.repaint();
                    }
                }
            }

        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (chessPiece == null) {
            return;
        }

        chessPiece.setVisible(false);
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JLabel) {
            c = c.getParent();
        }

        xFinal = c.getX() / 100;
        yFinal = c.getY() / 100;
        controler.move(new Coord(xInit, yInit), new Coord(xFinal, yFinal));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (chessPiece == null) {
            return;
        }
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void update(Observable o, Object arg) {
        List<PieceIHM> listePiece = (LinkedList) arg;

        if (chessPiece != null) {
            layeredPane.remove(chessPiece);
        }

        for (int i = 0; i < chessBoard.getComponentCount(); i++) {
            ((JPanel) (chessBoard.getComponent(i))).removeAll();
            JPanel square = (JPanel) (chessBoard.getComponent(i));

            int row = (i / 8) % 2;
            if (row == 0) {
                square.setBackground(i % 2 == 0 ? Color.black : Color.white);
            } else {
                square.setBackground(i % 2 == 0 ? Color.white : Color.black);
            }
        }

        for (PieceIHM p : listePiece) {
            List<Coord> listCoord = p.getList();
            for (Coord c : listCoord) {
                JLabel piece = new JLabel(new ImageIcon(ChessImageProvider.getImageFile(p.getTypePiece(), p.getCouleur())));
                int pos = (8 * c.y) + c.x;
                JPanel panel = (JPanel) chessBoard.getComponent(pos);
                panel.removeAll();
                panel.add(piece);
                panel.revalidate();
                panel.repaint();
            }
        }
        chessBoard.repaint();
    }

}
