import java.util.ArrayList;

public class BankingArray extends Banking{
    private static int numAccounts = 0;
    private static ArrayList<Banking> accountsList = new ArrayList<Banking>();

    public static void createAccounts(int newAccounts){
        numAccounts += newAccounts;

        for (int i = 0; i<numAccounts; i++){
            Banking account = new Banking();
            accountsList.add(account);
        }
    }

    public static void initializeArray(){
        for (int i = 0; i<numAccounts; i++) {
            if (accountsList.get(i).getFirstName() == "null"){
                initializeAccount(accountsList.get(i));
            }

        }
    }

    public static ArrayList<Banking> getArrayList(){
        return accountsList;
    }

    public static void displayArrayList(){
        for (int i = 0; i<numAccounts; i++) {
            if (accountsList.get(i).getFirstName() != "null"){
                System.out.printf("%s: %s", i, accountsList.get(i).getName());

            }
        }
        Formatting.blankSpace(2);
    }

    public static Banking chooseAccount(){
        displayArrayList();
        System.out.print("Please choose the account you wish to use: ");
        return  accountsList.get(Banking.input.nextInt());
    }


    public static void mainMenu(){

        int choice;

        System.out.printf("Welcome%n");

        do {
            System.out.printf("----------------------%n");
            System.out.printf("(1) Create new account%n");
            System.out.printf("(2) Display all active accounts%n");
            System.out.printf("(3) Display balance%n");
            System.out.printf("(4) Deposit money%n");
            System.out.printf("(5) Withdraw money%n");
            System.out.printf("(6) Close app%n");
            System.out.printf("----------------------%n");
            System.out.print("Please enter a number: ");
            choice = input.nextInt();
            Formatting.blankSpace(1);

            switch (choice) {
                case 1:
                    createAccounts(1);
                    initializeArray();
                    break;
                case 2:
                    displayArrayList();
                    break;
                case 3:
                    displayBalance(chooseAccount());
                    break;
                case 4:
                    promptDeposit(chooseAccount());
                    break;
                case 5:
                    promptWithdrawal(chooseAccount());
                    break;
                case 6:
                    break;
                default:
                    break;
            }

        } while (choice != 5);

        System.exit(0);
    }
}
