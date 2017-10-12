package com.log320.tp2.raulmarc;

public class Move {
    private int positionX;
    private int positionY;
    private String direction;

    private int [][] puzzleState = new int [7][7];
    public static enum DirectionEnum {UP, DOWN, LEFT, RIGHT};

    Move(int positionX, int positionY, DirectionEnum direction, int[][] puzzleState) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction.toString();
        this.puzzleState = puzzleState;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction.toString();
    }

    public int[][] getPuzzleState() {
        return puzzleState;
    }

    public void setPuzzleState(int[][] puzzleState) {
        this.puzzleState = puzzleState;
    }
}
