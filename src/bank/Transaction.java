package bank;

import javax.crypto.spec.PSource;
import javax.swing.*;
import java.util.ArrayList;
import java.util.TreeMap;

public class Transaction implements Runnable{
   // private BankTransaction bankTransaction;

    private Account src; // с какого аккаунта осуществлять перевод
    private Account dst; // на какой аккаунт осуществлять перевод
    private int money;// сколько переводить
    private ArrayList bankTransactions = new ArrayList<>();
    

    public Transaction(Account src, Account dst, int money) {
        this.src = src;
        this.dst = dst;
        this.money = money;

    }

    public Transaction(Account src, Account dst, int money, ArrayList bankTransactions) {
        this.src = src;
        this.dst = dst;
        this.money = money;
        this.bankTransactions = bankTransactions;
    }

    @Override
    public void run() {
        System.out.println("перевод");
        src.setBalance(src.getBalance()-money);
        System.out.println("у аккаунта " + src.getId()+ " на счете осталось " + src.getBalance());
        bankTransactions.add(money);
        System.out.println("в банке " + bankTransactions.size() + " транзакция");
        money = (int) bankTransactions.remove(0);
        dst.setBalance(dst.getBalance() + money);
        System.out.println("у аккаунта " + dst.getId() + " на счете появилось " + dst.getBalance());
    }
}
