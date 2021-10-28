public class BankingTest {
    public static void main(String[] args) {

        Banking account1 = new Banking("Braden", "Hines", 50.75, "1234");
        Banking account2 =  new Banking("Timothy", "Syfert", 20.00, "4321");

        Banking.printInfo(account1);
        Banking.printInfo(account2);
        Formatting.blankSpace(1);

        Banking.promptDeposit(account1);
        Formatting.promptEnterKey();

        Banking.printInfo(account1);
        Banking.printInfo(account2);
        Formatting.blankSpace(1);
        Formatting.promptEnterKey();

        Banking.promptWithdrawal(account2);

        Banking.printInfo(account1);
        Banking.printInfo(account2);
        Formatting.blankSpace(1);
        Formatting.promptEnterKey();

        Banking account3 = new Banking();

        Banking.initializeAccount(account3);

        Banking.printInfo(account1);
        Banking.printInfo(account2);
        Banking.printInfo(account3);
        Formatting.blankSpace(1);

        Banking.promptDeposit(account3);

        Banking.printInfo(account1);
        Banking.printInfo(account2);
        Banking.printInfo(account3);
        Formatting.blankSpace(1);

        Banking.promptDecision(account3);

        Banking.printInfo(account1);
        Banking.printInfo(account2);
        Banking.printInfo(account3);
        Formatting.blankSpace(1);
    }
}