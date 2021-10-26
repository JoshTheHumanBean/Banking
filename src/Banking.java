import org.jetbrains.annotations.NotNull;
import java.util.Scanner;

public class Banking {
    private String firstName;
    private String lastName;
    private double balance;
    private String pin;

    public static Scanner input = new Scanner(System.in);

    public Banking(){
        this.firstName = "null";

        this.lastName = "null";

        this.balance = 0;

        this.pin = "0000";
    }

    public Banking(String firstName, String lastName, double balance, String pin){
        this.firstName = firstName;

        this.lastName = lastName;

        if (balance > 0.0) this.balance = balance;

        int length = pin.length();

        if (length == 4) this.pin = pin;

    }

    public static void initializeAccount(Banking object){
        System.out.printf("Please enter new account details%n");
        Formatting.blankSpace(1);
        System.out.print("New Account's First Name: ");
        object.setFirstName(input.next());
        Formatting.blankSpace(1);
        System.out.print("New Account's Last Name: ");
        object.setLastName(input.next());
        Formatting.blankSpace(1);
        System.out.print("New Account's Initial Balance: ");
        object.setBalance(input.nextDouble());
        Formatting.blankSpace(1);
        System.out.print("New Account Pin Number: ");
        object.setPin(input.next());
        Formatting.blankSpace(1);
    }

    public static void printInfo(@NotNull Banking object){
        System.out.printf("%s's balance: $%.2f%n",
                object.getName(), object.getBalance());

    }

    public static void promptDeposit(@NotNull Banking object){
        System.out.printf("For security reasons, please enter %s's pin number: ", object);
        if (object.checkPin()) {

            System.out.printf("Enter deposit amount for %s: ",
                    object.getName());
            object.deposit(Banking.input.nextDouble());
            Formatting.blankSpace(1);
        }

        else {
           System.out.printf("Invalid pin entered; Action canceled%n");
        }
    }

    public void deposit(double depositAmount){
        if (depositAmount > 0.0) balance += depositAmount;
    }

    public static void promptWithdrawal(@NotNull Banking object){

        System.out.printf("Enter withdrawal amount for %s: ",
                object.getName());
        object.withdraw(Banking.input.nextDouble());
        Formatting.blankSpace(1);

    }

    public void withdraw(double withdrawalAmount){
        if (withdrawalAmount > 0.0) balance -= withdrawalAmount;
    }

    public double getBalance(){
        return balance;
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getName(){
        return firstName + " " + lastName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPin(String pin) {
        this.pin = pin;

        int length = pin.length();

        if (length == 4) this.pin = pin;

        else {
            Formatting.blankSpace(1);
            System.out.printf("%s is an invalid pin; Default pin '0000' set", pin);
            Formatting.blankSpace(1);
        }
    }

    public Boolean checkPin(){

        if (input.next().equals(pin)) {
            return true;
        }

        else {
            return false;
        }
    }

}