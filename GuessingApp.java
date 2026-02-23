import java.util.Scanner;

/**
 * MAIN CLASS
 * 
 * Use Case 5: Game Result Storage
 * 
 * This class coordinates the complete game flow
 * and persists the final result after completion
 * 
 * Responsibilites:
 * - Initialize game configuration 
 * - Accept and validate user guesses
 * - Generate hints when applicable
 * - Store game result at the end
 * 
 * 
 * @author Developer
 * @version 5.0
 */

public class GuessingApp {
    public static void main(String[] args) throws InvalidInputException{
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("===========================");
        System.out.println("Welcome to the Guessing App");
        System.out.println("===========================\n");

        /**
         * Player name is captured once
         * and stored along with game results
         */
        System.out.println("Enter Player Name: ");
        String player = scanner.nextLine();
        
        GameConfig config = new GameConfig();
        config.showRules();

        
        int attempts = 0;
        int hintCount = 0;

        /**
         * Tracks weather the player
         * successfully guessed the number.
         */
        boolean win = false;

        /**
         * Game loop runs until the player
         * exhausts the maximum attempts.
         */
        while(attempts < config.getMaxAttempts()){
            System.out.println("Enter your guess: ");
            
            /**
             * User input is validated before
             * being used on the game
             */	
            int guess = ValidationService.validateInput(scanner.nextLine());
            attempts++;

            String result = GuessValidator.validateGuess(guess, config.getTargetNumber());
            
            if(!"CORRECT".equals(result) && hintCount < config.getMaxHints()){
                hintCount++;
                System.out.println(HintService.generateHint(config.getTargetNumber(), hintCount));    
            }
            
            System.out.println(result);

            /**
             * Stop the loop immediately
             * if correct number is guessed.
             */
            if("CORRECT".equals(result)){
                win = true;
                break;
            }
        }

        StorageService.saveResult(player, attempts, win);

        scanner.close();
    }
}
