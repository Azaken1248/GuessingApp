# GuessingApp
An number guessing game using the concepts of OOPS in java.

## [Use Case 1](https://github.com/Azaken1248/GuessingApp/tree/feature/UC1-GuessNumber)
To create a configuration file to initialize the game and set rules and boundaries.
  
#### key concept
(1) Initializing constants ([GameConfig](https://github.com/Azaken1248/GuessingApp/blob/feature/UC1-GuessNumber/GameConfig.java))
```java
private final int MIN = 1;
private final int MAX = 100;
private final int MAX_ATTEMPTS = 7;
private final int MAX_HINTS = 3;
```

(2) Generation of random target to guess ([GameConfig](https://github.com/Azaken1248/GuessingApp/blob/feature/UC1-GuessNumber/GameConfig.java))
```java
import java.util.Random;
targetNumber = random.nextInt(MAX - MIN + 1) + MIN;
```
(3) Implementing getters and setters ([GameConfig](https://github.com/Azaken1248/GuessingApp/blob/feature/UC1-GuessNumber/GameConfig.java))
```java
public int getTargetNumber(){
    return targetNumber;
}

public int getMaxAttempts(){
    return MAX_ATTEMPTS;
}

public int getMaxHints(){
    return MAX_HINTS;
}
```
(4) Method to display game rules ([GameConfig](https://github.com/Azaken1248/GuessingApp/blob/feature/UC1-GuessNumber/GameConfig.java))
```java
public void showRules(){
    System.out.println("🎯 Guess a number between " + MIN + " and " + MAX +".");
    System.out.println("You have " + MAX_ATTEMPTS + " attempts.");
    System.out.println("Hints will be provided after wrong guesses.");
}
```

(5) Create a configuratuion object to initialize a new game ([GuessingApp](https://github.com/Azaken1248/GuessingApp/blob/feature/UC1-GuessNumber/GuessingApp.java))
```java
System.out.println("Welcome to the Guessing App");
GameConfig gameConfig = new GameConfig();
gameConfig.showRules();
```


## [Use Case 2](https://github.com/Azaken1248/GuessingApp/tree/feature/UC2-GuessNumber)
Add validation logic to the game to evaluate the guesses made by the user.

#### key concept
(1) Create a method to valid guesses of users and encapsulate it in a service ([GuessValidator](https://github.com/Azaken1248/GuessingApp/blob/feature/UC2-GuessNumber/GuessValidator.java))
```java
public static String validateGuess(int guess, int target){
    
    if(guess == target){
        return "CORRECT";
    }else if(guess < target){
        return "LOW";
    }

    return "HIGH";
}
```
(2) Add a scanner object to read user input ([GuessingApp](https://github.com/Azaken1248/GuessingApp/blob/feature/UC2-GuessNumber/GuessingApp.java))
```java
import java.uti;.Scanner;

Scanner scanner = new Scanner(System.in);
```
(3) Add a game loop to allow the player to enter guesses and validate them ([GuessingApp](https://github.com/Azaken1248/GuessingApp/blob/feature/UC2-GuessNumber/GuessingApp.java))
```java
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
```
## [Use Case 3](https://github.com/Azaken1248/GuessingApp/tree/feature/UC3-GuessNumber)
Add a module to generate hints for the player in case of a wrong answer (upto allowed number of hints).

#### key concept  
(1) Create a method to generate hints upto the valid hint count and encapsulate it in a module ([HintService](https://github.com/Azaken1248/GuessingApp/blob/feature/UC3-GuessNumber/HintService.java))
```java
public static String generateHint(int target, int hintCount){
    
    if(hintCount == 1){
        return (target % 2 == 0)
        ? "Hint: Number is EVEN"
        : "Hint: Number is ODD";
    }else if(hintCount == 2){
        return (target > 50)
        ? "Hint: Number is greater than 50"
        : "Hint: Number is 50 or less";
    }

    return "No more hints available";
}
```
(2) Update the game loop to include hint generation ([GuessingApp](https://github.com/Azaken1248/GuessingApp/blob/feature/UC3-GuessNumber/GuessingApp.java))
```java
// Can use non static variable if you plan on having different
// instances with seperate hint counters
static int hintCount = 0; 

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
```
## [Use Case 4](https://github.com/Azaken1248/GuessingApp/tree/feature/UC4-GuessNumber)
Create a module to handle exceptions caused due to incorrect inputs from the user

#### key concept
(1) Define a custom exception ([InvalidInputException](https://github.com/Azaken1248/GuessingApp/blob/feature/UC4-GuessNumber/InvalidInputException.java))
```java
class InvalidInputException extends Exception{
    public InvalidInputException(String message){
        super(message);
    }
}
```
(2) Create a method to validate the user input and encapsulate it in a module ([ValidationService](https://github.com/Azaken1248/GuessingApp/blob/feature/UC4-GuessNumber/ValidationService.java))
```java
public static int validateInput(String input) throws InvalidInputException{
    try{
        int value = Integer.parseInt(input);

        if(value < 1 || value > 100){
            throw new InvalidInputException("Numbers must be between 1 and 100");
        }

        return value;
        
    }catch(NumberFormatException e){
        throw new InvalidInputException("Invalid input. Please enter numbers only.");
    }
}
```
(3) Implement the validation function while taking input ([GuessingApp](https://github.com/Azaken1248/GuessingApp/blob/feature/UC4-GuessNumber/GuessingApp.java))
```java
public static void main(String[] args) throws InvalidInputException{
  // Initialization and other things ...
  while(attempts < config.getMaxAttempts()){
    System.out.println("Enter your guess: ");
    int guess = ValidationService.validateInput(scanner.nextLine());

    // Rest of the game loop...
  }
}
```
## [Use Case 5](https://github.com/Azaken1248/GuessingApp/tree/feature/UC5-GuessNumber)
Create a module to persist the scores of users in a results file using Buffered Reader

#### key concept
(1) Create a method to append player data to a file at the end of a game ([StorageService](https://github.com/Azaken1248/GuessingApp/blob/feature/UC5-GuessNumber/StorageService.java))
```java
public static void saveResult(String player, int attempts, boolean win){
    
    /**
     * Try-with-resources ensures that
     * the writer is closed automatically
     * after the operation is completes.
     */
    try(BufferedWriter writer = new BufferedWriter(new FileWriter("game_results.txt", true))){
        
        writer.write("Player: " + player + ", Attempts: " + attempts + ", Result: " + (win ? "WIN" : "LOSE"));
        writer.newLine();
    }catch(IOException e){
        System.out.println("Unable to save game result.");
    }
}
```
(2) Update the main class to save the playerdata at the end of a game ([GuessingApp](https://github.com/Azaken1248/GuessingApp/blob/feature/UC5-GuessNumber/GuessingApp.java))
```java
public static void main(String[] args) throws InvalidInputException{
  // Initialization and other things ...
  while(attempts < config.getMaxAttempts()){
    // Game loop remains same...
  }
  
  StorageService.saveResult(player, attempts, win);
}
```
## [Use Case 6](https://github.com/Azaken1248/GuessingApp/tree/feature/UC6-GuessNumber)
Create a controller module to handle the game loop based on user intents enabling retries

#### key concept
(1) Create a method that enables retries and encapsulate it in a module ([GameController](https://github.com/Azaken1248/GuessingApp/blob/feature/UC6-GuessNumber/GameController.java))
```java
public static boolean restartGame(Scanner scanner){
    System.out.println("Do you want to play again? (yes/no): ");
    return scanner.nextLine().equalsIgnoreCase("yes");
}
```
(2) Add an outer game controller loop outside the game loop to enable retries ([GuessingApp](https://github.com/Azaken1248/GuessingApp/blob/feature/UC6-GuessNumber/GuessingApp.java))
```java
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
```
## Integration Pipeline
Each feature is developed on a seperate branch and after testing it is  
moved to [dev](https://github.com/Azaken1248/GuessingApp/tree/dev)
after which it is finally released to [main](https://github.com/Azaken1248/GuessingApp)
