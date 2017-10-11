package com.log320.tp2.raulmarc;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Puzzle {
    private int pegs = 0;
    private int [][] twoDimPuzzle = new int [7][7];
    private ArrayList<Move> moves = new ArrayList<>();

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

    private void readPuzzleFile(String puzzleURI) {
        try {
            BufferedReader brWords = new BufferedReader(new InputStreamReader(new FileInputStream(puzzleURI), "UTF8"));

            String word;
            int line = 0;
            while ((word = brWords.readLine()) != null) {
                int column = 0;
                for (char letter : word.toCharArray()) {
                    if (Character.getNumericValue(letter) == 1) { pegs++; }
                    twoDimPuzzle[line][column] = Character.getNumericValue(letter);
                    column++;
                }
                line++;
            }
        }
        catch (IOException err) {
            System.out.println(err.toString());
        }
    }

    private boolean findNextMove(int positionX, int positionY) {
        // use Konami order : up, down, left, right
        if (positionY <= 4 &&
                twoDimPuzzle[positionX][positionY + 1] == 1 &&
                twoDimPuzzle[positionX][positionY + 2] == 2) {
            // move UP
            twoDimPuzzle[positionX][positionY + 1] = 2;
            twoDimPuzzle[positionX][positionY + 2] = 1;
            twoDimPuzzle[positionX][positionY] = 2;
            moves.add(new Move(positionX, positionY, Move.DirectionEnum.UP));
            pegs--;
            return true;
        }
        else if (positionY >= 2 &&
                    twoDimPuzzle[positionX][positionY - 1] == 1 &&
                    twoDimPuzzle[positionX][positionY - 2] == 2) {
            // move DOWN
            twoDimPuzzle[positionX][positionY - 1] = 2;
            twoDimPuzzle[positionX][positionY - 2] = 1;
            twoDimPuzzle[positionX][positionY] = 2;
            pegs--;
            moves.add(new Move(positionX, positionY, Move.DirectionEnum.DOWN));
            return true;
        }
        else if (positionX <= 4 &&
                    twoDimPuzzle[positionX + 1][positionY] == 1 &&
                    twoDimPuzzle[positionX + 2][positionY] == 2) {
            // move LEFT
            twoDimPuzzle[positionX + 1][positionY] = 2;
            twoDimPuzzle[positionX + 2][positionY] = 1;
            twoDimPuzzle[positionX][positionY] = 2;
            moves.add(new Move(positionX, positionY, Move.DirectionEnum.LEFT));
            pegs--;
            return true;
        }
        else if (positionX >= 2 &&
                twoDimPuzzle[positionX - 1][positionY] == 1 &&
                twoDimPuzzle[positionX - 2][positionY] == 2) {
            // move RIGHT
            twoDimPuzzle[positionX - 1][positionY] = 2;
            twoDimPuzzle[positionX - 2][positionY] = 1;
            twoDimPuzzle[positionX][positionY] = 2;
            moves.add(new Move(positionX, positionY, Move.DirectionEnum.RIGHT));
            pegs--;
            return true;
        }
        else {
            // There is no move left for this position
            return false;
        }
    }

    private void undo(Move move) {
        if (move.getDirection().equals(Move.DirectionEnum.UP.toString())) {
            twoDimPuzzle[move.getPositionX()][move.getPositionY() + 1] = 1;
            twoDimPuzzle[move.getPositionX()][move.getPositionY() + 2] = 2;
            twoDimPuzzle[move.getPositionX()][move.getPositionY()] = 1;
        }
        else if (move.getDirection().equals(Move.DirectionEnum.DOWN.toString())) {
            twoDimPuzzle[move.getPositionX()][move.getPositionY() - 1] = 1;
            twoDimPuzzle[move.getPositionX()][move.getPositionY() - 2] = 2;
            twoDimPuzzle[move.getPositionX()][move.getPositionY()] = 1;
        }
        else if (move.getDirection().equals(Move.DirectionEnum.LEFT.toString())) {
            twoDimPuzzle[move.getPositionX() + 1][move.getPositionY()] = 1;
            twoDimPuzzle[move.getPositionX() + 2][move.getPositionY()] = 2;
            twoDimPuzzle[move.getPositionX()][move.getPositionY()] = 1;
        }
        else if (move.getDirection().equals(Move.DirectionEnum.RIGHT.toString())) {
            twoDimPuzzle[move.getPositionX() - 1][move.getPositionY()] = 1;
            twoDimPuzzle[move.getPositionX() - 2][move.getPositionY()] = 2;
            twoDimPuzzle[move.getPositionX()][move.getPositionY()] = 1;
        }
    }

    private boolean resolve() {
        // TODO : create recursive function to resolve puzzle
        int pegsLeft = 0;
        for (int i = 0; i < 7; i++) {
            for (int n = 0; n < 7; n++) {
                if (twoDimPuzzle[i][n] == 1) {
                    if (findNextMove(i, n)) {
                        resolve();
                    }
                }
                else if (twoDimPuzzle[i][n] == 1)
                    pegsLeft++;
            }
        }

        if (pegsLeft == 1) {
            return true;
        }
        else {
            return false;
        }
    }
}
