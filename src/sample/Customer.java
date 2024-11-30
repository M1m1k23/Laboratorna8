package sample;

public class Customer {

    private String firstName;
    private String lastName;
    private String email;
    private CustomerType customerType;
    private Account account;

    public Customer(String firstName, String lastName, String email, CustomerType customerType, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.customerType = customerType;
        this.account = account;
    }
    public String printCustomer() {
        return firstName + " " + lastName + " - " + email;
    }
    public boolean withdraw(double amount) {
        return account.withdraw(amount);
    }

    public String printCustomerMoney() {
        return "Account: IBAN: " + account.getIban() + ", Money: " + account.getMoney();
    }

    public String printCustomerAccount() {
        return "Account: IBAN: " + account.getIban() + ", Money: " + account.getMoney() +
                ", Account type: " + (account.getCustomer().isPremium() ? "premium" : "normal");
    }

    public String printCustomerDaysOverdrawn() {
        return firstName + " " + lastName + " Account: " + account.getIban() + ", Days Overdrawn: " + account.getMoney();
    }

    public boolean isPremium() {
        return account.getCustomer().isPremium();
    }
}

