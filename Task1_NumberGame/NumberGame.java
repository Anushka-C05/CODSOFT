import java.util.Random;
import java.util.Scanner;

class GameStats {
    int gamesPlayed = 0;
    int gamesWon = 0;
    int highScore = 0;
}

public class NumberGame {

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();
    static GameStats stats = new GameStats();

    public static void main(String[] args) {

        int choice;

        do {
            showMenu();

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    playGame();
                    break;

                case 2:
                    showStatistics();
                    break;

                case 3:
                    showHighScore();
                    break;

                case 4:
                    System.out.println("Thank you for playing!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 4);

        sc.close();
    }

    public static void showMenu() {
        System.out.println("\n=================================");
        System.out.println("      NUMBER GUESSING GAME");
        System.out.println("=================================");
        System.out.println("1. Play Game");
        System.out.println("2. View Statistics");
        System.out.println("3. View High Score");
        System.out.println("4. Exit");
        System.out.println("=================================");
    }

    public static void playGame() {

        int maxNumber;
        int maxAttempts;

        System.out.println("\nChoose Difficulty:");
        System.out.println("1. Easy (1-50, 10 Attempts)");
        System.out.println("2. Medium (1-100, 7 Attempts)");
        System.out.println("3. Hard (1-200, 5 Attempts)");

        System.out.print("Enter Difficulty: ");
        int difficulty = sc.nextInt();

        switch (difficulty) {
            case 1:
                maxNumber = 50;
                maxAttempts = 10;
                break;

            case 2:
                maxNumber = 100;
                maxAttempts = 7;
                break;

            case 3:
                maxNumber = 200;
                maxAttempts = 5;
                break;

            default:
                System.out.println("Invalid choice! Medium selected.");
                maxNumber = 100;
                maxAttempts = 7;
        }

        int secretNumber = random.nextInt(maxNumber) + 1;

        int attempts = 0;
        boolean guessedCorrectly = false;

        stats.gamesPlayed++;

        System.out.println("\nGuess a number between 1 and " + maxNumber);

        while (attempts < maxAttempts) {

            System.out.print("Enter your guess: ");
            int guess = sc.nextInt();

            attempts++;

            if (guess == secretNumber) {

                guessedCorrectly = true;

                stats.gamesWon++;

                int score = calculateScore(maxAttempts, attempts);

                if (score > stats.highScore) {
                    stats.highScore = score;
                }

                System.out.println("\nCongratulations!");
                System.out.println("You guessed the number correctly.");
                System.out.println("Attempts Used: " + attempts);
                System.out.println("Score Earned: " + score);

                break;
            }

            if (guess > secretNumber) {

    int difference = guess - secretNumber;

    System.out.println("Too High!");

    if (difference > 30) {
        System.out.println("You are very far from the correct number.");
    } else {
        System.out.println("You are close! Try a slightly smaller number.");
    }

}
else {

    int difference = secretNumber - guess;

    System.out.println("Too Low!");

    if (difference > 30) {
        System.out.println("You are very far from the correct number.");
    } else {
        System.out.println("You are close! Try a slightly larger number.");
    }

}
            // Hint after 3 wrong attempts
            if (attempts == 3) {

                System.out.println("\nHINT:");

                if (secretNumber % 2 == 0) {
                    System.out.println("The number is EVEN.");
                } else {
                    System.out.println("The number is ODD.");
                }

                int lower = Math.max(1, secretNumber - 10);
                int upper = Math.min(maxNumber, secretNumber + 10);

                System.out.println("The number lies between "
                        + lower + " and " + upper);
            }

            System.out.println("Attempts Remaining: "
                    + (maxAttempts - attempts));
        }

        if (!guessedCorrectly) {
            System.out.println("\nGame Over!");
            System.out.println("The correct number was: "
                    + secretNumber);
        }
    }

    public static int calculateScore(int maxAttempts,
                                     int attemptsUsed) {

        int score = (maxAttempts - attemptsUsed + 1) * 10;

        if (score < 10) {
            score = 10;
        }

        return score;
    }

    public static void showStatistics() {

        System.out.println("\n========== STATISTICS ==========");

        System.out.println("Games Played : "
                + stats.gamesPlayed);

        System.out.println("Games Won    : "
                + stats.gamesWon);

        System.out.println("Games Lost   : "
                + (stats.gamesPlayed - stats.gamesWon));

        if (stats.gamesPlayed > 0) {

            double winPercentage =
                    ((double) stats.gamesWon
                            / stats.gamesPlayed) * 100;

            System.out.printf("Win Percentage : %.2f%%\n",
                    winPercentage);
        }

        else {
            System.out.println("Win Percentage : 0%");
        }
    }

    public static void showHighScore() {

        System.out.println("\n========== HIGH SCORE ==========");
        System.out.println("Highest Score : "
                + stats.highScore);
    }
}