package vendingmachine.repository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Money;
import vendingmachine.domain.MoneyStorage;
import vendingmachine.util.CoinGenerator;

public class CoinRepository {
    private LinkedHashMap<Coin, Integer> coinRepository = new LinkedHashMap<>();

    public CoinRepository() {
        coinRepository.clear();
        Arrays.stream(Coin.values()).forEach(coin -> coinRepository.put(coin, 0));
    }

    public LinkedHashMap<Coin, Integer> putCoinInVendingMachine(Money moneyVendingMachineHave) {
        LinkedHashMap<Coin, Integer> coins = CoinGenerator.makeCoins(moneyVendingMachineHave);
        Arrays.stream(Coin.values())
            .forEach(coin -> coinRepository.put(coin, coinRepository.get(coin) + coins.get(coin)));
        return coins;
    }

    public LinkedHashMap<Coin, Integer> giveChange(MoneyStorage moneyStorage) {
        LinkedHashMap<Coin, Integer> coins = new LinkedHashMap<>();
        int remainingMoneyValue = moneyStorage.getMoney().getValue();
        // 비싼거부터 하나씩 꺼낸다.
        for (Coin coin : Coin.values()) {
            int giveCnt = remainingMoneyValue / coin.getAmount();
            int haveCnt = coinRepository.get(coin);
            if (haveCnt < giveCnt) {
                giveCnt = haveCnt;
            }
            if (giveCnt == 0) {
                continue;
            }
            coins.put(coin, giveCnt);
            remainingMoneyValue -= coin.getAmount() * giveCnt;
        }
        return coins;
    }
}
