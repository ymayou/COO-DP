package model;

/**
 * @author francoise.perrin * Inspiration Jacques SARAYDARYAN, Adrien GUENARD
 */
public class Roi extends AbstractPiece {

    /**
     * @param name
     * @param couleur_de_piece
     * @param coord
     */
    private boolean roque;

    public Roi(String name, Couleur couleur_de_piece, Coord coord) {
        super(name, couleur_de_piece, coord);
        this.roque = true;
    }

    public void roqued() {
        this.roque = false;
    }

    @Override
    public boolean isMoveOk(int xFinal, int yFinal) {
        boolean ret = false;

        if ((Math.abs(yFinal - this.getY()) <= 1)
                && (Math.abs(xFinal - this.getX()) <= 1)) {
            ret = true;
        } else if ((Math.abs(yFinal - this.getY()) == 0)
                && ((xFinal == this.getX() + 2 || xFinal == this.getX() + 3) || (xFinal == this.getX() - 2 || xFinal == this.getX() - 3))
                && this.roque) {
            ret = true;
        }
        return ret;
    }

}
