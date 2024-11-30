package sample;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CustomerTest {

    @Test
    public void testWithdrawPersonWithNormalAccount() throws Exception {
        Account account = new Account(new Account.NormalAccountType(), 34.0);
        Customer customer = getPersonCustomer(account);
        customer.withdraw(10);
        assertThat(account.getMoney(), is(24.0));
    }

    @Test
    public void testWithdrawPersonWithPremiumAccount() throws Exception {
        Account account = new Account(new Account.PremiumAccountType(), 34.0);
        Customer customer = getPersonCustomer(account);
        customer.withdraw(10);
        assertThat(account.getMoney(), is(24.0));
    }

    private Customer getPersonCustomer(Account account) {
        Customer customer = new Customer("danix", "dan", "dan@mail.com", CustomerType.PERSON, account);
        account.setCustomer(customer);
        return customer;
    }
}