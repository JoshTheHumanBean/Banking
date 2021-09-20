import java.util.Scanner;

public class BankingTest {
    public static void main(String[] args) {
        Banking account1 = new Banking("Braden Hines", 50.75);
        Banking account2 =  new Banking("Timothy Syfert", 20.00);

        System.out.printf("%s's balance: $%.2f%n",
                account1.getName(), account1.getBalance());
        System.out.printf("%s's balance: $%.2f%n",
                account2.getName(), account2.getBalance());

        Scanner input = new Scanner(System.in);

        System.out.printf("Enter deposit amount for %s: ",
                account1.getName());
        double depositAmount = input.nextDouble();
        account1.deposit(depositAmount);

        System.out.printf("%s's balance: $%.2f%n",
                account1.getName(), account1.getBalance());
        System.out.printf("%s's balance: $%.2f%n",
                account2.getName(), account2.getBalance());

        System.out.printf("Enter withdrawal amount for %s: ",
                account2.getName());
        double withdrawalAmount = input.nextDouble();
        account2.withdraw(withdrawalAmount);

        System.out.printf("%s's balance: $%.2f%n",
                account1.getName(), account1.getBalance());
        System.out.printf("%s's balance: $%.2f%n",
                account2.getName(), account2.getBalance());

        System.out.println("Please enter new account details");
        Banking account3 = new Banking(null, 0);

        System.out.printf("New Account Name: %n");
        account3.setName(input.nextLine());
        System.out.printf("New Account Initial Balance: %n");
        account3.setBalance(input.nextDouble());

        System.out.printf("%s's balance: $%.2f%n",
                account1.getName(), account1.getBalance());
        System.out.printf("%s's balance: $%.2f%n",
                account2.getName(), account2.getBalance());
        System.out.printf("%s's balance: $%.2f%n",
                account3.getName(), account3.getBalance());
    }
}