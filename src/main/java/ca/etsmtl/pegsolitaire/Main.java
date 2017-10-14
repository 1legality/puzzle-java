package ca.etsmtl.pegsolitaire;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        // create Loader instance
        FileLoader loader = new FileLoader();

        try {
            Reader fileReader = new InputStreamReader(new FileInputStream("./src/main/resources/test.puzzle"), "UTF8");
            int[][] puzzleArray = loader.readPuzzleFile(fileReader);

            Evoker evoker = Evoker.getEvoker();
            evoker.setPegs(loader.getNumberOfPegs());

            Puzzle puzzle = new Puzzle(puzzleArray);
            Solver solver = new Solver(puzzle);

            long start = System.nanoTime();
            if(solver.findSolution(1)){
                long stop = System.nanoTime();

                System.out.println("Success!");
                System.out.println("Nodes: " + evoker.getNodes());
                System.out.println("Time to solve: " + ((stop - start) / 1000000) + " ms");
                puzzle.printPuzzle();
            } else {
                System.out.println("Can not solve puzzle");
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        // load file
        // create puzzle instance (pass board array)
        // create solver instance (pass puzzle
    }

/*
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
*/
}
