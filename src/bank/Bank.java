package bank;

import java.util.AbstractCollection;
import java.util.ArrayList;

public class Bank {
    public static void main(String[] args) {
        ArrayList bankTransactions = new ArrayList();

        Account src = new Account();
        src.getAccount(src);
        System.out.println(src);



        Account dst = new Account();
        dst.getAccount(dst);
        System.out.println(dst);

        new Thread(new Transaction(src, dst, 34, bankTransactions)).start();

    }
    public void transferMoney(Account src, Account dst, int money){
        Thread transaction = new Thread(new Transaction(src, dst, money));
        transaction.start();
    }
}
