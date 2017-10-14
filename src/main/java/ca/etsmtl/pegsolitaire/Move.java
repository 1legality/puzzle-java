package ca.etsmtl.pegsolitaire;

public class Move implements Command{
    private Puzzle puzzle;
    private int    initX,
                   initY,
                   direction;

    /**
     * Constructor
     * @param puzzle puzzle instance
     * @param x initial x coord
     * @param y initial y coord
     * @param direction desired move direction
     */
    public Move(Puzzle puzzle, int x, int y, int direction) {
        this.puzzle    = puzzle;
        this.initX     = x;
        this.initY     = y;
        this.direction = direction;
    }

    /**
     * Moves peg to destination
     * @return true if operation succeeded
     */
    public boolean execute() {
        if(this.puzzle.isLegalMove(initX, initY, direction)) {
            // vacate starting point
            this.puzzle.vacate(initX, initY);

            // occupy destination
            int[] destinationCoords = this.puzzle.computeDestinationCoords(initX, initY, direction);
            int destinationX = destinationCoords[0];
            int destinationY = destinationCoords[1];

            this.puzzle.occupy(destinationX, destinationY);

            // vacate neighbour
            int[] neighbourCoords = this.puzzle.computeNeighbourCoords(initX, initY, direction);
            int neighbourX = neighbourCoords[0];
            int neighboury = neighbourCoords[1];

            this.puzzle.vacate(neighbourX, neighboury);

            return true;
        }

        return false;
    }

    /**
     * Moves peg back to source
     * @return true if operation succeeded
     */
    public boolean undo() {
        // occupy starting point
        this.puzzle.occupy(initX, initY);

        // vacate destination
        int[] destinationCoords = this.puzzle.computeDestinationCoords(initX, initY, direction);
        int destinationX = destinationCoords[0];
        int destinationY = destinationCoords[1];

        this.puzzle.vacate(destinationX, destinationY);

        // occupy neighbour
        int[] neighbourCoords = this.puzzle.computeNeighbourCoords(initX, initY, direction);
        int neighbourX = neighbourCoords[0];
        int neighbourY = neighbourCoords[1];

        this.puzzle.occupy(neighbourX, neighbourY);

        return true;
    }
}
