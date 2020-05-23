package bank;

public class Account {
    private int id; // unique (1,2,3,4,5...)
    private int balance; // доступные средства на аккаунте

    public Account() {
    }

    public Account(int id, int balance) {
        setId(id);
        setBalance(balance);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }

    public void getAccount(Account account){
        account.setId((int)(Math.random() * 25));
        account.setBalance((int)(Math.random()* 3000));


    }
}
