package vendingmachine.view;

import java.util.Map;

import vendingmachine.repository.CoinRepository;

public class OutputView {

    public static void showCoinsInVendingMachine(CoinRepository coinRepository) {
        System.out.println("\n자판기가 보유한 동전");
        System.out.println(coinRepository);
    }
}
