package com.log320.tp2.raulmarc;

public class Main {

    public static void main(String[] args) {
        Puzzle puzzle= new Puzzle();
        puzzle.run("test.puzzle");
    }


    void run(String puzzleURI) {
        readPuzzleFile(puzzleURI);
        boolean won = resolve();

        String result =  won ? "Congratulations!" : "Lost, " + pegs + " left on board";
        System.out.println(result);
        System.out.println(Arrays.deepToString(twoDimPuzzle).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

        // undo everything (test)
        Collections.reverse(moves);
        for (Move move : moves) {
            System.out.println(move.getPositionX() + ":" + move.getPositionY() + ":" + move.getDirection());
            undo(move);
        }
        System.out.println(Arrays.deepToString(twoDimPuzzle).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));

    }
}
