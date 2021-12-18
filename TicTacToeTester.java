import java.util.Scanner;
public class TicTacToeTester
{
    /**
     * Method main() implements the game by calling the relevant methods from the required
     * public interfaces - Player, Board, GameLogic
     * @param args - not used
     */
    public static void main(String args[])
    {
        //Instance of GameLogic Class created to invoke methods of GameLogic Class
        GameLogic obj = new GameLogic();

        //Set the number of players playing the game
        obj.setPlayersCount();

        //Set the BoardSize using static method setBoardSize from Board Class
        Board.setBoardSize(obj.getPlayersCount());

        //Initialize the Board -- set all values on Board to default " ", which is otherwise
        //                        an invalid input
        Board.initBoard();

        //Display the initial Board at the start of the game
        Board.displayBoard();

        //Initialize the Game -- all players chose their playCharacter and define the number
        //of repetitions of a character that make a winner
        obj.initializeGame();

        //playGame() method of GameLogic Class controls the game play until someone wins or until the game
        //is a tie
        obj.playGame();


    }

}
