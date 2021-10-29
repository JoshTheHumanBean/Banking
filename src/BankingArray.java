import java.util.ArrayList;
import java.io.*;
import java.util.InputMismatchException;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

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

    public static void serializeAccounts() throws IOException{
        FileOutputStream fos = new FileOutputStream("accounts.xml");
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(new ExceptionListener() {
            public void exceptionThrown(Exception e) {
                System.out.println("Exception! :"+e.toString());
            }
        });
        encoder.writeObject(accountsList);
        encoder.close();
        fos.close();
    }

    public static void deserializeAccounts() throws IOException{
        FileInputStream fis = new FileInputStream("accounts.xml");
        XMLDecoder decoder = new XMLDecoder(fis);
        ArrayList<Banking> decodedAccounts = (ArrayList<Banking>) decoder.readObject();
        decoder.close();
        fis.close();
        accountsList.addAll(decodedAccounts);
    }



    public static void mainMenu(){
        int choice;
            do {
                try {
                    Formatting.clearScreen();
                    System.out.printf("----------------------%n");
                    System.out.printf("(1) Create new account%n");
                    System.out.printf("(2) Display all active accounts%n");
                    System.out.printf("(3) Display balance%n");
                    System.out.printf("(4) Deposit money%n");
                    System.out.printf("(5) Withdraw money%n");
                    System.out.printf("(6) Save all active accounts%n");
                    System.out.printf("(7) Load in all accounts%n");
                    System.out.printf("(8) Close app%n");
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
                            try {serializeAccounts();}
                            catch(IOException ie) {ie.printStackTrace();}
                            break;
                        case 7:
                            try {deserializeAccounts();}
                            catch(IOException ie) {ie.printStackTrace();}
                            break;
                        case 8:
                            System.exit(0);
                            break;
                        default:
                            System.out.printf("Invalid choice, please try again%n");
                            break;
                    }
                } catch (InputMismatchException i) {
                    System.out.printf("Incorrect input type entered; Exiting main menu%n");
                    break;
                }
            } while (true);
    }
}
