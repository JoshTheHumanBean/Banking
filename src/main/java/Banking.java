package main.java;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.util.Scanner;

public class Banking {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private double balance;
    @Expose
    private String pin;

    private int numAccounts = 0;
    private transient ArrayList<Banking> accountsList = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    String path = "accounts.json";
    private Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public Banking() {
        this.firstName = "";
        this.lastName = "";
        this.balance = 0.0;
        this.pin = "0000";
    }

    public Banking(String firstName, String lastName, double balance, String number) {
        this.firstName = firstName;
        this.lastName = lastName;
        if (balance > 0.0) this.balance = balance;
        if (pin.length() == 4) this.pin = number;
    }

    public void initializeAccount(Banking object) {
        System.out.printf("Please enter new account details%n%n");
        System.out.print("New Account's First Name: ");
        object.setFirstName(input.next());
        System.out.println();
        System.out.print("New Account's Last Name: ");
        object.setLastName(input.next());
        System.out.println();
        System.out.print("New Account's Initial Balance: ");
        object.setBalance(input.nextDouble());
        System.out.println();
        System.out.print("New Account 4 Digit Pin Number: ");
        object.setPin(input.next());
        System.out.println();
    }

    public String info() {return String.format("%s's balance: $%.2f%n", getName(), getBalance());}

    public void displayBalance(Banking object) throws NullPointerException {
        try {
            System.out.printf("For security, please enter %s's pin: ", object.getName());
            if (object.checkPin()) {System.out.print(object.info());}
            else {System.out.printf("Invalid pin entered; Action canceled%n");}
            System.out.println();
        }catch(NullPointerException n) {System.out.printf("Invalid; Action canceled%n");}
    }

    public void promptDeposit(Banking object) {
        try {
            System.out.printf("For security reasons, please enter %s's pin: ", object.getName());
            if (object.checkPin()) {
                System.out.printf("Enter deposit amount for %s: ", object.getName());
                object.deposit(input.nextDouble());
            }
            else {System.out.printf("Invalid pin entered; Action canceled%n");}
            System.out.println();
        }catch(NullPointerException n) {System.out.printf("Invalid; Action canceled%n");}
    }

    public void deposit(double depositAmount) {if (depositAmount > 0.0) balance += depositAmount;}

    public void promptWithdrawal( Banking object) {
        try {
            System.out.printf("For security reasons, please enter %s's pin: ", object.getName());
            if (object.checkPin()) {

                System.out.printf("Enter withdrawal amount for %s: ",
                        object.getName());
                object.withdraw(input.nextDouble());
            }
            else {System.out.printf("Invalid pin entered; Action canceled%n");}
            System.out.println();
        }catch(NullPointerException n) {System.out.printf("Invalid; Action canceled%n");}
    }

    public void withdraw(double withdrawalAmount) {if (withdrawalAmount > 0.0) balance -= withdrawalAmount;    }

    public double getBalance() {return balance;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getName() {return String.format("%s %s", firstName, lastName);}

    public void setBalance(double balance) {this.balance = balance;}

    public String getPin() {return pin;}

    public void setPin(String pin) {
        if (pin.length() == 4) {this.pin = pin;}
        else {System.out.printf("&n%s is an invalid pin; Previous pin restored (Default pin is '0000')%n", pin);}
    }

    public void changePin(Banking object) {
        System.out.printf("For security reasons, please enter %s's pin: ", object.getName());
        if (object.checkPin()) {
            System.out.print("Please enter the new pin you wish to use: ");
            object.setPin(input.next());
        }
    }

    public Boolean checkPin() {return input.next().equals(pin);}

    public void createAccounts(int newAccounts) {
        numAccounts += newAccounts;
        for(int i = 0; i<newAccounts; i++) {
            Banking account = new Banking();
            accountsList.add(account);
        }
    }

    public void promptEnterKey() {System.out.println("Press \"ENTER\" to continue...");input.nextLine();}

    public void initializeArray() {
        for(int i = 0; i<numAccounts; i++) {
            if (Objects.isNull(accountsList.get(i))) {
                initializeAccount(accountsList.get(i));
            }

        }
    }

    public ArrayList<Banking> getArrayList() {return accountsList;}

    public void displayArrayList() {
        for(int i = 0; i<numAccounts; i++) {
            if (!Objects.isNull(accountsList.get(i))) {
                System.out.printf("%s: %s%n", i, accountsList.get(i).getName());
            }
        }
        if(numAccounts == 0) {
            System.out.printf("Sorry, there are no active accounts%n");
        }
        System.out.println();
    }

    public Banking chooseAccount() {
        displayArrayList();
        if (numAccounts > 0) {
            System.out.print("Please choose the account you wish to use: ");
            int choice = input.nextInt();
            if (choice <= accountsList.size() - 1) {return  accountsList.get(choice);} else {return null;}
        } else {return null;}
    }

    public void serializeAccounts() throws IOException {
        try(PrintWriter out = new PrintWriter(new FileWriter(path))) {
            for(Banking account : accountsList) {out.write(gson.toJson(account));}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deserializeAccounts() throws IOException {
        try {
            FileInputStream fis = new FileInputStream("accounts.xml");
            XMLDecoder decoder = new XMLDecoder(fis);
            ArrayList<Banking> accountsOnFile = (ArrayList<Banking>) decoder.readObject();
            for(Banking account : accountsOnFile) {
                accountsList.add(account); numAccounts++;
            }
            /*
            for(int i = 0; i < decoder.count(); i++) {
                accountsList.add((Banking) decoder.readObject());
                numAccounts++;
            }
             */
            decoder.close();
            fis.close();
        } catch(java.io.FileNotFoundException file) {
            System.out.printf("File not found; Action canceled%n");
        } catch(ArrayIndexOutOfBoundsException array) {
            System.out.printf("No accounts found on file; Action canceled%n");
        }
    }

    public void mainMenu() {
        int listNum;
        int choice;
        /*try {
            System.out.printf("Deserializing accounts%n%n");
            deserializeAccounts();
        } catch (java.io.FileNotFoundException file) {
            System.out.printf("File not found; No accounts loaded in%n");
        }
        catch(ArrayIndexOutOfBoundsException array) {
            System.out.printf("No accounts found on file; Action canceled%n");
        }
        catch(IOException io) {
            System.out.printf("Error occurred while deserializing accounts, No accounts loaded in %n");
        }finally {
            System.out.printf("Deserializing finished%n%n");
        }*/
        do {
            promptEnterKey();
            try {
                listNum = 1;
                System.out.printf("----------------------%n");
                for(String s : Arrays.asList("Create and initialize new account", "Display all active accounts",
                        "Display balance", "Deposit money", "Withdraw money", "Change pin", "Save all active accounts",
                        "Reload in all accounts", "Show number of accounts", "Delete account", "Close app")) {
                    System.out.printf("(%s) %s%n", listNum, s);
                    listNum++;
                }
                System.out.printf("----------------------%n");
                System.out.print("Please enter a number: ");
                choice = input.nextInt();
                System.out.println();
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
                        catch(IOException io) {
                            System.out.printf("Error occurred while serializing accounts, No accounts saved%n");
                        }
                        break;
                    case 8:
                        try {deserializeAccounts();}
                        catch(IOException io) {
                            System.out.printf("Error occurred while deserializing accounts, No accounts loaded in %n");
                        }
                        break;
                    case 9:
                        System.out.printf("Number of Accounts: %s%n", numAccounts);
                        break;
                    case 10:
                        break;
                    case 11:
                        try {serializeAccounts();}
                        catch(IOException io) {
                            System.out.printf("Error occurred while serializing accounts, No accounts saved%n");
                        }
                        System.exit(0);
                        break;
                    default:
                        System.out.printf("Invalid choice, please try again%n");
                        break;
                }
            } catch (InputMismatchException i) {
                System.out.printf("Incorrect input type entered; Returning to main menu%n");
            }
        } while(true);
    }
}