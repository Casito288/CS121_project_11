public class Customer extends User {
    private static final long serialVersionUID = 1L;

    private CheckingAccount checking;
    private SavingsAccount savings;

    // Default constructor
    public Customer() {
        this("customer", "1234");
    } // end Customer

    // Constructor with username and PIN
    public Customer(String userName, String PIN) {
        super(userName, PIN);
        checking = new CheckingAccount();
        savings = new SavingsAccount();
    } // end Customer

    // Main method for testing Customer by itself
    public static void main(String[] args) {
        Customer customer = new Customer("customer", "1234");
        System.out.println("Welcome to the ATM!");

        if (customer.login()) {
            System.out.println("Login successful!");
            customer.start();
        } else {
            System.out.println("Incorrect username or PIN.");
        } // end if else
    } // end main

    @Override
    public String menu() {
        System.out.println("\nCustomer Menu\n");
        System.out.println("0) Exit");
        System.out.println("1) Manage Checking Account");
        System.out.println("2) Manage Savings Account");
        System.out.println("3) Change PIN");
        System.out.print("\nPlease Enter (0-3): ");
        return User.input.nextLine();
    } // end menu

    @Override
    public void start() {
        boolean running = true;
        while (running) {
            String choice = menu();

            if (choice.equals("0")) {
                running = false;
            } else if (choice.equals("1")) {
                checking.start();
            } else if (choice.equals("2")) {
                savings.start();
            } else if (choice.equals("3")) {
                changePin();
            } else {
                System.out.println("Invalid input. Please enter one of the desired options.");
            } // end if else
        } // end while
    } // end start

    public void changePin() {
        System.out.print("Enter your current PIN: ");
        String currentPIN = User.input.nextLine();

        if (!this.PIN.equals(currentPIN)) {
            System.out.println("Incorrect PIN.");
            return;
        } // end if

        System.out.print("Enter your new PIN: ");
        String newPIN = User.input.nextLine();

        if (newPIN.trim().isEmpty()) {
            System.out.println("PIN cannot be empty.");
        } else {
            setPIN(newPIN);
            System.out.println("PIN successfully changed.");
        } // end if else
    } // end changPin

    public CheckingAccount getCheckingAccount() {
        return checking;
    } // end getCheckingAccount

    public SavingsAccount getSavingsAccount() {
        return savings;
    } // end getSavingsAccount

    @Override
    public String getReport() {
        String report = "\nAccount Report\n";
        report += "Customer: " + getUserName() + "\n";
        report += "Checking Account Balance: " + checking.getBalanceString() + "\n";
        report += "Savings Account Balance: " + savings.getBalanceString() + "\n";
        report += "Savings Interest Rate: "
                + String.format("%.2f%%", savings.getInterestRate() * 100) + "\n";
        report += "Total Balance: "
                + String.format("$%,.2f", checking.getBalance() + savings.getBalance());
        return report;
    } // end getReport
} // end Customer Class
