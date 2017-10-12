package java;

public class Move {
    private int positionX;
    private int positionY;
    private String direction;
    public static enum DirectionEnum {UP, DOWN, LEFT, RIGHT};

    Move(int positionX, int positionY, DirectionEnum direction) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction.toString();
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
}
