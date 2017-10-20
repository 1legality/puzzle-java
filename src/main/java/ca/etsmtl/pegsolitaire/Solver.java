package ca.etsmtl.pegsolitaire;

public class Solver {
    private Puzzle puzzle   = null;
    private Evoker evoker   = null;

    public Solver(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.evoker = Evoker.getEvoker();
    }

    public boolean findSolution(int pegCtr) {
        for(int x = 0; x < 7 && pegCtr > 0; x++) {
            for(int y = 0; y < 7; y++) {
                for(int direction : puzzle.getDirections()) {
                    if(evoker.execute(new Move(puzzle, x, y, direction))) {
                        if( !(evoker.getPegsLeft() == 1) ) {
                            if(findSolution(pegCtr - 1)) {
                                return true;
                            } else {
                                Evoker.undo();
                            }
                        } else {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
