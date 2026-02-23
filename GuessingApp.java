import java.util.Scanner;

/**
 * MAIN CLASS
 * 
 * Use Case 6: Game Restart & Exit
 * 
 * This class coordinates the complete game lifecycle,
 * allowinf the player to replay or exit gracefully.
 * 
 * Responsibilites:
 * - Start a new game session 
 * - Execute the guessing flow
 * - Persist game results
 * - Restart or exit based on user choice
 * 
 * 
 * @author Developer
 * @version 6.0
 */

public class GuessingApp {
    public static void main(String[] args) throws InvalidInputException{
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("===========================");
        System.out.println("Welcome to the Guessing App");
        System.out.println("===========================\n");
        boolean restart;

        do{
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

            /**
             * Player decides weather to 
             * restart the game or exit.
             */
            restart = GameController.restartGame(scanner);

        }while(restart);
    }
}
