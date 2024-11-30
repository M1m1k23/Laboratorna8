package sample;

public class Customer {

    private String name;
    private String surname;
    private String email;
    private CustomerType customerType;
    private Account account;
    private double companyOverdraftDiscount = 1;

    public Customer(String name, String surname, String email, CustomerType customerType, Account account) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.customerType = customerType;
        this.account = account;
    }

    // For creating companies with discount
    public Customer(String name, String email, Account account, double companyOverdraftDiscount) {
        this.name = name;
        this.email = email;
        this.customerType = CustomerType.COMPANY;
        this.account = account;
        this.companyOverdraftDiscount = companyOverdraftDiscount;
    }

    public void withdraw(double amount, String currency) {
        account.withdraw(amount, currency, customerType, companyOverdraftDiscount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public String printCustomerDaysOverdrawn() {
        return String.format("%s %s Account: IBAN: %s, Days Overdrawn: %d", name, surname, account.getIban(), account.getDaysOverdrawn());
    }

    public String printCustomerMoney() {
        return String.format("%s %s Account: IBAN: %s, Money: %.2f", name, surname, account.getIban(), account.getMoney());
    }

    public String printCustomerAccount() {
        return String.format("Account: IBAN: %s, Money: %.2f, Account type: %s", account.getIban(), account.getMoney(), account.getType());
    }
}
