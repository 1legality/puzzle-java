package ca.etsmtl.pegsolitaire;

import java.io.*;

public class FileLoader {
    private int pegsInFile = 0;

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
                    int number = Character.getNumericValue(letter);
                    puzzleBoard[line][column] = number;
                    if(number == 1) {
                        this.pegsInFile++;
                    }
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

    /**
     * @return number of detected pegs
     */
    public int getNumberOfPegs() {
        return this.pegsInFile;
    }
}