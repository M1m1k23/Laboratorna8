package sample;

import sample.AccountType;
import sample.Customer;

public class Account {

    private String iban;
    private AccountType type;
    private int daysOverdrawn;
    private double money;
    private String currency;
    private Customer customer;

    public Account(AccountType type, int daysOverdrawn) {
        super();
        this.type = type;
        this.daysOverdrawn = daysOverdrawn;
    }

    public double bankcharge() {
        double result = 4.5;
        result += overdraftCharge();
        return result;
    }

    private double overdraftCharge() {
        if (type.isPremium()) {
            double result = 10;
            if (daysOverdrawn > 7)
                result += (daysOverdrawn - 7) * 1.0;
            return result;
        } else {
            return daysOverdrawn * 1.75;
        }
    }

    public double overdraftFee() {
        return type.isPremium() ? 0.10 : 0.20;
    }

    public void withdraw(double sum, boolean isCompany, double discount) {
        if (isInOverdraft()) {
            applyOverdraftFee(sum, isCompany, discount);
        } else {
            applySimpleWithdrawal(sum);
        }
    }

    private boolean isInOverdraft() {
        return money < 0;
    }

    private void applyOverdraftFee(double sum, boolean isCompany, double discount) {
        double overdraftFee = sum * overdraftFee();
        if (isCompany) {
            overdraftFee *= discount;
        }
        money -= (sum + overdraftFee);
    }

    private void applySimpleWithdrawal(double sum) {
        money -= sum;
    }

    // Гетери та сетери

    public int getDaysOverdrawn() {
        return daysOverdrawn;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public AccountType getType() {
        return type;
    }

    public String printCustomer() {
        return customer.getName() + " " + customer.getEmail();
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
