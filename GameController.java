import java.util.Scanner;
/**
 * Handles game lifecylce decisions.
 * 
 * This class is responsible for deciding
 * weather the game should restart or exit
 * based on user choice.
 */
class GameController {

    /**
     * Asks the player if they want to 
     * restart the game after completion.
     * 
     * Returns true if the game should restart,
     * false if the application should exit.
     */
    public static boolean restartGame(Scanner scanner){
        System.out.println("Do you want to play again? (yes/no): ");
        return scanner.nextLine().equalsIgnoreCase("yes");
    }
    
}
