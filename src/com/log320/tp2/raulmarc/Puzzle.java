package com.log320.tp2.raulmarc;

import java.io.*;
import java.util.Arrays;

public class Puzzle {
    int [][] twoDimPuzzle = new int [7][7];

    void run(String puzzleURI) {
        readPuzzleFile(puzzleURI);
    }

    private void readPuzzleFile(String puzzleURI) {
        try {
            BufferedReader brWords = new BufferedReader(new InputStreamReader(new FileInputStream(puzzleURI), "UTF8"));

            String word;
            int line = 0;
            while ((word = brWords.readLine()) != null) {
                int column = 0;
                for (char letter : word.toCharArray()) {
                    System.out.println(letter);
                    twoDimPuzzle[line][column] = Character.getNumericValue(letter);
                    column++;
                }
                line++;
            }

            System.out.println(Arrays.deepToString(twoDimPuzzle));
        }
        catch (IOException err) {
            System.out.println(err);
        }
    }
}
