import java.util.ArrayList;
import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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
            if (Objects.isNull(accountsList.get(i))) {
                initializeAccount(accountsList.get(i));
            }

        }
    }

    public static ArrayList<Banking> getArrayList(){
        return accountsList;
    }

    public static void displayArrayList(){
        for (int i = 0; i<numAccounts; i++) {
            if (!Objects.isNull(accountsList.get(i))){
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
        if (numAccounts != 0){
            System.out.print("Please choose the account you wish to use: ");
            int choice = Banking.input.nextInt();
            if (choice <= accountsList.size() - 1){
                return  accountsList.get(choice);
            }
            else{return null;}
        }
        else{return null;}
    }

    public static void serializeAccounts() throws IOException{
        FileOutputStream fos = new FileOutputStream("accounts.xml");
        XMLEncoder encoder = new XMLEncoder(fos);
        encoder.setExceptionListener(e -> System.out.println("Exception! :"+e.toString()));
        encoder.writeObject(accountsList);
        encoder.close();
        fos.close();

        for (Banking object : accountsList){
            gson.toJson(object);
        }
    }

    public static void deserializeAccounts() throws IOException{
        try{
            FileInputStream fis = new FileInputStream("accounts.xml");
            XMLDecoder decoder = new XMLDecoder(fis);
            ArrayList<Banking> accountsOnFile = (ArrayList<Banking>) decoder.readObject();
            for (Banking object : accountsOnFile){
                accountsList.add(object);
                numAccounts++;
            }
            decoder.close();
            fis.close();
        }
        catch(java.io.FileNotFoundException file){
            System.out.printf("File not found; Action canceled%n");
        }
        catch(ArrayIndexOutOfBoundsException array){
            System.out.printf("No accounts found on file; Action canceled%n");
        }
    }

    public static void changePin(Banking object){
        System.out.printf("For security reasons, please enter %s's pin: ", object.getName());
        if (object.checkPin()) {
            System.out.print("Please enter the new pin you wish to use: ");
            object.setPin(input.next());
        }
    }

    public static void deleteAccount(){
        try {
            String file = "accounts.xml";
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            // Get the parent node
            Node ArrayList = doc.getFirstChild();
            // Get the employee element
            Node Banking = doc.getElementsByTagName("object").item(0);
            // Get the list of child nodes of employee
            NodeList list = Banking.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                //Remove "name" node
                if ("name".equals(node.getNodeName())) {
                    Banking.removeChild(node);
                }
            }
            // write the content to the xml file
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource src = new DOMSource(doc);
            StreamResult res = new StreamResult(new File(file));
            transformer.transform(src, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void mainMenu(){
        int listnum;
        int choice;
        try {
            System.out.printf("Deserializing accounts%n");
            Formatting.blankSpace(1);
            deserializeAccounts();
        } catch (java.io.FileNotFoundException file) {
            System.out.printf("File not found; No accounts loaded in%n");
        }
        catch(ArrayIndexOutOfBoundsException array){
            System.out.printf("No accounts found on file; Action canceled%n");
        }
        catch(IOException io){
            System.out.printf("Error occurred while deserializing accounts, No accounts loaded in %n");
        }
        System.out.printf("Deserializing finished%n");
        Formatting.blankSpace(1);
        Formatting.promptEnterKey();
        do {
                try {
                    Formatting.clearScreen();
                    listnum = 0;
                    System.out.printf("----------------------%n");
                    for (String s : Arrays.asList("Create and initialize new account", "Display all active accounts",
                            "Display balance", "Deposit money", "Withdraw money", "Change pin", "Save all active accounts",
                            "Reload in all accounts", "Show number of accounts", "Delete account", "Close app%n")){
                        listnum++;
                        System.out.printf("(%s) %s%n", listnum, s);
                    }
                    System.out.printf("----------------------%n");
                    System.out.print("Please enter a number: ");
                    choice = input.nextInt();
                    Formatting.blankSpace(1);
                    switch (choice) {
                        case 1:
                            createAccounts(1);
                            initializeAccount(accountsList.get(accountsList.size() - 1));
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
                            changePin(chooseAccount());
                            break;
                        case 7:
                            try {serializeAccounts();}
                            catch(IOException io){
                                System.out.printf("Error occurred while serializing accounts, No accounts saved%n");
                            }
                            break;
                        case 8:
                            try {deserializeAccounts();}
                            catch(IOException io){
                                System.out.printf("Error occurred while deserializing accounts, No accounts loaded in %n");
                            }
                            break;
                        case 9:
                            System.out.printf("Number of Accounts: %s%n", numAccounts);
                            break;
                        case 10:
                            deleteAccount();
                            break;
                        case 11:
                            try {serializeAccounts();}
                            catch(IOException io){
                                System.out.printf("Error occurred while serializing accounts, No accounts saved%n");
                            }
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
