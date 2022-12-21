package ua.nure.hrynko.dto;

import java.util.Objects;

public class Account extends Entity {

    private static final long serialVersionUID = 4716395168539434663L;


    private int number;

    private double balance;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account accounts = (Account) o;
        return number == accounts.number && Double.compare(accounts.balance, balance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, balance);
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "number=" + number +
                ", balance=" + balance +
                '}';
    }
}

