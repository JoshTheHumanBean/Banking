import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class Banking {
    private String name;
    private double balance;

    public static Scanner input = new Scanner(System.in);

    public Banking(){
        this.name = "null";

        this.balance = 0;
    }

    public Banking(String name, double balance){
        this.name = name;

        if (balance > 0.0) this.balance = balance;
    }

    public static void promptDeposit(@NotNull Banking object){

        System.out.printf("%n%nEnter deposit amount for %s: ",
                object.getName());
        object.deposit(Banking.input.nextDouble());

    }

    public void deposit(double depositAmount){
        if (depositAmount > 0.0) balance += depositAmount;
    }

    public static void promptWithdrawal(@NotNull Banking object){

        System.out.printf("%n%nEnter withdrawal amount for %s: ",
                object.getName());
        object.withdraw(Banking.input.nextDouble());

    }

    public void withdraw(double withdrawalAmount){
        if (withdrawalAmount > 0.0) balance -= withdrawalAmount;
    }

    public double getBalance(){
        return balance;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public static void printInfo(@NotNull Banking object){
        System.out.printf("%n%s's balance: $%.2f",
                object.getName(), object.getBalance());

    }
    public static void initializeAccount(Banking object){
        System.out.printf("%n%nPlease enter new account details");
        System.out.printf("%n%nNew Account Name: ");
        object.setName(input.next());
        System.out.printf("%nNew Account's Initial Balance: ");
        object.setBalance(input.nextDouble());
    }

}