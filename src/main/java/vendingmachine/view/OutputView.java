package vendingmachine.view;

import java.util.Map;

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
}
