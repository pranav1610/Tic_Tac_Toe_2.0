public class Board
{
    //BOARD_SIZE - stores the size of the BOARD - (playerCount + 1)
    private static int BOARD_SIZE;

    //BOARD - stores the string representation of the BOARD
    private static String[][] BOARD;

    /**
     * Method getBoardSize - returns the size of the BOARD
     * @return - the size of the BOARD -- (playerCount + 1)
     */
    public static int getBoardSize()
    {
        return BOARD_SIZE;
    }


    /**
     * Method getBOARD returns the BOARD
     * @return returns the Tic-Tac-Toe Board
     */
    public static String[][] getBOARD()
    {
        return BOARD;
    }

    /**
     * Method initBoard - initializes all the spots on the BOARD to a space
     */
    public static void initBoard()
    {
        BOARD = new String[BOARD_SIZE][BOARD_SIZE];
        for(int i=0;i<BOARD_SIZE;i++)
        {
            for(int j=0;j<BOARD_SIZE;j++)
            {
                BOARD[i][j] = " ";
            }
        }
    }

    /**
     * Method isFilled returns if the BOARD is filled so the game is a tie!
     * @return true if all spots on BOARD have been visited/played, false otherwise
     */
    public static boolean isFilled()
    {
        for(int i=0;i<BOARD.length;i++)
        {
            for(int j=0;j<BOARD.length;j++)
            {
                if(BOARD[i][j].equals(" "))
                {
                    return false;
                }
            }
        }

        return true;
    }

    //Static or NOT??
    public static void setBoardSize(int playercount)
    {
        BOARD_SIZE = playercount+1;
    }

    /**
     * Method updateBoard updates the board with the input character at the specified location
     * @param row - row where character needs to be placed
     * @param col - col where character needs to be placed
     * @param character - the character to be placed
     */
    public static void updateBoard(int row, int col, String character)
    {
        BOARD[row][col] = character;
        displayBoard();
    }

    /**
     * Method displayBoard - displays the board
     */
    public static void displayBoard()
    {
        System.out.print("  |");
        for(int i=0;i<BOARD_SIZE;i++)
        {
            System.out.print(i+" |");
        }
        System.out.println();
        for(int i=0;i<(BOARD_SIZE*3+3);i++)
        {
            System.out.print("-");
        }
        System.out.println();
        for(int i=0;i<BOARD_SIZE;i++)
        {
            System.out.print(i+" |");
            for(int j=0;j<BOARD_SIZE;j++)
            {
                System.out.print(BOARD[i][j]+" |");
            }
            System.out.println();
            for(int divider=0;divider<(BOARD_SIZE+1);divider++)
            {
                System.out.print("-  ");
            }
            System.out.println();
        }
    }



}
