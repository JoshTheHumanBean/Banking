public class BankingTest {
    public static void main(String[] args) {

        Banking account1 = new Banking("Braden Hines", 50.75);
        Banking account2 =  new Banking("Timothy Syfert", 20.00);

        Banking.printInfo(account1);
        Banking.printInfo(account2);

        Banking.promptDeposit(account1);

        Banking.printInfo(account1);
        Banking.printInfo(account2);

        Banking.promptWithdrawal(account2);

        Banking.printInfo(account1);
        Banking.printInfo(account2);

        Banking account3 = new Banking();

        Banking.initializeAccount(account3);

        Banking.printInfo(account1);
        Banking.printInfo(account2);
        Banking.printInfo(account3);

    }
}