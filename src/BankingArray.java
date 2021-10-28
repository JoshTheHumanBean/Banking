import java.util.ArrayList;

public class BankingArray extends Banking{
    private static int numAccounts = 0;
    private static ArrayList<Banking> accountsList = new ArrayList<>();

    public static void createAccounts(int newAccounts){
        numAccounts += newAccounts;

        for (int i = 0; i<newAccounts; i++){
            Banking account = new Banking();
            accountsList.add(account);
        }
    }

    public static void initializeArray(){
        for (int i = 0; i<numAccounts; i++) {
            if (accountsList.get(i).getFirstName().equals("null")){
                initializeAccount(accountsList.get(i));
            }

        }
    }

    public static ArrayList<Banking> getArrayList(){
        return accountsList;
    }

    public static void displayArrayList(){
        for (int i = 0; i<numAccounts; i++) {
            if (!accountsList.get(i).getFirstName().equals("null")){
                System.out.printf("%s: %s%n", i, accountsList.get(i).getName());

            }
        }
        if(numAccounts == 0){
            System.out.printf("Sorry, there are no active accounts%n");
        }
        Formatting.blankSpace(1);
    }

    public static Banking chooseAccount(){
        displayArrayList();
        System.out.print("Please choose the account you wish to use: ");
        int choice = Banking.input.nextInt();
        if (choice <= accountsList.size() - 1){
            return  accountsList.get(choice);
        }
        else{return null;}
    }


    public static void mainMenu(){
        int choice;

        do {
            Formatting.clearScreen();
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
                    System.exit(0);
                    break;
                default:
                    System.out.printf("Invalid choice, please try again%n");
                    break;
            }
        } while (true);
    }
}
