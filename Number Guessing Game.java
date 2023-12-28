import java.util.*;
 public class Main {
    public static void main(String[] args) {
        int lowerBound = 1;
        int upperBound = 100;
        int randomNumber = generateRandomNumber(lowerBound, upperBound);
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Guess the number between " + lowerBound + " and " + upperBound + ": ");
            int userGuess = sc.nextInt();
            if (userGuess == randomNumber) {
                System.out.println("Congratulations! Your guess is correct.");
                break; 
            } else if (userGuess < randomNumber) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }
        }
    }
    private static int generateRandomNumber(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }
}