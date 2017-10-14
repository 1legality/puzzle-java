package ca.etsmtl.pegsolitaire;

import java.util.Stack;

public class Evoker {

    private static Evoker         evoker    = null;
    private static Stack<Command> executed  = new Stack<Command>();
    private static int            pegsLeft  = 0;

    /**
     * Constructor
     */
    private Evoker() {}

    /**
     * @return Evoker instance
     */
    public static Evoker getEvoker() {
        if( evoker == null) {
            evoker = new Evoker();
        }

        return evoker;
    }

    /**
     * Sets number of pegs on board
     * @param numberOfPegs number of pegs
     */
    public void setPegs(int numberOfPegs) {
        pegsLeft = numberOfPegs;
    }

    /**
     * @return number of pegs left on board
     */
    public int getPegsLeft() {
        return pegsLeft;
    }

    /**
     * Executes commands
     * @param pCommand command to execute
     * @return true if executed successfully
     */
    public static boolean execute(Command pCommand) {
        if(pCommand.execute()) {
            executed.push(pCommand);
            pegsLeft--;
            return true;
        }

        return false;
    }

    /**
     * Undoes executed commands
     */
    public static void undo() {
        if(executed.isEmpty()) {
            return;
        }

        Command last = executed.peek();
        last.undo();
        executed.pop();
        pegsLeft++;

    }
}
