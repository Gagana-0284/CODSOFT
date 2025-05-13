import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        boolean playAgain = true;
        int totalRounds = 0;
        int totalWins = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            totalRounds++;
            int attemptsLimit = 10; // You can adjust the number of attempts
            int attempts = 0;
            boolean guessedCorrectly = false;

            // Generate a random number between 1 and 100.2
            // Since 100.2 is not an integer, we'll interpret the range as 1 to 100 with
            // decimal
            // For simplicity, generate a double between 1 and 100.2
            double targetNumber = 1 + (100.2 - 1) * random.nextDouble();

            System.out.println("\nRound " + totalRounds + ": Guess the number between 1 and 100.2");

            while (attempts < attemptsLimit && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                String input = scanner.nextLine();

                double userGuess;
                try {
                    userGuess = Double.parseDouble(input);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                    continue;
                }

                attempts++;

                if (Math.abs(userGuess - targetNumber) < 0.0001) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    guessedCorrectly = true;
                    totalWins++;
                } else if (userGuess > targetNumber) {
                    System.out.println("Too high. Try again.");
                } else {
                    System.out.println("Too low. Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.printf("Sorry, you've used all attempts. The number was %.2f%n", targetNumber);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes") && !response.equals("y")) {
                playAgain = false;
            }
        }

        System.out.println("\nGame Over!");
        System.out.println("Total rounds played: " + totalRounds);
        System.out.println("Total wins: " + totalWins);
        System.out.println("Thanks for playing!");

        scanner.close();
    }
}