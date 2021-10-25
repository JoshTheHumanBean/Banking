import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

public class Banking {
    private int accountNumber;
    private String name;
    private double balance;

    Scanner scanner = new Scanner(System.in);

    public Banking(){
        this.name = "null";

        this.balance = 0;
    }

    public Banking(int accountNumber, String name, double balance){
        this.name = name;

        if (balance > 0.0) this.balance = balance;
    }

    public void deposit(double depositAmount){
        if (depositAmount > 0.0) balance += depositAmount;
    }

    public void withdraw(double withdrawlAmount){
        if (withdrawlAmount > 0.0) balance -= withdrawlAmount;
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

    public void setAccountNumber(int accountNumber){
        if (accountNumber > 0) this.accountNumber = accountNumber;
    }

    public static void printInfo(@NotNull Banking object){
        System.out.printf("%n%s's balance: $%.2f",
                object.getName(), object.getBalance());

    }
    /*public void initializeAccount(Banking object){
        System.out.printf("%n%nNew Account Name: ");
        String temp1 = scanner.next();
        //input.nextLine();
        object.setName(temp1);
        System.out.printf("%nNew Account's Initial Balance: ");
        double temp2 = scanner.nextDouble();
        scanner.nextLine();
        object.setBalance(temp2);
    }*/

}