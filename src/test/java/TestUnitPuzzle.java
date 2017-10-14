import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import ca.etsmtl.pegsolitaire.Puzzle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class TestUnitPuzzle {
    private static Puzzle   puzzle       = null;
    private static int[][]  puzzleArray  = new int[][] {
                                                          {0,0,1,1,1,0,0},
                                                          {0,0,2,2,1,0,0},
                                                          {1,2,2,1,1,1,1},
                                                          {1,2,1,2,1,1,1},
                                                          {1,1,1,1,1,1,1},
                                                          {0,0,1,1,1,0,0},
                                                          {0,0,1,1,1,0,0}
                                                       };

    @BeforeClass
    public static void setup() {
        puzzle = new Puzzle(puzzleArray);
    }

    @Test
    public void getDirection() {
        int[] directions = puzzle.getDirections();

        assertArrayEquals(new int[] {1,2,3,4}, directions);
    }

    @Test
    public void goingUp() {
        assertEquals(1, puzzle.goingUp());
    }

    @Test
    public void goingDown() {
        assertEquals(2, puzzle.goingDown());
    }

    @Test
    public void goingLeft() {
        assertEquals(3, puzzle.goingLeft());
    }

    @Test
    public void goingRight() {
        assertEquals(4, puzzle.goingRight());
    }

    @Test
    public void isLegalMove() {
        assertEquals(true, puzzle.isLegalMove(3, 5, puzzle.goingUp()));
    }

    @Test
    public void isNotLegalMoveUp() {
        // Has no neighbour and destination is not vacant
        assertEquals(false, puzzle.isLegalMove(3, 2, puzzle.goingUp()));
    }

    @Test
    public void isNotLegalMoveLeft() {
        // Has no neighbour, destination is vacant
        assertEquals(false, puzzle.isLegalMove(3, 2, puzzle.goingLeft()));
    }

    @Test
    public void isNotLegalMoveRight() {
        // Has neighbour, destination is not vacant
        assertEquals(false, puzzle.isLegalMove(3, 2, puzzle.goingRight()));

    }

    @Test
    public void isNotLegalMoveOutOfBounds() {
        // Destination is out of bounds
        assertEquals(false, puzzle.isLegalMove(3, 0, puzzle.goingRight()));
    }

    @Test
    public void computeDestinationUp() {
        int[] destinationCoords = puzzle.computeDestinationCoords(2,2, puzzle.goingUp());
        assertArrayEquals(new int[]{2, 0}, destinationCoords);
    }

    @Test
    public void computeDestinationDown() {
        int[] destinationCoords = puzzle.computeDestinationCoords(2,2, puzzle.goingDown());
        assertArrayEquals(new int[]{2, 4}, destinationCoords);
    }

    @Test
    public void computeDestinationLeft() {
        int[] destinationCoords = puzzle.computeDestinationCoords(2,2, puzzle.goingLeft());
        assertArrayEquals(new int[]{0, 2}, destinationCoords);
    }

    @Test
    public void computeDestinationRight() {
        int[] destinationCoords = puzzle.computeDestinationCoords(2,2, puzzle.goingRight());
        assertArrayEquals(new int[]{4, 2}, destinationCoords);
    }

    @Test
    public void computeNeighbourUp() {
        int[] neighbourCoords = puzzle.computeNeighbourCoords(2,2, puzzle.goingUp());
        assertArrayEquals(new int[]{2, 1}, neighbourCoords);
    }

    @Test
    public void computeNeighbourDown() {
        int[] neighbourCoords = puzzle.computeNeighbourCoords(2,2, puzzle.goingDown());
        assertArrayEquals(new int[]{2, 3}, neighbourCoords);
    }

    @Test
    public void computeNeighbourLeft() {
        int[] neighbourCoords = puzzle.computeNeighbourCoords(2,2, puzzle.goingLeft());
        assertArrayEquals(new int[]{1, 2}, neighbourCoords);
    }

    @Test
    public void computeNeighbourRight() {
        int[] neighbourCoords = puzzle.computeNeighbourCoords(2,2, puzzle.goingRight());
        assertArrayEquals(new int[]{3, 2}, neighbourCoords);
    }

    @Test
    public void isOccupied() {
        try {
            Method isOccupied = puzzle.getClass().getDeclaredMethod("isOccupied", int.class, int.class);
            isOccupied.setAccessible(true);

            assertEquals(true, isOccupied.invoke(puzzle, new Integer[]{2, 0}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isNotOccupied() {
        try {
            Method isOccupied = puzzle.getClass().getDeclaredMethod("isOccupied", int.class, int.class);
            isOccupied.setAccessible(true);

            assertEquals(false, isOccupied.invoke(puzzle, new Integer[]{2, 1}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isVacant() {
        try {
            Method isVacant = puzzle.getClass().getDeclaredMethod("isVacant", int.class, int.class);
            isVacant.setAccessible(true);

            assertEquals(true, isVacant.invoke(puzzle, new Integer[]{2, 1}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isNotVacant() {
        try {
            Method isVacant = puzzle.getClass().getDeclaredMethod("isVacant", int.class, int.class);
            isVacant.setAccessible(true);

            assertEquals(false, isVacant.invoke(puzzle, new Integer[]{2, 0}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isOutOfBounds() {
        try {
            Method isOutOfBounds = puzzle.getClass().getDeclaredMethod("isOutOfBounds", int.class, int.class);
            isOutOfBounds.setAccessible(true);

            assertEquals(true, isOutOfBounds.invoke(puzzle, new Integer[]{0, 0}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void isNotOutOfBounds() {
        try {
            Method isOutOfBounds = puzzle.getClass().getDeclaredMethod("isOutOfBounds", int.class, int.class);
            isOutOfBounds.setAccessible(true);

            assertEquals(false, isOutOfBounds.invoke(puzzle, new Integer[]{2, 0}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void hasNoHeighbourUp() {
        try {
            Method hasNeighbour = puzzle.getClass().getDeclaredMethod("hasNeighbour", int.class, int.class, int.class);
            hasNeighbour.setAccessible(true);

            assertEquals(false, hasNeighbour.invoke(puzzle, new Integer[]{3, 2, puzzle.goingUp()}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void hasNoHeighbourDown() {
        try {
            Method hasNeighbour = puzzle.getClass().getDeclaredMethod("hasNeighbour", int.class, int.class, int.class);
            hasNeighbour.setAccessible(true);

            assertEquals(false, hasNeighbour.invoke(puzzle, new Integer[]{3, 2, puzzle.goingDown()}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void hasNoHeighbourLeft() {
        try {
            Method hasNeighbour = puzzle.getClass().getDeclaredMethod("hasNeighbour", int.class, int.class, int.class);
            hasNeighbour.setAccessible(true);

            assertEquals(false, hasNeighbour.invoke(puzzle, new Integer[]{3, 2, puzzle.goingLeft()}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void hasHeighbourRight() {
        try {
            Method hasNeighbour = puzzle.getClass().getDeclaredMethod("hasNeighbour", int.class, int.class, int.class);
            hasNeighbour.setAccessible(true);

            assertEquals(true, hasNeighbour.invoke(puzzle, new Integer[]{3, 2, puzzle.goingRight()}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
