import java.util.Scanner;

public class Banking {
    private String name;
    private double balance;

    Scanner scanner = new Scanner(System.in);

    public Banking(String name, double balance){
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

    public void initializeAccount(Banking object){
        System.out.printf("%n%nNew Account Name: ");
        String temp1 = scanner.next();
        //input.nextLine();
        object.setName(temp1);
        System.out.printf("%nNew Account's Initial Balance: ");
        double temp2 = scanner.nextDouble();
        scanner.nextLine();
        object.setBalance(temp2);
    }

}