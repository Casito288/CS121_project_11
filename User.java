import java.io.Serializable;
import java.util.Scanner;

public abstract class User implements HasMenu, Serializable {
    private static final long serialVersionUID = 1L;

    // One Scanner is shared by every class that needs keyboard input.
    // It is static, so it is not included when User objects are serialized.
    public static final Scanner input = new Scanner(System.in);

    protected String userName;
    protected String PIN;

    public User() {
        this("", "");
    } // end User

    public User(String userName, String PIN) {
        this.userName = userName;
        this.PIN = PIN;
    } // end User

    public boolean login() {
        System.out.print("User name: ");
        String enteredName = input.nextLine();
        System.out.print("PIN: ");
        String enteredPIN = input.nextLine();
        return login(enteredName, enteredPIN);
    } // end login

    public boolean login(String enteredName, String enteredPIN) {
        return userName != null && PIN != null
                && userName.equals(enteredName)
                && PIN.equals(enteredPIN);
    } // end login

    public String getUserName() {
        return userName;
    } // end getUserName

    public String getPIN() {
        return PIN;
    } // end getPIN

    public void setUserName(String userName) {
        this.userName = userName;
    } // end setUserName

    public void setPIN(String PIN) {
        this.PIN = PIN;
    } // end setPIN

    @Override
    public String menu() {
        return "";
    } // end menu

    @Override
    public void start() {
        // The base User class does not have its own menu.
    } // end start

    public abstract String getReport();
} // end User class
