package vendingmachine.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Money;
import vendingmachine.util.CoinGenerator;

public class CoinRepository {
    private LinkedHashMap<Coin, Integer> coinRepository = new LinkedHashMap<>();

    public CoinRepository() {
        coinRepository.clear();
        Arrays.stream(Coin.values()).forEach(coin -> coinRepository.put(coin, 0));
    }

    public void putCoinInVendingMachine(Money moneyVendingMachineHave) {
        HashMap<Coin, Integer> coins = CoinGenerator.makeCoins(moneyVendingMachineHave);
        Arrays.stream(Coin.values())
            .forEach(coin -> coinRepository.put(coin, coinRepository.get(coin) + coins.get(coin)));
        for (Coin coin : coinRepository.keySet()) {
            System.out.println(coin + ": " + coinRepository.get(coin));
        }
    }

}
