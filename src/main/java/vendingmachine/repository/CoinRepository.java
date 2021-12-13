package vendingmachine.repository;

import java.util.LinkedHashMap;

import vendingmachine.domain.Coin;

public class CoinRepository {
    private LinkedHashMap<Coin, Integer> coinRepository = new LinkedHashMap<>();

    public void putCoins(LinkedHashMap<Coin, Integer> coins) {
        coins.keySet().stream().forEach(coin -> coinRepository.put(coin, coins.get(coin)));
        // coinRepository.keySet()
        //     .stream()
        //     .forEach(coin -> System.out.println(coin.getAmount() + "원 동전 개수 : " + coins.get(coin)));
    }
}
