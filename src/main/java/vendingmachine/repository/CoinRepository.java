package vendingmachine.repository;

import java.util.Arrays;
import java.util.LinkedHashMap;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Money;

public class CoinRepository {
    private LinkedHashMap<Coin, Integer> coinRepository = new LinkedHashMap<>();

    public CoinRepository() {
        coinRepository.clear();
        Arrays.stream(Coin.values()).forEach(coin -> coinRepository.put(coin, 0));
    }
}
