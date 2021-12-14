package vendingmachine.view;

import java.util.LinkedHashMap;
import java.util.Map;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Price;
import vendingmachine.repository.CoinRepository;

public class OutputView {

    public static void showCoinsInVendingMachine(CoinRepository coinRepository) {
        System.out.println("\n자판기가 보유한 동전");
        System.out.println(coinRepository);
    }

    public static void showUserInsertAmount(Price userInsertAmount) {
        System.out.println(String.format("\n투입 금액: %d원", userInsertAmount.getValue()));
    }

    public static void showReturnCoins(LinkedHashMap<Coin, Integer> coins) {
        System.out.println("잔돈");
        for (Coin coin : coins.keySet()) {
            System.out.println(coin.getAmount() + "원 - " + coins.get(coin) + "개");
        }
    }

    public static void showErrorMessage(IllegalArgumentException e) {
        System.out.println("[ERROR] "+e.getMessage());
    }
}
