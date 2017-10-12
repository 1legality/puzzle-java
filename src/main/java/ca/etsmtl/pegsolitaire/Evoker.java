package ca.etsmtl.pegsolitaire;

import java.util.Stack;

public class Evoker {

    private static Evoker         evoker    = null;
    private final  Stack<Command> executed  = new Stack<Command>();

    /**
     * Constructor
     */
    private Evoker() {}

    public static Evoker getEvoker() {
        if( evoker == null) {
            evoker = new Evoker();
        }

        return evoker;
    }

    public void execute(Command pCommand) {
        pCommand.execute();
        this.executed.push(pCommand);
    }

    public void undo(Command pCommand) {
        if(executed.isEmpty()) {
            return;
        }

        Command last = this.executed.peek();
        last.undo();
        this.executed.pop();
    }
}
