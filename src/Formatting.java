public class Formatting {
    public static void blankSpace(int lines){
        for (int i = 1; i<=lines; i++){

            System.out.println();

        }
    }

    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Banking.input.nextLine();
    }
}
