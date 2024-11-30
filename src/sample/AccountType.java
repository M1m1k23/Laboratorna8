package sample;

public class AccountType {
    private boolean premium;

    public AccountType(boolean premium) {
        this.premium = premium;
    }

    public boolean isPremium() {
        return premium;
    }
}
