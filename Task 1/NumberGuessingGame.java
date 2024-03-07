import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
 
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		Random rd = new Random();

		int guess, points = 0;

		for (int i = 1; i <= 3; i++) {
			int num = rd.nextInt(10);

			System.out.println("\nROUND NUMBER: " + i);
			{
				int attempt = 1, score = 0; 
				boolean guessedCorrectly = false; 
				
				while (attempt <= 3) {
					System.out.print("  -->Guess the Number Between 0 and 100: ");

					Scanner sc = new Scanner(System.in);
					guess = sc.nextInt();

					if (guess == num) {
						System.out.println("    You Won !! Number of Attempt took: " + attempt);
						score = 10;
						points += score;
						guessedCorrectly = true; 
						break;
					} 

					else if (guess > num) {
						System.out.println("    Number Guessed is HIGH");
					} 

					else {
						System.out.println("    Number Guessed is LOW");
					}
					attempt++;
				}

				if (!guessedCorrectly) {
					System.out.println("\nYou Lost !!!  Number was: " + num);
				}
				System.out.println("Points obtained for Round " + i + " is: " + score);
			}
		}
		System.out.println("\nTOTAL POINTS SCORED: " + points + "/30");
	}
}
