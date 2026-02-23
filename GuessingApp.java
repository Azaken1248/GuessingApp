import java.util.Scanner;

/**
 * GuessingApp - Use Case 1: Game Initialization
 * 
 * This class serves as the application entry point
 * It initializes the game configuration and displays game rules.
 * 
 * No user input or gameplay logic is implemented at this stage
 * 
 * @author Developer
 * @version 1.0
 */

public class GuessingApp {
    public static void main(String[] args) {
        System.out.println("Welcome to the Guessing App");
        
        GameConfig config = new GameConfig();
        config.showRules();

        Scanner scanner = new Scanner(System.in);
        int attempts = 0;

        /**
         * Game loop runs until the player
         * exhausts the maximum attempts.
         */
        while(attempts < config.getMaxAttempts()){
            System.out.println("Enter your guess: ");
            int guess = scanner.nextInt();
            attempts++;

            String result = GuessValidator.validateGuess(guess, config.getTargetNumber());
            System.out.println(result);

            /**
             * Stop the loop immediately
             * if correct number is guessed.
             */
            if("CORRECT".equals(result)){
                break;
            }
        }

        scanner.close();
    }
}
