package ca.etsmtl.pegsolitaire;

public class Solver {
    private Puzzle puzzle   = null;
    private Evoker evoker   = null;

    public Solver(Puzzle puzzle) {
        this.puzzle = puzzle;
        this.evoker = Evoker.getEvoker();
    }

    public boolean findSolution(int moveCtr) {
        for(int x = 0; moveCtr <= 31 && x < 7; x++) {
            for(int y = 0; y < 7; y++) {
                for(int direction : puzzle.getDirections()) {
                    if(evoker.execute(new Move(puzzle, x, y, direction))) {
                        if( ! (moveCtr >= 31 && evoker.getPegsLeft() == 1) ) {
                            if(findSolution(moveCtr + 1)) {
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
