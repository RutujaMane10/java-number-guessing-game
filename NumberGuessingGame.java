import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    // You can tweak these:
    private static final int MIN = 1;
    private static final int MAX = 100;
    private static final int MAX_ATTEMPTS = 7;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.println("=== Number Guessing Game ===");
        boolean playAgain = true;

        while (playAgain) {
            int secret = random.nextInt(MAX - MIN + 1) + MIN; // [MIN, MAX]
            int attempts = 0;

            System.out.printf("I'm thinking of a number between %d and %d.%n", MIN, MAX);

            // Game loop for one round
            while (attempts < MAX_ATTEMPTS) {
                System.out.printf("Attempt %d/%d - Enter your guess: ", attempts + 1, MAX_ATTEMPTS);

                // Read a line and validate it is an integer within range
                String line = sc.nextLine().trim();
                if (line.isEmpty()) {
                    System.out.println("Please type a number (e.g., 42).");
                    continue;
                }

                int guess;
                try {
                    guess = Integer.parseInt(line);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Enter digits only (e.g., 42).");
                    continue;
                }

                if (guess < MIN || guess > MAX) {
                    System.out.printf("Out of range! Enter a number between %d and %d.%n", MIN, MAX);
                    continue;
                }

                attempts++;

                if (guess == secret) {
                    System.out.printf("🎉 Correct! You guessed it in %d attempt%s.%n",
                            attempts, attempts == 1 ? "" : "s");
                    break;
                } else if (guess < secret) {
                    System.out.println("Too low! Try a higher number.");
                } else {
                    System.out.println("Too high! Try a lower number.");
                }

                if (attempts == MAX_ATTEMPTS) {
                    System.out.printf("No more attempts left. The number was %d.%n", secret);
                }
            }

            // Ask to play again (robust yes/no)
            playAgain = askYesNo(sc, "Play again? (y/n): ");
        }

        System.out.println("Thanks for playing! 👋");
        sc.close();
    }

    private static boolean askYesNo(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String ans = sc.nextLine().trim().toLowerCase();
            if (ans.equals("y") || ans.equals("yes")) return true;
            if (ans.equals("n") || ans.equals("no")) return false;
            System.out.println("Please reply with 'y' or 'n'.");
        }
    }
}
