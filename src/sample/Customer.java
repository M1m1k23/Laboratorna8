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

    // Використовується тільки для створення компаній
    public Customer(String name, String email, Account account, double companyOverdraftDiscount) {
        this.name = name;
        this.email = email;
        this.customerType = CustomerType.COMPANY;
        this.account = account;
        this.companyOverdraftDiscount = companyOverdraftDiscount;
    }

    public void withdraw(double sum, String currency) {
        if (!account.getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }

        boolean isCompany = customerType == CustomerType.COMPANY;
        account.withdraw(sum, isCompany, companyOverdraftDiscount);
    }

    // Гетери, сетери та методи друку інформації про клієнта

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
        return name + " " + surname + " Account: IBAN: " + account.getIban() + ", Days Overdrawn: " + account.getDaysOverdrawn();
    }

    public String printCustomerMoney() {
        return name + " " + surname + " Account: IBAN: " + account.getIban() + ", Money: " + account.getMoney();
    }

    public String printCustomerAccount() {
        return "Account: IBAN: " + account.getIban() + ", Money: " + account.getMoney() + ", Account type: " + account.getType();
    }
}
