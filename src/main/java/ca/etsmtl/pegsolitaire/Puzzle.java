package ca.etsmtl.pegsolitaire;

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


    /**
     * @return direction constants
     */
    public int[] getDirections() {
        return this.DIRECTIONS;
    }

    /**
     * Used only for testing
     * @return up direction constant
     */
    public int goingUp() {
        return UP;
    }

    /**
     * Used only for testing
     * @return down direction constant
     */
    public int goingDown() {
        return DOWN;
    }

    /**
     * Used only for testing
     * @return left direction constant
     */
    public int goingLeft() {
        return LEFT;
    }

    /**
     * Used only for testing
     * @return right direction constant
     */
    public int goingRight() {
        return RIGHT;
    }


    /**
     * Makes sure that moves respects the rules of the game
     * @param x peg x coord
     * @param y peg y coord
     * @param direction direction of movement
     * @return true if movement is legal
     */
    private boolean isLegalMove(int x, int y, int direction) {

        int[] destCoords = this.computeDestinationCoords(x, y, direction);
        int destX = destCoords[0];
        int destY = destCoords[1];

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
    private void occupy(int x, int y) {
        this.puzzleArray[x][y] = OCCUPIED;
    }


    /**
     * @param x x coord to verify
     * @param y y coord to verify
     */
    private boolean isOccupied(int x, int y) {
        if(this.puzzleArray[x][y] == OCCUPIED)
            return true;

        return false;
    }


    /**
     * Removes peg from specified coord
     * @param x x coord
     * @param y y coord
     */
    private void vacate(int x, int y) {
        this.puzzleArray[x][y] = VACANT;
    }


    /**
     *
     * @param x
     * @param y
     */
    private boolean isVacant(int x, int y) {
        if(this.puzzleArray[x][y] == VACANT)
            return true;

        return false;
    }


    /**
     *
     * @param x
     * @param y
     * @return true if
     */
    private boolean isOutOfBounds(int x, int y) {
        if(this.puzzleArray[x][y] == OUT)
            return true;

        return false;
    }


    /**
     *
     * @param x
     * @param y
     * @param direction
     * @return
     */
    private boolean hasNeighbour(int x, int y, int direction) {

        switch (direction) {
            case UP:
                return isOccupied(x, y + 1);

            case DOWN:
                return isOccupied(x, y - 1);

            case LEFT:
                return isOccupied(x - 1, y);

            case RIGHT:
                return isOccupied(x + 1, y);
        }

        return false;
    }


    private int[] computeDestinationCoords(int x, int y, int direction) {

        switch (direction) {
            case UP:
                return new int [] { x, y + 2 };

            case DOWN:
                return new int [] { x, y - 2 };

            case LEFT:
                return new int [] { x - 2, y };

            case RIGHT:
                return new int [] { x + 2, y };
        }

        return null;
    }
}
