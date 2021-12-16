package vendingmachine.domain;

import static vendingmachine.ErrorMessage.*;

public class MoneyStorage {
    private Money money;

    public MoneyStorage(String moneyInput) {
        this.money = new Money(moneyInput);
    }

    public Money getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return Integer.toString(money.getValue());
    }

    public void use(Money usedMoney) {
        money.minus(usedMoney);
    }
}
