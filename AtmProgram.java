import java.util.Scanner;

// Class representing a user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        if (initialBalance < 0) {
            this.balance = 0;
        } else {
            this.balance = initialBalance;
        }
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// Class representing the ATM machine
class ATM {
    private BankAccount account;
    private Scanner scanner;

    public ATM(BankAccount account) {
        this.account = account;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        int choice = 0;
        do {
            displayMenu();
            choice = getIntInput("Select an option: ");
            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    depositAmount();
                    break;
                case 3:
                    withdrawAmount();
                    break;
                case 4:
                    System.out.println("Thank you! Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n==== ATM Main Menu ====");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    private void checkBalance() {
        System.out.printf("Your current balance is: $%.2f\n", account.getBalance());
    }

    private void depositAmount() {
        double amount = getDoubleInput("Enter amount to deposit: $");
        if (amount <= 0) {
            System.out.println("Please enter an amount greater than zero.");
            return;
        }
        account.deposit(amount);
        System.out.printf("Successfully deposited $%.2f. New balance: $%.2f\n", amount, account.getBalance());
    }

    private void withdrawAmount() {
        double amount = getDoubleInput("Enter amount to withdraw: $");
        if (amount <= 0) {
            System.out.println("Please enter an amount greater than zero.");
            return;
        }
        if (amount > account.getBalance()) {
            System.out.println("Insufficient balance. Withdrawal failed.");
        } else {
            account.withdraw(amount);
            System.out.printf("Successfully withdrew $%.2f. Remaining balance: $%.2f\n", amount, account.getBalance());
        }
    }

    // Helper method to get integer input
    private int getIntInput(String prompt) {
        int value = -1;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return value;
    }

    // Helper method to get double input
    private double getDoubleInput(String prompt) {
        double value = -1;
        while (true) {
            System.out.print(prompt);
            try {
                value = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid amount.");
            }
        }
        return value;
    }
}

// Main class to run the ATM simulation
public class AtmProgram {
    public static void main(String[] args) {
        // Initialize bank account with some starting balance
        BankAccount userAccount = new BankAccount(1000.00);
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
