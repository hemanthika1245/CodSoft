import java.util.Scanner;

// Class representing the user's bank account
class BankAccount {

    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return false;
        }

        if (amount > balance) {
            System.out.println("Insufficient balance.");
            return false;
        }

        balance -= amount;
        return true;
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
            return false;
        }

        balance += amount;
        return true;
    }
}

// Class representing the ATM
class ATM {

    private BankAccount account;
    private Scanner sc;

    public ATM(BankAccount account) {
        this.account = account;
        sc = new Scanner(System.in);
    }

    public void displayMenu() {

        int choice;

        do {
            System.out.println("\n==================================");
            System.out.println("            ATM MENU");
            System.out.println("==================================");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    checkBalance();
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    deposit(depositAmount);
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    withdraw(withdrawAmount);
                    break;

                case 4:
                    System.out.println("Thank you for using the ATM!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 4);

        sc.close();
    }

    public void checkBalance() {
        System.out.printf("Current Balance: %.2f%n", account.getBalance());
    }

    public void deposit(double amount) {
        if (account.deposit(amount)) {
            System.out.println("Deposit successful.");
            checkBalance();
        }
    }

    public void withdraw(double amount) {
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful.");
            checkBalance();
        }
    }
}

// Main class
public class ATMInterface {

    public static void main(String[] args) {

        // Initial balance
        BankAccount account = new BankAccount(5000);

        // Connect ATM with bank account
        ATM atm = new ATM(account);

        // Start ATM
        atm.displayMenu();
    }
}