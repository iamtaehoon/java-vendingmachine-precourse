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

    public void putCoinInVendingMachine(LinkedHashMap<Coin, Integer> coins) {
        Arrays.stream(Coin.values())
            .forEach(coin -> coinRepository.put(coin, coinRepository.get(coin) + coins.get(coin)));
    }
    //금액이 들어오면 -> 동전을 만들어서 넣어준다. / 잘못된 금액인 경우 service 단계에서 잘라준다.

}
