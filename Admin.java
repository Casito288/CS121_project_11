public class Admin extends User {
    private static final long serialVersionUID = 1L;

    public Admin() {
        super("admin", "0000");
    } // end Admin

    @Override
    public String menu() {
        System.out.println("\nAdmin Menu");
        System.out.println("0) Exit this menu");
        System.out.println("1) Full customer report");
        System.out.println("2) Add user");
        System.out.println("3) Apply interest to savings accounts");
        System.out.print("\nAction: ");
        return User.input.nextLine();
    } // end menu

    // The Bank class owns the customer list and performs admin operations.
    @Override
    public void start() {
    } // end start

    @Override
    public String getReport() {
        return "Admin: " + getUserName();
    } // end getReport
}
