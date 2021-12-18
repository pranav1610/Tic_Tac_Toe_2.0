import java.util.Scanner;
import java.util.ArrayList;
public class Player
{

    //playerCharacter - player's play character
    private String playerCharacter;


    //characterLocations - stores the locations of characters placed by a player
    // eg. characterLocations.get(0), characterLocations.get(1)
    // --> would be (row,col) pair for first character placement
    private ArrayList<Integer> characterLocations;

    /**
     * Constructor - initializes default playCharacter to empty string and
     *               characterLocations to an empty ArrayList
     */
    public Player()
    {
        playerCharacter = "";

        characterLocations = new ArrayList<Integer>();
    }


    /**
     * Method getPlayerCharacter returns the player's play character when called
     * @return playerCharacter - the player's play character as a String
     */
    public String getPlayerCharacter()
    {
        return playerCharacter;
    }

    /**
     * getCharacterLocations - returns the integer ArrayList characterLocations
     * @return - integer ArrayList characterLocations
     */
    public ArrayList<Integer> getCharacterLocations()
    {
        return characterLocations;
    }


    /**
     * Method setPlayerCharacter sets the player's character and validates so that
     * every player has a unique character not repeated. The method also updates the
     * characters list with valid characters at respective playerIndex
     * @param playerIndex - the player's index eg. player 1's index = 0
     * @param charactersList - the arraylist of characters used until now
     *
     */
    public void setPlayerCharacter(int playerIndex, ArrayList<String> charactersList)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter player " + (playerIndex+1) + "\'s play character:");

        String character = input.nextLine();

        //Character input

        //For valid inputs - not a space, 1 character in length and not used by previous players,
        //accept the character and add to the charactersList to check for duplicacy later
        if(isCharacter(character) && !isSpace(character) && !charactersList.contains(character))
        {
            playerCharacter = character;
        }
        //Add the character to the list only when it is neither a space or more than 1 character in
        //length, nor used by another player!
        else
        {
            playerCharacter = getUnusedCharacter(charactersList,character);
        }

        //Add the valid character to characterList to avoid duplicacy
        charactersList.add(playerIndex, playerCharacter);

    }


    /**
     * Method isCharacter checks if the entered character is only 1 character in length
     * @param character - to be checked for length=1
     * @return true if string character's length is 1
     */
    private boolean isCharacter(String character)
    {
        return character.length()==1;
    }

    /**
     * Method isSpace checks if entered character is a space or not
     * @param character - to be checked if a space
     * @return true if character is a space, false otherwise
     */
    private boolean isSpace(String character)
    {
        return character.equals(" ");
    }

    /**
     * Method getUnusedCharacter prompts and returns a valid character - not used by any other player,
     * not a space, and not more than 1 character long!
     * @param charactersList - stores the list of characters chosen by previous players
     * @param character - the character that is chosen by the player, that is returned
     * @return character - chosen by a player
     */
    private String getUnusedCharacter(ArrayList<String> charactersList, String character)
    {

        Scanner input = new Scanner(System.in);

        do {
            if(!isCharacter(character))
            {
                System.out.println("The character must be only 1 character in length!");
            }
            else if(isSpace(character))
            {
                System.out.println("The character must not be a space!");
            }
            else if(charactersList.contains(character))
            {
                System.out.println("Character \'"+ character + "\' is already used by another player!");
            }
            System.out.print("Please enter a character(not a space or more than 1 character): ");
            character = input.nextLine();
        }
        while (!isCharacter(character) || isSpace(character) || charactersList.contains(character));

        //Returns the player's play character!
        return character;
    }

    /**
     * Method playTurn controls the play or turn of a player, accessed by his/her index
     * @param playerIndex - player's index who has the turn
     */
    public void playTurn(int playerIndex)
    {
        int row = 0;
        int col = 0;

        Scanner input = new Scanner(System.in);
        do {
            System.out.println("Player " + (playerIndex+1) + " enters: ");

            //Enter row
            System.out.print("row: ");
            row = getIntegerNum("row");

            //Enter col
            System.out.print("col: ");
            col = getIntegerNum("col");


            //For invalid turns, enter again!
            if(!isValidTurn(Board.getBOARD(), row,col))
            {
                printErrorForIndex(Board.getBOARD(), row,col);

                System.out.println("Player " + (playerIndex+1) + " enters: ");

                //Enter row
                System.out.print("row: ");
                row = getIntegerNum("row");

                //Enter col
                System.out.print("col: ");
                col = getIntegerNum("col");

                if(!isValidTurn(Board.getBOARD(), row,col))
                    printErrorForIndex(Board.getBOARD(),row,col);

            }
            /*
            else
            {
                printErrorForIndex(Board.getBOARD(),row,col);
            }
            */
        }
        while(!isValidTurn(Board.getBOARD(), row,col));

        //Board updated after getting the right input!
        Board.updateBoard(row, col, playerCharacter);

        //(row,col) pair added as 2 individual elements in characterLocations
        characterLocations.add(row);
        characterLocations.add(col);

    }

    /**
     * Method getIntegerNum returns an integer
     * @param str - defines what integer represents eg. "row" if num returns a row
     * @return the integer to be returned
     */
    private int getIntegerNum(String str)
    {
        //stringNumber - the string representation of num
        String stringNumber;
        Scanner input = new Scanner(System.in);
        stringNumber = input.nextLine();

        if(stringNumber.equals("10") && Board.getBOARD().length==11)
        {
            return Integer.parseInt(stringNumber);
        }

        while(stringNumber.length()!=1 || !Character.isDigit(stringNumber.charAt(0)) )
        {
            //10 is a valid input for 10 players!
            if(stringNumber.equals("10") && Board.getBOARD().length==11)
            {
                return Integer.parseInt(stringNumber);
            }

            //Print invalid input
            System.out.println("Invalid input!");

            //Enter number again
            System.out.print("Please enter " + str + " again: ");
            stringNumber = input.nextLine();
        }
        return Integer.parseInt(stringNumber);
    }

    /**
     * Method isValidTurn checks if entered row and col make a valid coordinate
     * @param board - tic-tac-toe board
     * @param row - entered row on the board
     * @param col - entered column on the board
     * @return true if row,col make a valid coordinate within bounds, unused by any other player
     */
    private boolean isValidTurn(String[][] board, int row, int col)
    {
        return (row>=0 && col>=0) && (row<board.length && col<board.length) && board[row][col].equals(" ");
    }

    /**
     * Method printErrorIndex prints the error message for invalid inputs
     * @param board - tic-tac-toe board
     * @param row - entered row on the board
     * @param col - entered column on the board
     */
    private void printErrorForIndex(String[][] board,int row,int col)
    {
        System.out.println("\n--Please enter valid indexes--");
        if((row<0 || col<0) || (row>=board.length || col>=board.length))
        {
            System.out.println("The entered indexes are out of bounds!");
        }
        else if(!board[row][col].equals(" "))
        {
            System.out.println("The entered indexes are already used by another player!");
        }


        //Print board after invalid turn!
        System.out.println();
        System.out.println("Enter coordinates again by referring to moves until now: ");
        System.out.println();
        Board.displayBoard();
        System.out.println();


    }




}
