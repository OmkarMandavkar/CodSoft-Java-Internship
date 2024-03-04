import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		Random rd = new Random();

		int guess, score=0, points=0;

		for(int i=1; i<=3; i++) {
			int num = rd.nextInt(100);
			System.out.println("\nROUND NUMBER: " + i);
			{		
				int attempt = 0;
				while(attempt <= 3) {
					System.out.print("\n  Guess the Number Between 0 and 100: ");

					Scanner sc = new Scanner(System.in); 
					guess = sc.nextInt();

					attempt++;

					if(guess == num) {
						System.out.println("  You Won !!");
						System.out.println("  Number of Attempt took: "+attempt);
						score = 10;
						points = points + score;
						break;
					}

					else if(guess > num) {
						System.out.println("  Number Guessed is HIGH");
					}

					else {
						System.out.println("  Number Guessed is LOW");
					}
				}
				System.out.println("\nYou Lost !!!  Number was: "+num);
				System.out.println("Points obtained for Round "+i+" is: "+ score);

			}
			//System.out.println("The total points obtained upto this round: " + points);
		}
		System.out.println("\nTOTAL POINTS SCORED: " + points + "/30");
	}
}
