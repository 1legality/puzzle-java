import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import ca.etsmtl.pegsolitaire.Puzzle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class UnitTestPuzzle {
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
    public void hasHeighbour() {
        try {
            Method hasNeighbour = puzzle.getClass().getDeclaredMethod("hasNeighbour", int.class, int.class, int.class);
            hasNeighbour.setAccessible(true);

            assertEquals(true, hasNeighbour.invoke(puzzle, new Integer[]{2, 0, puzzle.goingRight()}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void doesNotHaveHeighbour() {
        try {
            Method hasNeighbour = puzzle.getClass().getDeclaredMethod("hasNeighbour", int.class, int.class, int.class);
            hasNeighbour.setAccessible(true);

            assertEquals(false, hasNeighbour.invoke(puzzle, new Integer[]{2, 0, puzzle.goingLeft()}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isLegalMove() {
        try {
            Method isLegalMove = puzzle.getClass().getDeclaredMethod("isLegalMove", int.class, int.class, int.class);
            isLegalMove.setAccessible(true);

            assertEquals(true, isLegalMove.invoke(puzzle, new Integer[]{3, 5, puzzle.goingDown()}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isNotLegalMove1() {
        // Has no neighbour and destination is not vacant
        try {
            Method isLegalMove = puzzle.getClass().getDeclaredMethod("isLegalMove", int.class, int.class, int.class);
            isLegalMove.setAccessible(true);

            assertEquals(false, isLegalMove.invoke(puzzle, new Integer[]{3, 2, puzzle.goingUp()}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isNotLegalMove2() {
        // Has no neighbour, destination is vacant
        try {
            Method isLegalMove = puzzle.getClass().getDeclaredMethod("isLegalMove", int.class, int.class, int.class);
            isLegalMove.setAccessible(true);

            assertEquals(false, isLegalMove.invoke(puzzle, new Integer[]{3, 2, puzzle.goingLeft()}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isNotLegalMove3() {
        // Has neighbour, destination is not vacant
        try {
            Method isLegalMove = puzzle.getClass().getDeclaredMethod("isLegalMove", int.class, int.class, int.class);
            isLegalMove.setAccessible(true);

            assertEquals(false, isLegalMove.invoke(puzzle, new Integer[]{3, 2, puzzle.goingRight()}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isNotLegalMove4() {
        // Has neighbour, destination is out of bounds
        try {
            Method isLegalMove = puzzle.getClass().getDeclaredMethod("isLegalMove", int.class, int.class, int.class);
            isLegalMove.setAccessible(true);

            assertEquals(false, isLegalMove.invoke(puzzle, new Integer[]{3, 0, puzzle.goingRight()}));

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
