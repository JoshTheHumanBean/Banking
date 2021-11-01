import java.util.Scanner;
import java.io.*;
public class Banking implements java.io.Serializable{
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

    public Banking(String firstName, String lastName, double balance, String number){
        this.firstName = firstName;

        this.lastName = lastName;

        if (balance > 0.0) this.balance = balance;

        if (pin.length() == 4) this.pin = number;

    }

    public static void initializeAccount( Banking object){
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
        System.out.print("New Account 4 Digit Pin Number: ");
        object.setPin(input.next());
        Formatting.blankSpace(1);
    }

    public static void printInfo( Banking object){
        System.out.printf("%s's balance: $%.2f%n",
                object.getName(), object.getBalance());

    }

    public static void displayBalance(Banking object) throws NullPointerException{
        try {
            System.out.printf("For security reasons, please enter %s's pin: ", object.getName());
            if (object.checkPin()) {
                System.out.printf("%s's balance: $%.2f%n",
                        object.getName(), object.getBalance());
            }

            else {
                System.out.printf("Invalid pin entered; Action canceled%n");
            }
            Formatting.blankSpace(1);
        }catch(NullPointerException n){System.out.printf("Invalid; Action canceled%n");}
    }

    public static void promptDeposit( Banking object) throws NullPointerException{
        try{
            System.out.printf("For security reasons, please enter %s's pin: ", object.getName());
            if (object.checkPin()) {

                System.out.printf("Enter deposit amount for %s: ",
                        object.getName());
                object.deposit(Banking.input.nextDouble());
            }

            else {
                System.out.printf("Invalid pin entered; Action canceled%n");
            }
            Formatting.blankSpace(1);
        }catch(NullPointerException n){System.out.printf("Invalid; Action canceled%n");}
    }

    public void deposit(double depositAmount){

        if (depositAmount > 0.0) balance += depositAmount;
    }

    public static void promptWithdrawal( Banking object) throws NullPointerException{
        try{
            System.out.printf("For security reasons, please enter %s's pin: ", object.getName());
            if (object.checkPin()) {

                System.out.printf("Enter withdrawal amount for %s: ",
                        object.getName());
                object.withdraw(Banking.input.nextDouble());
            }

            else {
                System.out.printf("Invalid pin entered; Action canceled%n");
            }
            Formatting.blankSpace(1);
        }catch(NullPointerException n){System.out.printf("Invalid; Action canceled%n");}

    }

    public void withdraw(double withdrawalAmount){
        if (withdrawalAmount > 0.0) balance -= withdrawalAmount;
    }

    public double getBalance(){
        return balance;
    }

    public String getFirstName(){return firstName;}

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName(){return lastName;}

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getName(){
        return firstName + " " + lastName;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPin(){return pin;}

    public void setPin(String pin) {
        if (pin.length() == 4) this.pin = pin;

        else {
            Formatting.blankSpace(1);
            System.out.printf("%s is an invalid pin; Previous pin restored (Default pin is '0000')", pin);
            Formatting.blankSpace(1);
        }
    }

    public Boolean checkPin(){
        return input.next().equals(pin);
    }

    /*
    public static void promptDecision( Banking object){

        int choice;

        System.out.printf("Welcome%n");

        do {
            System.out.printf("----------------------%n");
            System.out.printf("(1) Display %s's balance%n", object.getName());
            System.out.printf("(2) Deposit money%n");
            System.out.printf("(3) Withdraw money%n");
            System.out.printf("(4) End%n");
            System.out.printf("----------------------%n");
            System.out.print("Please enter a number: ");
            choice = input.nextInt();
            Formatting.blankSpace(1);

            switch (choice) {
                case 1:
                    Banking.printInfo(object);
                    break;
                case 2:
                    Banking.promptDeposit(object);
                    break;
                case 3:
                    Banking.promptWithdrawal(object);
                    break;
                case 4:
                    Formatting.blankSpace(1);
                    break;
                default:
                    break;
            }

        } while (choice != 4);
    }
     */

}