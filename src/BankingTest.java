import java.util.Scanner;

public class BankingTest {
    public static void main(String[] args) {

        Banking account1 = new Banking("Braden Hines", 50.75);
        Banking account2 =  new Banking("Timothy Syfert", 20.00);

        Banking.printInfo(account1);
        Banking.printInfo(account2);

        System.out.printf("%n%nEnter deposit amount for %s: ",
                account1.getName());
        double depositAmount = Banking.input.nextDouble();
        account1.deposit(depositAmount);

        Banking.printInfo(account1);
        Banking.printInfo(account2);

        System.out.printf("%n%nEnter withdrawal amount for %s: ",
                account2.getName());
        double withdrawalAmount = Banking.input.nextDouble();
        account2.withdraw(withdrawalAmount);

        Banking.printInfo(account1);
        Banking.printInfo(account2);

        System.out.printf("%n%nPlease enter new account details");
        Banking account3 = new Banking();

        Banking.initializeAccount(account3);

        Banking.printInfo(account1);
        Banking.printInfo(account2);
        Banking.printInfo(account3);


    
    }
}