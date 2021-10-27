import java.util.ArrayList;

public class BankingArray extends Banking{
    private int numAccounts = 0;
    private ArrayList<BankingArray> accountsList = new ArrayList<BankingArray>();

    public BankingArray[] createAccounts(int newAccounts){
        BankingArray[] accounts = new BankingArray[newAccounts];

        for (int i = numAccounts; i<numAccounts+newAccounts-1; i++){
            accountsList.add(accounts[newAccounts]);
        }
        numAccounts += newAccounts;
        return accounts;

    }

    public void initializeArray(BankingArray[] objects){
        for (int i =0; i<numAccounts; i++){
            if (objects[i] == null){
                Banking.initializeAccount(objects[i]);
            }
        }
    }

    public ArrayList<BankingArray> getArrayList(){
        return accountsList;
    }

    public void displayArrayList(){
        System.out.print(accountsList);
    }


    public void mainMenu(){

        int choice;

        System.out.printf("Welcome%n");

        do {
            System.out.printf("----------------------%n");
            System.out.printf("(1) Create new account%n");
            System.out.printf("(2) Display balance%n");
            System.out.printf("(3) Deposit money%n");
            System.out.printf("(4) Withdraw money%n");
            System.out.printf("(5) Close app%n");
            System.out.printf("----------------------%n");
            System.out.print("Please enter a number: ");
            choice = input.nextInt();
            Formatting.blankSpace(1);

            switch (choice) {
                case 1:
                    BankingArray[] accounts = createAccounts(1);
                    initializeArray(accounts);
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    break;
            }

        } while (true);
    }
}
