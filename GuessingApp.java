import java.util.Scanner;

/**
 * MAIN CLASS
 * 
 * Coordinates the game flow
 * 1. Initialize game
 * 2. Accept user guesses
 * 3. Validate guesses
 * 4. Provide hints on wrong answer
 * 5. Stop when game ends
 * 
 * 
 * @author Developer
 * @version 3.0
 */

public class GuessingApp {

    static int hintCount = 0;
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
