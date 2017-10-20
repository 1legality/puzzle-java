import org.junit.platform.runner.JUnitPlatform;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;


import ca.etsmtl.pegsolitaire.Move;
import ca.etsmtl.pegsolitaire.Puzzle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(JUnitPlatform.class)
public class TestUnitMoveUndo {
    private static Puzzle   puzzle       = null;
    private static Move     movePeg      = null;
    private static boolean  successful   = false;
    private static int[][]  puzzleArray  = new int[][] {
            {0,0,1,1,1,0,0},
            {0,0,2,2,1,0,0},
            {1,2,2,1,1,1,1},
            {1,2,1,2,1,1,1},
            {1,1,1,1,1,1,1},
            {0,0,1,1,1,0,0},
            {0,0,1,1,1,0,0}
    };

    @BeforeAll
    public static void setup() {
        puzzle      = new Puzzle(puzzleArray);
        movePeg     = new Move(puzzle, 3, 5, puzzle.goingUp());
        successful  = movePeg.execute();
    }

    @DisplayName("Test successful move")
    @Test
    void testSuccessfulMovePeg() {
        assertTrue(successful);
    }

    @Nested
    @DisplayName("Testing move undo feature")
    class MoveUndoFeature {

        @Test
        void sourceIsOccupied() {
            try {
                Method isOccupied = puzzle.getClass().getDeclaredMethod("isOccupied", int.class, int.class);
                isOccupied.setAccessible(true);

                assertEquals(true, isOccupied.invoke(puzzle, new Integer[]{3, 5}));

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        @Test
        void neighbourIsOccupied() {
            int[] neighbourCoords = puzzle.computeNeighbourCoords(3, 5, puzzle.goingUp());
            int neighbourX = neighbourCoords[0];
            int neighbourY = neighbourCoords[1];

            try {
                Method isOccupied = puzzle.getClass().getDeclaredMethod("isOccupied", int.class, int.class);
                isOccupied.setAccessible(true);

                assertEquals(true, isOccupied.invoke(puzzle, new Integer[]{neighbourX, neighbourY}));

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        @Test
        void destinationIsVacant() {
            int[] destinationCoords = puzzle.computeDestinationCoords(3, 5, puzzle.goingUp());
            int destinationX = destinationCoords[0];
            int destinationY = destinationCoords[1];

            try {
                Method isVacant = puzzle.getClass().getDeclaredMethod("isVacant", int.class, int.class);
                isVacant.setAccessible(true);

                assertEquals(true, isVacant.invoke(puzzle, new Integer[]{destinationX, destinationY}));

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

}
