package sample;

import sample.AccountType;
import sample.Customer;

public class Account {

    private AccountType accountType;
    private double money;
    private String iban;
    private String currency;
    private Customer customer;

    public Account(AccountType accountType, double money) {
        this.accountType = accountType;
        this.money = money;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getMoney() {
        return money;
    }

    public String getIban() {
        return iban;
    }

    public String getCurrency() {
        return currency;
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }

        double availableMoney = money + (accountType.isPremium() ? 0.75 : 0.0);
        if (availableMoney < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        money -= amount;
        return true;
    }

    public boolean isOverdraftAllowed() {
        return accountType.isPremium() || money >= 0;
    }
}
