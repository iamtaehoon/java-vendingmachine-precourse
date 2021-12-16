package vendingmachine.view;

import java.util.LinkedHashMap;

import vendingmachine.domain.Coin;
import vendingmachine.domain.MoneyStorage;

public class OutputView {
    public static void showErrorMessage(IllegalArgumentException e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    public static void showRemainingAmount(MoneyStorage moneyStorage) {
        System.out.println("\n투입 금액: " + moneyStorage);
    }

    public static void showChange(LinkedHashMap<Coin, Integer> change) {
        for (Coin coin : change.keySet()) {
            System.out.println(coin + " - " + change.get(coin) + "개");
        }
    }
}
