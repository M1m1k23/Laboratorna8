package sample;

public class AccountType {
    // Інтерфейс для поведінки акаунтів
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
            return money;  // Без преміуму overdraft не дозволений
        }
    }

    public static class PremiumAccountType implements AccountTypeBehavior {
        @Override
        public boolean isPremium() {
            return true;
        }

        @Override
        public double applyOverdraft(double money) {
            return money - 0.25;  // Преміум акаунт з overdraft
        }
    }
}

