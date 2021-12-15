package vendingmachine.repository;

import java.util.Arrays;
import java.util.LinkedHashMap;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Price;
import vendingmachine.view.OutputView;

public class CoinRepository {
    private LinkedHashMap<Coin, Integer> coinRepository = new LinkedHashMap<>();

    public CoinRepository() {
        Arrays.stream(Coin.values()).forEach(coin -> coinRepository.put(coin, 0));
    }

    public void putCoins(LinkedHashMap<Coin, Integer> coins) {
        for (Coin coin : coins.keySet()) {
            coinRepository.put(coin, coinRepository.get(coin) + coins.get(coin));
        }
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

    public LinkedHashMap<Coin, Integer> returnCoins(Price userInsertAmount) {
        LinkedHashMap<Coin, Integer> returnCoins = new LinkedHashMap<>();

        int totalMoney = userInsertAmount.getValue();
        for (Coin coin : Coin.values()) {
            int coinValue = coin.getAmount();

            int giveCoinCnt = totalMoney / coinValue;
            int havingCoinCnt = coinRepository.get(coin);

            if (havingCoinCnt < giveCoinCnt) {
                giveCoinCnt = havingCoinCnt;
            }
            returnCoins.put(coin,giveCoinCnt);
            if (giveCoinCnt != 0) {
                totalMoney -= coinValue * giveCoinCnt;
            }
        }
        return returnCoins;
    }
}
