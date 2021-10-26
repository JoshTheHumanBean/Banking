public class BankingArray {
    private static int numAccounts = 0;

    public static void createAccounts(int i){
        Banking[] accounts = new Banking[numAccounts+i];
        numAccounts += i;
    }

    public static void initializeAccounts(Banking[] objects){
        for (int i =0; i<numAccounts; i++){
            if (objects[i] == null){
                Banking.initializeAccount(objects[i]);
            }
        }
    }


}
