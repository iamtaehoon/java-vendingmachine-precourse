package vendingmachine.domain;

public class MoneyStorage {
    private Money money;

    public MoneyStorage(String moneyInput) {
        this.money = new Money(moneyInput);
    }

    public Money getMoney() {
        return money;
    }
}
