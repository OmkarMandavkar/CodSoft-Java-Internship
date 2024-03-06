import java.util.Scanner;

// Bank Account class to store account balance
class BankAccount {
	private double balance;

	public BankAccount(double initialBalance) {
		this.balance = initialBalance;
	}

	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		balance = balance + amount;
	}

	public boolean withdraw(double amount) {
		if (amount <= balance) 
		{
			balance = balance - amount;
			return true;
		} 
		else {
			return false;
		}
	}
}	// close BankAccount class

// ATM class to represent the ATM machine
class ATM {
	private BankAccount account;
	private Scanner scanner;

	public ATM(BankAccount account) {
		this.account = account;
		this.scanner = new Scanner(System.in);
	}

	public void displayMenu() {
		System.out.println("\n1. CHECK BALANCE");
		System.out.println("2. DEPOSIT");
		System.out.println("3. WITHDRAW");
		System.out.println("4. EXIT");
	}

	public void Transaction() {

		while (true) {
			displayMenu();
			System.out.print("\nEnter your choice: ");
			int choice = scanner.nextInt();
			System.out.println(" ");

			switch (choice) {
			case 1:
				checkBalance();
				break;
			case 2:
				checkdeposit();
				break;
			case 3:
				checkwithdraw();
				break;
			case 4:
				System.out.print("SYSTEM EXIT !!!");
				System.exit(0);

			default:
				System.out.println("Invalid choice. Please try again.");
			}
		}
	}

	private void checkBalance() {
		System.out.println("Your balance is: " + account.getBalance());
	}

	private void checkdeposit() {
		System.out.print("Enter amount to deposit: ");
		double amount = scanner.nextDouble();
		account.deposit(amount);

		System.out.println("Deposit successful.");
	}

	private void checkwithdraw() {
		System.out.print("Enter amount to withdraw: ");
		double amount = scanner.nextDouble();
		boolean success = account.withdraw(amount);

		if (success) {
			System.out.println("Withdrawal Successful.");
		} 
		else {
			System.out.println("Withdrawal Failed -- Insufficient Balance");
		}
	}
}	// close ATM class

public class ATMMachine {
	public static void main(String[] args) {

		BankAccount account = new BankAccount(1000.0);
		ATM atm = new ATM(account);
		atm.Transaction();
	}
}	// close ATMMachine class