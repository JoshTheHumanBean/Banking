import java.util.ArrayList;
import java.io.*;
import java.util.InputMismatchException;
import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import org.w3c.dom.*;
import org.xml.sax.*;
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
        try{
            FileInputStream fis = new FileInputStream("accounts.xml");
            XMLDecoder decoder = new XMLDecoder(fis);
            accountsList.addAll((ArrayList<Banking>) decoder.readObject());
            decoder.close();
            fis.close();
        }catch(java.io.FileNotFoundException file){
            System.out.printf("File not found; Action canceled%n");
        }
    }

    public static void changePin(Banking object){
        System.out.printf("For security reasons, please enter %s's pin: ", object.getName());
        if (object.checkPin()) {
            System.out.printf("Please enter the new pin you wish to use: ");
            Banking.setPin(input.next(), object);
        }
    }
    /*
    public static void deleteAccount(){
        try{
            BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
            String xmlFile = accounts.xml;
            File file = new File(xmlFile);
            System.out.print("Enter an element which have to delete: ");
            String remElement = bf.readLine();
            if (file.exists()){
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(xmlFile);
                TransformerFactory tFactory = TransformerFactory.newInstance();
                Transformer tFormer = tFactory.newTransformer();
                Element element = (Element)doc.getElementsByTagName(remElement).item(0);
                //  Remove the node
                element.getParentNode().removeChild(element);
                //  Normalize the DOM tree to combine all adjacent nodes
                doc.normalize();
                Source source = new DOMSource(doc);
                Result dest = new StreamResult(System.out);
                tFormer.transform(source, dest);
                System.out.println();
            }
            else{
                System.out.println("File not found!");
            }
        }
        catch (Exception e){
            System.err.println(e);
            System.exit(0);
        }
    }
    */
    public static void mainMenu(){
        int choice;
            do {
                try {
                    Formatting.clearScreen();
                    System.out.printf("----------------------%n");
                    System.out.printf("(1) Create and initialize new account%n");
                    System.out.printf("(2) Display all active accounts%n");
                    System.out.printf("(3) Display balance%n");
                    System.out.printf("(4) Deposit money%n");
                    System.out.printf("(5) Withdraw money%n");
                    System.out.printf("(6) Change pin%n");
                    System.out.printf("(7) Save all active accounts%n");
                    System.out.printf("(8) Load in all accounts%n");
                    System.out.printf("(9) Create null account%n");
                    System.out.printf("(10) Initialize null account%n");
                    System.out.printf("(11) Close app%n");
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
                            catch(IOException ie) {ie.printStackTrace();}
                            break;
                        case 8:
                            try {deserializeAccounts();}
                            catch(IOException ie) {ie.printStackTrace();}
                            break;
                        case 9:
                            createAccounts(1);
                            break;
                        case 10:
                            initializeAccount(chooseAccount());
                            break;
                        case 11:
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
