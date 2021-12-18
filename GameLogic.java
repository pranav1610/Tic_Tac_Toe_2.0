import java.util.ArrayList;
import java.util.Scanner;

public class GameLogic
{
    //Stores the play characters different players
    private ArrayList<String> charactersList = new ArrayList<String>();

    //Stores the number of players playing game
    private int playerCount;

    //Stores the player objects
    Player[] players;

    //Stores the number of repeated characters in a row required to win!
    int numToWin;

    //Stores the player playing
    Player playerWon;


    /**
     * Method initializeGame controls the initialization of the game by calling
     * for the input characters for each player, and the number of repeated characters required
     * to declare a winner. Lastly, the method declares playerCount number of Player objects
     */
    public void initializeGame()
    {
        //Initialize the number of player objects to be instantiated!
        players = new Player[getPlayersCount()];

        //Get criteria for win - number of repeated characters in a row to win!
        setNumToWin();
        System.out.println();

        //Get characters for each player
        inputCharacters();
        System.out.println();

    }

    /**
     * Method getPlayersCount is an accessor that gets the playerCount variable
     * @return playerCount - number of players playing game
     */
    public int getPlayersCount()
    {
        return playerCount;
    }

    /**
     * getNumToWin - returns the number of repeated characters in a row to win
     * @return - the number of repeated characters in a row to win
     */
    public int getNumToWin()
    {
        return numToWin;
    }

    /**
     * Method setPlayersCount determines the number of players playing the game and mutates
     * playerCount private instance variable
     */
    public void setPlayersCount()
    {

        Scanner input = new Scanner(System.in);

        String userInput;

        playerCount = 0;

        System.out.print("How many players will be playing? (3-10): ");

        //Keep prompting for valid input until 3-10 is entered
        while (playerCount < 3 || playerCount > 10)
        {
            //Prompt again for invalid inputs
            try
            {
                userInput = input.nextLine();
                /*making sure the number entered meets the requirement*/
                if(Integer.parseInt(userInput)>2 && Integer.parseInt(userInput)<11) {
                    playerCount = Integer.parseInt(userInput);
                }

                else {
                    System.out.print("How many players will be playing? (3-10): ");
                }
            }
            catch (NumberFormatException ex) {
                System.out.print("The input should only be an integer\nHow many players will be playing? (3-10): ");
            }
        }


    }

    /**
     * Method inputCharacters prompts and stores the playCharacter for each player
     */
    public void inputCharacters()
    {
        System.out.println("Let's begin by getting play characters for all players!" +
                "\n--The character should only be one character in length." +
                "\n--The character cannot be a space.");
        for(int i=0;i<playerCount;i++)
        {
            //Create the actual Player Object
            players[i] = new Player();

            //Set the player's character
            players[i].setPlayerCharacter(i,charactersList);


        }
        System.out.println("The characters chosen in order are: " + charactersList);

    }

    /**
     * Method setNumToWin mutates the numToWin private instance variable that stores
     * how many repetitions of a character defines a winner
     */
    public void setNumToWin()
    {
        Scanner input = new Scanner(System.in);

        //numberWin - the number of characters required to win!
        int numberWin = 0;

        //userInput - string representation of input
        String userInput;

        System.out.print("Enter the number repeated characters in a row to win: ");

        //Accept only between 3 and (playerCount + 1)
        while (numberWin>(getPlayersCount()+1) || (numberWin<3))
        {
            try
            {
                userInput = input.nextLine();
                /*making sure the number entered meets the requirement*/
                if(Integer.parseInt(userInput)>=3 && Integer.parseInt(userInput)<=(getPlayersCount()+1)) {
                    numberWin = Integer.parseInt(userInput);
                }

                else {
                    System.out.print("Enter the number repeated characters in a row to win: ");
                }
            }
            catch (NumberFormatException ex) {
                System.out.print("Input should only be an integer!\nEnter the number repeated characters in a row to win: ");
            }
        }

        //The number of characters required to win returned!
        numToWin = numberWin;
    }

    /**
     * Method playGame control the game -- allotting turns to players until someone wins or
     * in case the game ties
     */
    public void playGame()
    {

        //keeps track of which player is supposed to make the turn
        int playerIndex = 0;

        int playerWonIndex=-1;

        //someoneWon - stores if any player won the game!
        boolean someoneWon = false;

        while(!someoneWon && !Board.isFilled())
        {
            players[playerIndex].playTurn(playerIndex);
            //Print the recently added (row,col) at index Arraylist (size-2) & (size-1)
            System.out.println("LAST PLAY OF THE GAME -- row: " + players[playerIndex].getCharacterLocations().get(players[playerIndex].getCharacterLocations().size()-2) + ", col: " + players[playerIndex].getCharacterLocations().get(players[playerIndex].getCharacterLocations().size()-1));

            //The current player playing won!
            if(isWinner(players[playerIndex]))
            {
                //store the player who won the game
                playerWon = players[playerIndex];
                playerWonIndex = playerIndex;

                System.out.println("Player " + (playerWonIndex+1) + " won!"  );

                //indicates 'playerWon' won the game
                someoneWon = true;

            }
            //Game not won by anyone yet!
            else if(!Board.isFilled())
            {
                System.out.println("\nNext Player plays!");
            }

            //Next player's turn!
            playerIndex++;

            //After last player, first player plays!
            if(playerIndex==getPlayersCount())
            {
                playerIndex=0;
            }

        }

        //If Board is filled, print that Game was a tie!
        if(Board.isFilled() )
        {
            System.out.println();
            System.out.println("Board was filled!");
            System.out.println();
            System.out.println("\t********* The Game is a Tie **********");
        }

    }

    /**
     * Helper method isWinner checks if a player won
     * @param player - player being checked if he/she won
     * @return true if player won, false otherwise
     */
    private boolean isWinner(Player player)
    {
        //Store the string representation of board to pass to the helper methods in return
        String[][] board = Board.getBOARD();

        return (
                    checkNorth(player, board) ||
                    checkSouth(player, board) ||
                    checkEast(player, board) ||
                    checkNorthEast(player, board) ||
                    checkSouthEast(player, board) ||
                    checkWest(player, board) ||
                    checkNorthWest(player, board) ||
                    checkSouthWest(player, board)
                );

    }

    /**
     * Method checkWest checks for repetitions for win in west
     * @param player - player being checked for win
     * @param board - String representation of Tic-Tac-Toe Board
     * @return true if characters repeat and players win, false otherwise
     */
    private boolean checkWest(Player player, String[][] board)
    {
        //characterCount -- counts the number of times a player's character appears in a row
        int characterCount=1;

        if(player.getCharacterLocations().size() >= 2)
        {
            int row = player.getCharacterLocations().get(player.getCharacterLocations().size()-2);
            int col = player.getCharacterLocations().get(player.getCharacterLocations().size()-1);

            //Check for repetitions
            while(characterCount<getNumToWin())
            {
                if(col<=0 || !board[row][col-1].equals(board[row][col]))
                {
                    return false;
                }
                else
                {
                    col--;
                    characterCount++;
                }

            }
            //remove this if, logic has to work without it!
            //if(characterCount==getNumToWin())
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Method checkNorthWest checks for repetitions for win in North-West
     * @param player - player being checked for win
     * @param board - String representation of Tic-Tac-Toe Board
     * @return true if characters repeat and players win, false otherwise
     */
    private boolean checkNorthWest(Player player, String[][] board)
    {
        //characterCount -- counts the number of times a player's character appears in a row
        int characterCount=1;

        if(player.getCharacterLocations().size() >= 2)
        {
            int row = player.getCharacterLocations().get(player.getCharacterLocations().size()-2);
            int col = player.getCharacterLocations().get(player.getCharacterLocations().size()-1);

            //Check for repetitions
            while(characterCount<getNumToWin())
            {
                if(row<=0 || col<=0 || !board[row-1][col-1].equals(board[row][col]))
                {
                    return false;
                }
                else
                {
                    row--;
                    col--;
                    characterCount++;

                    //print the count!
                    //System.out.println("character count of \'" + player.getPlayerCharacter() + "\' : " + characterCount);
                }

            }
            //remove this if, logic has to work without it!
            //if(characterCount==getNumToWin())
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Method checkSouthEast checks for repetitions for win in South-East
     * @param player - player being checked for win
     * @param board - String representation of Tic-Tac-Toe Board
     * @return true if characters repeat and players win, false otherwise
     */
    private boolean checkSouthEast(Player player, String[][] board)
    {
        //characterCount -- counts the number of times a player's character appears in a row
        int characterCount=1;

        if(player.getCharacterLocations().size() >= 2)
        {
            int row = player.getCharacterLocations().get(player.getCharacterLocations().size()-2);
            int col = player.getCharacterLocations().get(player.getCharacterLocations().size()-1);

            //Check for repetitions
            while(characterCount<getNumToWin())
            {
                if(row>=(board.length-1) || col>=(board.length-1) || !board[row+1][col+1].equals(board[row][col]))
                {
                    return false;
                }
                else
                {
                    row++;
                    col++;
                    characterCount++;

                }

            }
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Method checkSouth checks for repetitions for win in South
     * @param player - player being checked for win
     * @param board - String representation of Tic-Tac-Toe Board
     * @return true if characters repeat and players win, false otherwise
     */
    private boolean checkSouth(Player player, String[][] board)
    {
        //characterCount -- counts the number of times a player's character appears in a row
        int characterCount=1;

        if(player.getCharacterLocations().size() >= 2)
        {
            int row = player.getCharacterLocations().get(player.getCharacterLocations().size()-2);
            int col = player.getCharacterLocations().get(player.getCharacterLocations().size()-1);

            //Check for repetitions
            while(characterCount<getNumToWin())
            {
                if(row>=(board.length-1) || !board[row][col].equals(board[row+1][col]))
                {
                    return false;
                }
                else
                {
                    row++;
                    characterCount++;
                }

            }
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Method checkNorth checks for repetitions for win in North
     * @param player - player being checked for win
     * @param board - String representation of Tic-Tac-Toe Board
     * @return true if characters repeat and players win, false otherwise
     */
    private boolean checkNorth(Player player, String[][] board)
    {
        //characterCount -- counts the number of times a player's character appears in a row
        int characterCount=1;

        if(player.getCharacterLocations().size() >= 2)
        {
            int row = player.getCharacterLocations().get(player.getCharacterLocations().size()-2);
            int col = player.getCharacterLocations().get(player.getCharacterLocations().size()-1);

            //Check for repetitions
            while(characterCount<getNumToWin())
            {
                if(row<=0 || !board[row-1][col].equals(board[row][col]))
                {
                    return false;
                }
                else
                {
                    row--;
                    characterCount++;
                }

            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Method checkNorthEast checks for repetitions for win in North-East
     * @param player - player being checked for win
     * @param board - String representation of Tic-Tac-Toe Board
     * @return true if characters repeat and players win, false otherwise
     */
    private boolean checkNorthEast(Player player, String[][] board)
    {
        //characterCount -- counts the number of times a player's character appears in a row
        int characterCount=1;

        if(player.getCharacterLocations().size() >= 2)
        {
            int row = player.getCharacterLocations().get(player.getCharacterLocations().size()-2);
            int col = player.getCharacterLocations().get(player.getCharacterLocations().size()-1);

            //Check for repetitions
            while(characterCount<getNumToWin())
            {
                if(row<=0 || col>=(board.length-1) || !board[row-1][col+1].equals(board[row][col]))
                {
                    return false;
                }
                else
                {
                    row--;
                    col++;
                    characterCount++;

                }

            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Method checkSouthWest checks for repetitions for win in South-West
     * @param player - player being checked for win
     * @param board - String representation of Tic-Tac-Toe Board
     * @return true if characters repeat and players win, false otherwise
     */
    private boolean checkSouthWest(Player player, String[][] board)
    {
        //characterCount -- counts the number of times a player's character appears in a row
        int characterCount=1;

        if(player.getCharacterLocations().size() >= 2)
        {
            int row = player.getCharacterLocations().get(player.getCharacterLocations().size()-2);
            int col = player.getCharacterLocations().get(player.getCharacterLocations().size()-1);

            //Check for repetitions
            while(characterCount<getNumToWin())
            {
                if(row>=(board.length-1) || col<=0 || !board[row+1][col-1].equals(board[row][col]))
                {
                    return false;
                }
                else
                {
                    row++;
                    col--;
                    characterCount++;

                }

            }

            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Method checkEast checks for repetitions for win in east
     * @param player - player being checked for win
     * @param board - String representation of Tic-Tac-Toe Board
     * @return true if player won in East, false otherwise
     */
    private boolean checkEast(Player player, String[][] board)
    {
        //characterCount -- counts the number of times a player's character appears in a row
        int characterCount=1;

        if(player.getCharacterLocations().size() >= 2)
        {
            int row = player.getCharacterLocations().get(player.getCharacterLocations().size()-2);
            int col = player.getCharacterLocations().get(player.getCharacterLocations().size()-1);

            //Check for repetitions
            while(characterCount<getNumToWin())
            {
                if(col>=(board.length-1) || !board[row][col].equals(board[row][col+1]))
                {
                    return false;
                }
                else
                {
                    col++;
                    characterCount++;
                }

            }

            return true;
        }
        else
        {
            return false;
        }
    }

}
