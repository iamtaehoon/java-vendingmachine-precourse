package vendingmachine.view;

import vendingmachine.domain.MoneyStorage;

public class OutputView {
    public static void showErrorMessage(IllegalArgumentException e) {
        System.out.println("[ERROR] " + e.getMessage());
    }

    public static void showRemainingAmount(MoneyStorage moneyStorage) {
        System.out.println("\n투입 금액: " + moneyStorage);
    }
}
