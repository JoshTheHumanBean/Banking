import java.util.List;

public class BankingArray extends Banking{
    private int numAccounts = 0;
    private List<BankingArray> accountsList;

    public BankingArray[] createAccounts(int newAccounts){
        BankingArray[] accounts = new BankingArray[newAccounts];
        numAccounts += newAccounts;
        for (int i = numAccounts; i<numAccounts; i++){
            accountsList.add(accounts[newAccounts]);
        }
        return accounts;

    }

    public void initializeArray(BankingArray[] objects){
        for (int i =0; i<numAccounts; i++){
            if (objects[i] == null){
                Banking.initializeAccount(objects[i]);
            }
        }
    }
    /*
    public BankingArray[] getArray(){
        return;
    }
    */
    public void displayArray(){
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
                    initializeArray(createAccounts(1));
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
