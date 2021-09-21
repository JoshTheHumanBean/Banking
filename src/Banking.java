public class Banking {
    private String name;
    private double balance;

    public Banking(String name, double balance){
        this.name = name;

        if (balance > 0.0) this.balance = balance;
    }

    public void deposit(double depositAmount){
        if (depositAmount > 0.0) balance += depositAmount;
    }

    public void withdraw(double withdrawlAmount){
        if (withdrawlAmount > 0.0) balance -= withdrawlAmount;
    }

    public double getBalance(){
        return balance;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


}