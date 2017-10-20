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
            int pegsOnBoard = loader.getNumberOfPegs();
            evoker.setPegs(pegsOnBoard);

            Puzzle puzzle = new Puzzle(puzzleArray);
            Solver solver = new Solver(puzzle);

            long start = System.nanoTime();
            if(solver.findSolution(pegsOnBoard)){
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
    }
}
