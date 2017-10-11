package com.log320.tp2.raulmarc;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

class Puzzle {
    private int [][] twoDimPuzzle = new int [7][7];
    private ArrayList<Move> moves = new ArrayList<>();

    void run(String puzzleURI) {
        readPuzzleFile(puzzleURI);
        boolean won = resolve();

        System.out.println("won? : " + won);
        System.out.println(Arrays.deepToString(twoDimPuzzle));

        for (Move move : moves) {
            System.out.println(move.getPositionX() + ":" + move.getPositionY() + ":" + move.getDirection());
        }
    }

    private void readPuzzleFile(String puzzleURI) {
        try {
            BufferedReader brWords = new BufferedReader(new InputStreamReader(new FileInputStream(puzzleURI), "UTF8"));

            String word;
            int line = 0;
            while ((word = brWords.readLine()) != null) {
                int column = 0;
                for (char letter : word.toCharArray()) {
                    twoDimPuzzle[line][column] = Character.getNumericValue(letter);
                    column++;
                }
                line++;
            }

            System.out.println(Arrays.deepToString(twoDimPuzzle));
        }
        catch (IOException err) {
            System.out.println(err.toString());
        }
    }

    private boolean findNextMove(int positionX, int positionY) {
        // use Konami order : up, down, left, right
        if (positionX >= 2 &&
            twoDimPuzzle[positionX - 1][positionY] == 1 && twoDimPuzzle[positionX - 2][positionY] == 1) {
            // move up
            twoDimPuzzle[positionX - 1][positionY] = 2;
            twoDimPuzzle[positionX - 2][positionY] = 2;
            moves.add(new Move(positionX, positionY, Move.DirectionEnum.UP));
            return true;
        }
        else if (positionX <= 4 &&
                 twoDimPuzzle[positionX + 1][positionY] == 1 && twoDimPuzzle[positionX + 2][positionY] == 1) {
            // move down
            twoDimPuzzle[positionX + 1][positionY] = 2;
            twoDimPuzzle[positionX + 2][positionY] = 2;
            moves.add(new Move(positionX, positionY, Move.DirectionEnum.RIGHT));

            return true;

        }
        else if (positionY <= 4 &&
                 twoDimPuzzle[positionX][positionY + 1] == 1 && twoDimPuzzle[positionX][positionY + 2] == 1) {
            // move left
            twoDimPuzzle[positionX][positionY + 1] = 2;
            twoDimPuzzle[positionX][positionY + 2] = 2;
            moves.add(new Move(positionX, positionY, Move.DirectionEnum.LEFT));

            return true;

        }
        else if (positionY >= 2 &&
                 twoDimPuzzle[positionX][positionY - 1] == 1 && twoDimPuzzle[positionX][positionY - 2] == 1) {
            // move right
            twoDimPuzzle[positionX][positionY - 1] = 2;
            twoDimPuzzle[positionX][positionY - 2] = 2;
            moves.add(new Move(positionX, positionY, Move.DirectionEnum.RIGHT));
            return true;

        }
        else {
            // There is no move left for this position
            return false;
        }
    }

    private boolean resolve() {
        // TODO : create recursive function to resolve puzzle
        int ballsLeft = 0;
        for (int i = 0; i < 7; i++) {
            for (int n = 0; n < 7; n++) {
                if (twoDimPuzzle[i][n] == 2) {
                    System.out.println("trying position " + i + ":" + n);
                    if (findNextMove(i, n)) {
                        resolve();
                    }
                }
                else if (twoDimPuzzle[i][n] == 1)
                    ballsLeft++;
            }
        }

        if (ballsLeft == 1) {
            return true;
        }
        else {
            return false;
        }
    }
}
