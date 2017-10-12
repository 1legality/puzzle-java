class Helpers {

    /**
     * Read puzzle file
     * 
     * @param puzzleURI path to puzzle file
     * @return puzzle 2D array
     */
    private int[][] readPuzzleFile(String puzzleURI) {
        int[][] puzzleBoard = new int[7][7];

        try {
            BufferedReader brWords = new BufferedReader(new InputStreamReader(new FileInputStream(puzzleURI), "UTF8"));

            String word;
            int line = 0;
            while ((word = brWords.readLine()) != null) {
                int column = 0;
                for (char letter : word.toCharArray()) {
                    puzzleBoard[line][column] = Character.getNumericValue(letter);
                    column++;
                }
                line++;
            }

            return puzzleBoard
        }
        catch (IOException err) {
            System.out.println(err.toString());
        }

        return null;
    }
}