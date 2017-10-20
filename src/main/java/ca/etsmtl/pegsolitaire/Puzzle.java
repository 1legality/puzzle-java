package ca.etsmtl.pegsolitaire;

import java.util.Arrays;

public class Puzzle {
    private                int [][]  puzzleArray = new int [7][7];

    private  static final  int       OUT         = 0,
                                     OCCUPIED    = 1,
                                     VACANT      = 2,
                                     UP          = 1,
                                     DOWN        = 2,
                                     LEFT        = 3,
                                     RIGHT       = 4;

    private static final  int []     DIRECTIONS  = new int [] { UP,
                                                                DOWN,
                                                                LEFT,
                                                                RIGHT };

    /**
     * Constructor
     *
     * @param pBoardArray the puzzle board 2D array
     */
    public Puzzle(int[][] pBoardArray) {
        this.puzzleArray = pBoardArray;
    }


    public int[][] getBoard() {
        return this.puzzleArray;
    }

    /**
     * @return direction constants
     */
    public int[] getDirections() {
        return this.DIRECTIONS;
    }

    /**
     * @return up direction constant
     */
    public int goingUp() {
        return UP;
    }

    /**
     * @return down direction constant
     */
    public int goingDown() {
        return DOWN;
    }

    /**
     * @return left direction constant
     */
    public int goingLeft() {
        return LEFT;
    }

    /**
     * @return right direction constant
     */
    public int goingRight() {
        return RIGHT;
    }


    /**
     * Computes peg destination coords
     * @param x initial x coord
     * @param y initial y coord
     * @param direction desired move direction
     * @return destination coords
     */
    public int[] computeDestinationCoords(int x, int y, int direction) {

        switch (direction) {
            case UP:
                return new int [] { x, y - 2 };

            case DOWN:
                return new int [] { x, y + 2 };

            case LEFT:
                return new int [] { x - 2, y };

            case RIGHT:
                return new int [] { x + 2, y };
        }

        return null;
    }


    /**
     * Computes neighbour coords
     * @param x initial x coord
     * @param y initial y coord
     * @param direction desired neighbour direction
     * @return neighbour coords
     */
    public int[] computeNeighbourCoords(int x, int y, int direction) {

        switch (direction) {
            case UP:
                return new int [] { x, y - 1 };

            case DOWN:
                return new int [] { x, y + 1 };

            case LEFT:
                return new int [] { x - 1, y };

            case RIGHT:
                return new int [] { x + 1, y };
        }

        return null;
    }


    /**
     * Makes sure that moves respects the rules of the game
     * @param x peg x coord
     * @param y peg y coord
     * @param direction direction of movement
     * @return true if movement is legal
     */
    public boolean isLegalMove(int x, int y, int direction) {

        int[] destCoords = this.computeDestinationCoords(x, y, direction);
        int destX = destCoords[0];
        int destY = destCoords[1];

        // Can't move out of the board
        if (destX > 6 || destY > 6 || destX < 0 || destY < 0) {
            return false;
        }

        // Can't move if no peg is present
        if(!this.isOccupied(x, y))
            return false;

        // Can't move if destination is occupied
        if(this.isOccupied(destX, destY))
            return false;

        // Can't move if there is no neighbour peg
        if(!this.hasNeighbour(x, y, direction))
            return false;

        // Cant't move if destination is out of bounds
        if(this.isOutOfBounds(destX, destY))
            return false;

        // Rules of the game are respected
        return true;
    }


    /**
     * Position peg to specified coords
     * @param x x coord
     * @param y y coord
     */
    public void occupy(int x, int y) {
        this.puzzleArray[x][y] = OCCUPIED;
    }


    /**
     * Removes peg from specified coord
     * @param x x coord
     * @param y y coord
     */
    public void vacate(int x, int y) {
        this.puzzleArray[x][y] = VACANT;
    }


    public void printPuzzle() {
        System.out.println(Arrays.deepToString(puzzleArray).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }


    /**
     * Makes sure position is occupied
     * @param x x coord to verify
     * @param y y coord to verify
     * @return true if position is occupied
     */
    public boolean isOccupied(int x, int y) {
        if(this.puzzleArray[x][y] == OCCUPIED)
            return true;

        return false;
    }


    /**
     * Makes sure position is vacant
     * @param x x coord
     * @param y y coord
     * @return true if position is vacant
     */
    private boolean isVacant(int x, int y) {
        if(this.puzzleArray[x][y] == VACANT)
            return true;

        return false;
    }


    /**
     * Makes sure the position is not out of bounds
     * @param x x coord
     * @param y y coord
     * @return true if position is out of bounds
     */
    private boolean isOutOfBounds(int x, int y) {
        if(this.puzzleArray[x][y] == OUT)
            return true;

        return false;
    }


    /**
     * Makes sure neighbouring position is occupied
     * @param x peg x coord
     * @param y peg y coord
     * @param direction desired neighbour direction
     * @return true if neighbour exists
     */
    private boolean hasNeighbour(int x, int y, int direction) {

        int[] neighbourCoords = this.computeNeighbourCoords(x, y, direction);
        int neighbourX = neighbourCoords[0];
        int neighbourY = neighbourCoords[1];

        return isOccupied(neighbourX, neighbourY);
    }
}
