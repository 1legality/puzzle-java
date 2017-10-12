package ca.etsmtl.pegsolitaire;

import java.io.*;

public class Helpers {

    /**
     * Read puzzle file
     *
     * @param pReader character stream reader
     * @return puzzle 2D array
     */
    public int[][] readPuzzleFile(Reader pReader) {

        int[][] puzzleBoard = new int[7][7];

        try {
            BufferedReader brPuzzle = new BufferedReader(pReader);

            String word;
            int line = 0;
            while ((word = brPuzzle.readLine()) != null) {
                int column = 0;
                for (char letter : word.toCharArray()) {
                    puzzleBoard[line][column] = Character.getNumericValue(letter);
                    column++;
                }
                line++;
            }

            return puzzleBoard;
        }
        catch (IOException err) {
            System.out.println(err.toString());
        }

        return null;
    }


}