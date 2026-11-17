import java.io.Serializable;

public class CheckingAccount implements HasMenu, Serializable {
    private static final long serialVersionUID = 1L;
    protected double balance;

    // Default constructor
    public CheckingAccount() {
        balance = 0.0;
    } // end checkingAccount

    // Constructor with starting balance
    public CheckingAccount(double balance) {
        if (balance >= 0.0) {
            this.balance = balance;
        } else {
            this.balance = 0.0;
        } // end if else
    } // end CheckingAccount

    // Main method for testing this class by itself
    public static void main(String[] args) {
        CheckingAccount account = new CheckingAccount(500.00);
        account.start();
    } // end main

    // Display menu and get selection
    @Override
    public String menu() {
        System.out.println("\nChecking Account");
        System.out.println("0) Exit");
        System.out.println("1) Check Balance");
        System.out.println("2) Make a Deposit");
        System.out.println("3) Make a Withdrawal");
        System.out.print("Please enter (0-3): ");
        return User.input.nextLine();
    } // endu menu

    // Run the account menu
    @Override
    public void start() {
        boolean running = true;
        while (running) {
            String choice = menu();

            if (choice.equals("0")) {
                running = false;
            } else if (choice.equals("1")) {
                checkBalance();
            } else if (choice.equals("2")) {
                makeDeposit();
            } else if (choice.equals("3")) {
                makeWithdrawal();
            } else {
                System.out.println("Invalid input. Please enter 0, 1, 2, or 3.");
            } // end if else
        } // end while
    } // end start

    public double getBalance() {
        return balance;
    } // end getBalance

    public String getBalanceString() {
        return String.format("$%,.2f", balance);
    } // end getBalanceString

    public void setBalance(double balance) {
        if (balance >= 0.0) {
            this.balance = balance;
        } else {
            System.out.println("Balance cannot be negative.");
        } // end if else
    } // end setBalance

    public void checkBalance() {
        System.out.println("Your current balance is: " + getBalanceString());
    } // end checkBalance

    // Read a number from the shared Scanner
    private double getDouble() {
        try {
            return Double.parseDouble(User.input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid number entered.");
            return 0.0;
        } // end try catch
    } // end getDpuble

    public void makeDeposit() {
        System.out.print("Enter deposit amount: ");
        double amount = getDouble();

        if (amount > 0.0) {
            balance += amount;
            System.out.println("Deposit successful.");
            System.out.println("New balance: " + getBalanceString());
        } else {
            System.out.println("Deposit amount must be greater than zero.");
        } // end if else
    } // end makeDeposit

    public void makeWithdrawal() {
        System.out.print("Enter withdrawal amount: ");
        double amount = getDouble();

        if (amount <= 0.0) {
            System.out.println("Withdrawal amount must be greater than zero.");
        } else if (amount > balance) {
            System.out.println("Insufficient funds.");
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful.");
            System.out.println("New balance: " + getBalanceString());
        } // end if else
    } // end makeWithdrawal
} // end CheckingAccount class
