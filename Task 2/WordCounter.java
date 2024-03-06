import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WordCounter {

	public static void main(String[] args) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.println("\n\n1: WORD COUNTER: USING STRING");
			System.out.println("2: WORD COUNTER: USING FILE");
			System.out.println("3: EXIT");
			System.out.print("\nEnter your Choice: ");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				UsingString();
				break;

			case 2:
				UsingFile();
				break;

			case 3:
				System.out.println("\nSYSTEM EXIT !!!");
				System.exit(0);

			default:
				System.out.println("\nInvalid Choice !!!, Please try again.");
			}


		}
	}

	public static void UsingString(){

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEnter a String: ");
		String str = sc.nextLine();
		str = str.toLowerCase();

		int count=0;
		int visited = -1;

		String words[] = str.split(" ");
		int fr[] = new int [words.length];	

		for(int i=0; i<words.length; i++) {
			count++;
		}

		System.out.println("Total Count of Words: "+ count);

		for(int i=0; i<words.length; i++)
		{
			count=1;

			for(int j=i+1; j<words.length; j++)
			{
				if(words[i].equals(words[j]))
				{
					count++;
					fr[j] = visited;
				}
			}

			if(fr[i]!= visited) 			
			{
				fr[i] = count;
			}
		}

		System.out.println("\nWords with their Frequency: ");
		for(int i=0; i<fr.length; i++) {
			if(fr[i]!= visited)
				System.out.println("- " +words[i] +": " +fr[i]);
		}
	}


	public static void UsingFile() {

		String line;  
		int count = 0;  

		try {
			FileReader file = new FileReader("bin\\Task2.txt");
			try (BufferedReader br = new BufferedReader(file)) {
				while((line = br.readLine()) != null) {
					String words[] = line.split(" ");

					for(int i=0; i<words.length; i++) {
						count++;
					}	
					System.out.println("\nTotal Count of Words: "+ count);
				}
			}
		} 

		catch(IOException e)
		{
			System.out.println(e);
		} 
	}
}	