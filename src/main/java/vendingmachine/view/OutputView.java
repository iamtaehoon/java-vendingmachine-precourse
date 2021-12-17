package vendingmachine.view;

import java.util.LinkedHashMap;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Money;

public class OutputView {
    public static void showErrorMessage(IllegalArgumentException e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    public static void showRemainingAmount(Money remainingMoney) {
        System.out.println("\n투입 금액: " + remainingMoney+"원");
    }

    public static void showChange(LinkedHashMap<Coin, Integer> change) {
        for (Coin coin : change.keySet()) {
            System.out.println(coin + " - " + change.get(coin) + "개");
        }
    }

    public static void showVendingMachineHaveCoin(LinkedHashMap<Coin, Integer> coins) {
        System.out.println("자판기가 보유한 동전");
        OutputView.showChange(coins);

    }
}
