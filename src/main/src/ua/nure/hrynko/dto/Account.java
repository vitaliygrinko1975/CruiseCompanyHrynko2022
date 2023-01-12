package ua.nure.hrynko.dto;

import java.util.Objects;

public class Account extends Entity {

    private static final long serialVersionUID = 4716395168539434663L;

    private double balance;

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
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance);
    }
    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                '}';
    }

}

