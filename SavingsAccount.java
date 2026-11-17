public class SavingsAccount extends CheckingAccount {
    private static final long serialVersionUID = 1L;
    private double interestRate;

    public SavingsAccount() {
        this(0.0, 0.05);
    } // end SavingsAccount

    public SavingsAccount(double balance) {
        this(balance, 0.05);
    } // end SavinsAccount

    public SavingsAccount(double balance, double interestRate) {
        super(balance);
        this.interestRate = 0.05;
        setInterestRate(interestRate);
    } // end SavingsAccount

    public double getInterestRate() {
        return interestRate;
    } // end getInterestRate

    public void setInterestRate(double interestRate) {
        if (interestRate >= 0.0) {
            this.interestRate = interestRate;
        } else {
            System.out.println("Interest rate cannot be negative.");
        } // end if else
    } // end setInterestRate

    public void calcInterest() {
        double interest = getBalance() * interestRate;
        setBalance(getBalance() + interest);
        System.out.println("Interest added: " + String.format("$%,.2f", interest));
        System.out.println("New savings balance: " + getBalanceString());
    } // end calcInterest
} // end SavingsAccount
