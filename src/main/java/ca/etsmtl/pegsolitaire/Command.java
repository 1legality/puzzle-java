package ca.etsmtl.pegsolitaire;

public interface Command {

    /**
     * @return
     */
    boolean execute();

    /**
     * @return
     */
    int[][] undo();
}
