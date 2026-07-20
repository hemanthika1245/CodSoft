import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int roundsWon = 0;
        int totalScore = 0;
        char playAgain;

        System.out.println("======================================");
        System.out.println("      NUMBER GUESSING GAME");
        System.out.println("======================================");

        do {

            int randomNumber = random.nextInt(100) + 1; // 1 to 100
            int maxAttempts = 7;
            boolean guessedCorrectly = false;

            System.out.println("\nI have chosen a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts to guess it.");

            for (int attempt = 1; attempt <= maxAttempts; attempt++) {

                System.out.print("\nAttempt " + attempt + ": Enter your guess: ");
                int guess = sc.nextInt();

                if (guess == randomNumber) {
                    System.out.println(" Congratulations! You guessed the correct number.");

                    guessedCorrectly = true;
                    roundsWon++;

                    // Score based on attempts left
                    int score = (maxAttempts - attempt + 1) * 10;
                    totalScore += score;

                    System.out.println("You earned " + score + " points.");
                    break;

                } else if (guess < randomNumber) {
                    System.out.println(" Too Low!");

                } else {
                    System.out.println(" Too High!");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("\n You have used all your attempts.");
                System.out.println("The correct number was: " + randomNumber);
            }

            System.out.println("\nCurrent Score: " + totalScore);
            System.out.println("Rounds Won: " + roundsWon);

            System.out.print("\nDo you want to play another round? (Y/N): ");
            playAgain = sc.next().charAt(0);

        } while (playAgain == 'Y' || playAgain == 'y');

        System.out.println("\n======================================");
        System.out.println("           GAME OVER");
        System.out.println("======================================");
        System.out.println("Rounds Won : " + roundsWon);
        System.out.println("Final Score: " + totalScore);
        System.out.println("Thanks for playing!");

        sc.close();
    }
}