/**
 * A class that represents a single cell of the maze.
 * @author Ms. Namasivayam, Kirin Debnath
 * @version 03/10/2023
 */

public class MazeCell {
    private boolean explored;
    private boolean isWall;
    private MazeCell parent;
    private int row;
    private int col;

    public MazeCell(int row, int col) {
        this.row = row;
        this.col = col;
        this.isWall = false;
        this.explored = false;
        this.parent = null;
    }

    /** Getters and Setters **/
    public boolean isExplored() {
        return this.explored;
    }

    public boolean isWall() {
        return this.isWall;
    }

    public MazeCell getParent() {
        return this.parent;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    public void setParent(MazeCell parent) {
        this.parent = parent;
    }

    public void setWall(boolean wall) {
        this.isWall = wall;
    }


    /**
     * returns current cells coordinates and parent cell coordinates
     * if no parent cell prints replaces parent cords w/ (null, null)
     * @return Formated information of current (x,y) and parent (x,y)
     **/
    @Override
    public String toString() {

        try {
            return "Cell: (" + this.getRow() + " , " + this.getCol() + ") Parent: (" + this.getParent().getRow() + " , " + this.getParent().getCol() + ")";
        }
        catch (NullPointerException e) {
            return "Cell: (" + this.getRow() + " , " + this.getCol() + ") Parent: (null,null)";
        }

    }
}
