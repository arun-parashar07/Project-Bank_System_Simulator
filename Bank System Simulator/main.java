import java.util.*;


// CLASS 1: Account
// Represents a single bank account.
// Encapsulation is used (private fields + getters/setters).


class Account {
    private int accountNumber;
    private String holderName;
    private double balance;

    // Constructor to create a new account
    public Account(int accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    // Getters
    public int getAccountNumber() { return accountNumber; }
    public String getHolderName() { return holderName; }
    public double getBalance() { return balance; }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + " | New Balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount + " | New Balance: " + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount!");
        }
    }

    // To display account info
    @Override
    public String toString() {
        return "Account No: " + accountNumber + ", Holder: " + holderName + ", Balance: " + balance;
    }
}


// CLASS 2: Bank
// Manages multiple accounts using a HashMap.


class Bank {
    private HashMap<Integer, Account> accounts = new HashMap<>();

    // Create a new account
    public void createAccount(int accountNumber, String holderName, double initialBalance) {
        if (accounts.containsKey(accountNumber)) {
            System.out.println("Account with number " + accountNumber + " already exists!");
        } else {
            accounts.put(accountNumber, new Account(accountNumber, holderName, initialBalance));
            System.out.println("Account created successfully.");
        }
    }

    // Deposit into an account
    public void deposit(int accountNumber, double amount) {
        Account acc = accounts.get(accountNumber);
        if (acc != null) {
            acc.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Withdraw from an account
    public void withdraw(int accountNumber, double amount) {
        Account acc = accounts.get(accountNumber);
        if (acc != null) {
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Transfer money between accounts
    public void transfer(int fromAcc, int toAcc, double amount) {
        Account sender = accounts.get(fromAcc);
        Account receiver = accounts.get(toAcc);

        if (sender == null || receiver == null) {
            System.out.println("One or both accounts not found!");
            return;
        }

        if (sender.getBalance() >= amount) {
            sender.withdraw(amount);
            receiver.deposit(amount);
            System.out.println("Transfer successful.");
        } else {
            System.out.println("Insufficient balance in sender account!");
        }
    }

    // Display account details
    public void displayAccount(int accountNumber) {
        Account acc = accounts.get(accountNumber);
        if (acc != null) {
            System.out.println(acc);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Display all accounts
    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts in the bank.");
        } else {
            for (Account acc : accounts.values()) {
                System.out.println(acc);
            }
        }
    }
}


// CLASS 3: Main
// Entry point of program. Provides menu-driven interface.


public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\n===== Bank System Simulator =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Display Account");
            System.out.println("6. Display All Accounts");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    int accNo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Holder Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Initial Balance: ");
                    double balance = sc.nextDouble();
                    bank.createAccount(accNo, name, balance);
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();
                    System.out.print("Enter Amount to Deposit: ");
                    double deposit = sc.nextDouble();
                    bank.deposit(accNo, deposit);
                    break;

                case 3:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();
                    System.out.print("Enter Amount to Withdraw: ");
                    double withdraw = sc.nextDouble();
                    bank.withdraw(accNo, withdraw);
                    break;

                case 4:
                    System.out.print("Enter Sender Account Number: ");
                    int fromAcc = sc.nextInt();
                    System.out.print("Enter Receiver Account Number: ");
                    int toAcc = sc.nextInt();
                    System.out.print("Enter Amount to Transfer: ");
                    double amt = sc.nextDouble();
                    bank.transfer(fromAcc, toAcc, amt);
                    break;

                case 5:
                    System.out.print("Enter Account Number: ");
                    accNo = sc.nextInt();
                    bank.displayAccount(accNo);
                    break;

                case 6:
                    bank.displayAllAccounts();
                    break;

                case 7:
                    System.out.println("Exiting... Goodbye!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
}
