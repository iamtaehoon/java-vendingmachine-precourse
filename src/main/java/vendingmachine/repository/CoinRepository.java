package vendingmachine.repository;

import java.util.Collections;
import java.util.LinkedHashMap;

import vendingmachine.domain.Coin;
import vendingmachine.view.OutputView;

public class CoinRepository {
    private LinkedHashMap<Coin, Integer> coinRepository = new LinkedHashMap<>();

    public void putCoins(LinkedHashMap<Coin, Integer> coins) {
        coins.keySet().stream().forEach(coin -> coinRepository.put(coin, coins.get(coin)));
        OutputView.showCoinsInVendingMachine(this);
    }

    @Override
    public String toString() {
        StringBuffer temp = new StringBuffer();
        coinRepository.keySet()
            .stream()
            .forEach(coin -> temp.append(coin.getAmount() + "원 - " + coinRepository.get(coin) + "개\n"));
        return temp.toString();
    }
}
