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
        this.type = type;
        this.daysOverdrawn = daysOverdrawn;
    }

    public double bankCharge() {
        double result = 4.5;
        result += overdraftCharge();
        return result;
    }

    private double overdraftCharge() {
        if (type.isPremium()) {
            double result = 10;
            if (daysOverdrawn > 7) {
                result += (daysOverdrawn - 7) * 1.0;
            }
            return result;
        } else {
            return daysOverdrawn * 1.75;
        }
    }

    public void withdraw(double amount, String currency, CustomerType customerType, double discount) {
        if (!this.currency.equals(currency)) {
            throw new RuntimeException("Cannot withdraw in " + currency + " from account with currency " + this.currency);
        }

        if (type.isPremium()) {
            handlePremiumAccountWithdrawal(amount, customerType, discount);
        } else {
            handleNormalAccountWithdrawal(amount, customerType, discount);
        }
    }

    private void handlePremiumAccountWithdrawal(double amount, CustomerType customerType, double discount) {
        if (money < 0) {
            money -= amount + calculateOverdraftFee(amount, customerType, discount / 2);
        } else {
            money -= amount;
        }
    }

    private void handleNormalAccountWithdrawal(double amount, CustomerType customerType, double discount) {
        if (money < 0) {
            money -= amount + calculateOverdraftFee(amount, customerType, discount);
        } else {
            money -= amount;
        }
    }

    private double calculateOverdraftFee(double amount, CustomerType customerType, double discount) {
        double feeRate = type.isPremium() ? 0.10 : 0.20;
        return amount * feeRate * (customerType == CustomerType.COMPANY ? discount : 1);
    }

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
