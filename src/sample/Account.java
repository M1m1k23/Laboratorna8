package sample;

import sample.AccountType;
import sample.Customer;

public class Account {

    private AccountTypeBehavior accountTypeBehavior;
    private double money;
    private String iban;
    private String currency;
    private Customer customer;

    public Account(AccountTypeBehavior accountTypeBehavior, double money) {
        this.accountTypeBehavior = accountTypeBehavior;
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

        double availableMoney = accountTypeBehavior.applyOverdraft(money);
        if (availableMoney < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        money -= amount;
        return true;
    }

    public boolean isOverdraftAllowed() {
        return accountTypeBehavior.isPremium() || money >= 0;
    }

    public double bankcharge() {
        if (accountTypeBehavior.isPremium()) {
            return 14.5;
        } else {
            return 10.0;
        }
    }
    public String printCustomer() {
        if (customer != null) {
            return customer.printCustomer();
        } else {
            return "No customer associated with this account";
        }
    }

    public double overdraftFee() {
        if (accountTypeBehavior.isPremium()) {
            return 0.10;
        } else {
            return 0.20;
        }
    }

    public interface AccountTypeBehavior {
        boolean isPremium();
        double applyOverdraft(double money);
    }

    public static class NormalAccountType implements AccountTypeBehavior {
        @Override
        public boolean isPremium() {
            return false;
        }

        @Override
        public double applyOverdraft(double money) {
            return money;
        }
    }

    public static class PremiumAccountType implements AccountTypeBehavior {
        @Override
        public boolean isPremium() {
            return true;
        }

        @Override
        public double applyOverdraft(double money) {
            return money - 0.25;
        }
    }
}
