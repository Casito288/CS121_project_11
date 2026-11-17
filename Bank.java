import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Bank implements HasMenu {
    private static final String DATA_FILE = "customers.dat";

    private Admin admin;
    private CustomerList customerList;

    public Bank() {
        admin = new Admin();
        customerList = new CustomerList();

        // Load saved customers. If this is the first run, create sample users.
        if (!loadCustomers()) {
            loadSampleCustomers();
            saveCustomers();
        } // end if

        start();
        saveCustomers();
    } // end Bank

    @Override
    public String menu() {
        System.out.println("\nBank Menu");
        System.out.println("0) Exit system");
        System.out.println("1) Login as admin");
        System.out.println("2) Login as customer");
        System.out.print("\nAction: ");
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
                if (admin.login()) {
                    System.out.println("Admin login successful.");
                    adminStart();
                } else {
                    System.out.println("Incorrect username or PIN.");
                }
            } else if (choice.equals("2")) {
                customerLogin();
            } else {
                System.out.println("Invalid input. Please choose 0, 1, or 2.");
            } // end if else statments
        } // end while
    } // end start

    public void adminStart() {
        boolean running = true;
        while (running) {
            String choice = admin.menu();

            if (choice.equals("0")) {
                running = false;
            } else if (choice.equals("1")) {
                fullCustomerReport();
            } else if (choice.equals("2")) {
                addUser();
            } else if (choice.equals("3")) {
                applyInterest();
            } else {
                System.out.println("Invalid input. Please choose 0, 1, 2, or 3.");
            }// end if else statments
        } // end while
    } // end adminStart

    public void customerLogin() {
        System.out.print("User name: ");
        String userName = User.input.nextLine();
        System.out.print("PIN: ");
        String pin = User.input.nextLine();

        Customer currentCustomer = null;
        for (Customer customer : customerList) {
            if (customer.login(userName, pin)) {
                currentCustomer = customer;
                break;
            } // end if
        } // end for

        if (currentCustomer == null) {
            System.out.println("Incorrect username or PIN.");
        } else {
            System.out.println("Login successful.");
            currentCustomer.start();
            saveCustomers();
        } // end if else
    } // end customerLogin

    public void fullCustomerReport() {
        System.out.println("\nFull Customer Report");
        if (customerList.isEmpty()) {
            System.out.println("There are no customers to display.");
        } else {
            for (Customer customer : customerList) {
                System.out.println(customer.getReport());
                System.out.println();
            } // end for
        } // end if else
    } // end fullCustomerReport

    public void addUser() {
        System.out.println("\nAdd User");
        System.out.print("Name: ");
        String name = User.input.nextLine();
        System.out.print("PIN: ");
        String pin = User.input.nextLine();

        if (name.trim().isEmpty() || pin.trim().isEmpty()) {
            System.out.println("Name and PIN cannot be empty.");
            return;
        } // end if

        for (Customer customer : customerList) {
            if (customer.getUserName().equalsIgnoreCase(name)) {
                System.out.println("A user with that name already exists.");
                return;
            } // end if
        } // end for

        customerList.add(new Customer(name, pin));
        System.out.println("User added successfully.");
        saveCustomers();
    } // end addUser

    public void applyInterest() {
        System.out.println("\nApplying interest to savings accounts...");
        for (Customer customer : customerList) {
            System.out.println(customer.getUserName() + ":");
            customer.getSavingsAccount().calcInterest();
        } // end for
        saveCustomers();
    } // end applyInterest

    public void loadSampleCustomers() {
        customerList.clear();

        Customer alice = new Customer("Alice", "1111");
        alice.getCheckingAccount().setBalance(1000.00);
        alice.getSavingsAccount().setBalance(1000.00);

        customerList.add(alice);
        customerList.add(new Customer("Bob", "2222"));
        customerList.add(new Customer("Cindy", "3333"));
    } // end loadSampleCustomers

    public void saveCustomers() {
        try (ObjectOutputStream output =
                     new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            output.writeObject(customerList);
        } catch (IOException e) {
            System.out.println("Could not save customer data: " + e.getMessage());
        } // end try catch
    } // end saveCustomer

    public boolean loadCustomers() {
        try (ObjectInputStream input =
                     new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            Object savedData = input.readObject();

            if (savedData instanceof CustomerList) {
                customerList = (CustomerList) savedData;
                return true;
            } else if (savedData instanceof ArrayList<?>) {
                CustomerList loadedCustomers = new CustomerList();
                for (Object item : (ArrayList<?>) savedData) {
                    if (item instanceof Customer) {
                        loadedCustomers.add((Customer) item);
                    } // end if
                } // end nested for
                customerList = loadedCustomers;
                return true;
            } // end else if

            System.out.println("Saved customer data had an unexpected format. Starting with sample users.");
            return false;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load saved customer data: " + e.getMessage());
            return false;
        } // end try catch
    } // end loadCustomers

    public static void main(String[] args) {
        new Bank();
    } // end main
}
