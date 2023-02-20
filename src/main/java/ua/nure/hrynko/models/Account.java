package ua.nure.hrynko.models;

import java.util.Objects;

public class Account extends Entity {

    private static final long serialVersionUID = 4716395168539434663L;

    private double balance;

    private String fileName;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {this.balance = balance;}

    public String getFileName() {return fileName;}

    public void setFileName(String fileName) {this.fileName = fileName;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Double.compare(account.balance, balance) == 0 && fileName.equals(account.fileName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(balance, fileName);
    }
    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", fileName='" + fileName + '\'' +
                '}';
    }
}

