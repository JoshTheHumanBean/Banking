import java.util.Scanner;

public class BankingTest {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int currentAccount = 1;

        Banking account1 = new Banking("Braden Hines", 50.75);
        Banking account2 =  new Banking("Timothy Syfert", 20.00);

        Banking.printInfo(account1);
        Banking.printInfo(account2);

        System.out.printf("%n%nEnter deposit amount for %s: ",
                account1.getName());
        double depositAmount = scanner.nextDouble();
        account1.deposit(depositAmount);

        Banking.printInfo(account1);
        Banking.printInfo(account2);

        System.out.printf("%n%nEnter withdrawal amount for %s: ",
                account2.getName());
        double withdrawalAmount = scanner.nextDouble();
        account2.withdraw(withdrawalAmount);

        Banking.printInfo(account1);
        Banking.printInfo(account2);

        System.out.printf("%n%nPlease enter new account details");
        Banking account3 = new Banking();

        System.out.printf("%n%nNew Account Name: ");
        account3.setName(scanner.next());

        System.out.printf("%nNew Account's Initial Balance: ");
        account3.setBalance(scanner.nextDouble());

        Banking.printInfo(account1);
        Banking.printInfo(account2);
        Banking.printInfo(account3);


    
    }
}