import java.util.Scanner;

/**
 * MAIN CLASS
 * 
 * Use Case 4: Error Handling & validation
 * 
 * This class coordinates the game execution while ensuring
 * all user inputs are safely validated before processing.
 * 
 * Responsibilites:
 * 1. Initialize game configuration 
 * 2. Accept user input
 * 3. Validate input using validationService
 * 4. Handle game flow without crashing on invalid input
 * 
 * 
 * @author Developer
 * @version 4.0
 */

public class GuessingApp {

    static int hintCount = 0;
    public static void main(String[] args) throws InvalidInputException{
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
                break;
            }
        }

        scanner.close();
    }
}
