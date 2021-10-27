import java.util.ArrayList;

public class BankingArray extends Banking{
    private int numAccounts = 0;
    private ArrayList<BankingArray> accountsList = new ArrayList<BankingArray>();

    public void createAccounts(int newAccounts){
        numAccounts += newAccounts;

        for (int i = numAccounts; i<numAccounts-1; i++){
            BankingArray account = new BankingArray();
            accountsList.add(account);
        }
    }


    public void initializeAccount(BankingArray object){
        System.out.printf("Please enter new account details%n");
        Formatting.blankSpace(1);
        System.out.print("New Account's First Name: ");
        object.setFirstName(input.next());
        Formatting.blankSpace(1);
        System.out.print("New Account's Last Name: ");
        object.setLastName(input.next());
        Formatting.blankSpace(1);
        System.out.print("New Account's Initial Balance: ");
        object.setBalance(input.nextDouble());
        Formatting.blankSpace(1);
        System.out.print("New Account Pin Number: ");
        object.setPin(input.next());
        Formatting.blankSpace(1);
    }


    public void initializeArray(){
        for (BankingArray bankingArray : accountsList) {
            bankingArray.initializeAccount(bankingArray);
        }
    }

    public ArrayList<BankingArray> getArrayList(){
        return accountsList;
    }

    public void displayArrayList(){
        System.out.print(accountsList.toString());
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
                    createAccounts(4);
                    initializeArray();
                    displayArrayList();
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
