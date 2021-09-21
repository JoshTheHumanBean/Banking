import java.util.Scanner;

public class BankingTest {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int currentAccount = 1;

        Banking account1 = new Banking(currentAccount,"Braden Hines", 50.75);  currentAccount++;
        Banking account2 =  new Banking(currentAccount,"Timothy Syfert", 20.00); currentAccount++;

        System.out.printf("%s's balance: $%.2f",
                account1.getName(), account1.getBalance());
        System.out.printf("%n%s's balance: $%.2f",
                account2.getName(), account2.getBalance());

        System.out.printf("%n%nEnter deposit amount for %s: ",
                account1.getName());
        double depositAmount = scanner.nextDouble();
        account1.deposit(depositAmount);

        System.out.printf("%n%s's balance: $%.2f",
                account1.getName(), account1.getBalance());
        System.out.printf("%n%s's balance: $%.2f",
                account2.getName(), account2.getBalance());

        System.out.printf("%n%nEnter withdrawal amount for %s: ",
                account2.getName());
        double withdrawalAmount = scanner.nextDouble();
        account2.withdraw(withdrawalAmount);

        System.out.printf("%n%s's balance: $%.2f",
                account1.getName(), account1.getBalance());
        System.out.printf("%n%s's balance: $%.2f",
                account2.getName(), account2.getBalance());

        System.out.printf("%n%nPlease enter new account details");
        Banking account3 = new Banking(currentAccount,null, 0); currentAccount++;

        System.out.printf("%n%nNew Account Name: ");
        String temp1 = scanner.next();
        //input.nextLine();
        account3.setName(temp1);
        System.out.printf("%nNew Account's Initial Balance: ");
        double temp2 = scanner.nextDouble();
        scanner.nextLine();
        account3.setBalance(temp2);


        System.out.printf("%n%s's balance: $%.2f",
                account1.getName(), account1.getBalance());
        System.out.printf("%n%s's balance: $%.2f",
                account2.getName(), account2.getBalance());
        System.out.printf("%n%s's balance: $%.2f",
                account3.getName(), account3.getBalance());

    }
}