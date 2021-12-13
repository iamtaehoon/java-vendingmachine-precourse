package vendingmachine.utils;

import java.util.Arrays;
import java.util.LinkedHashMap;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Price;

public class CoinGenerator {
    static LinkedHashMap<Coin, Integer> coins = new LinkedHashMap<>();

    public static LinkedHashMap<Coin, Integer> makeCoins(Price price) {
        int totalMoney = price.getValue();
        initialize(coins);

        makeCoinsUntilSameAsInput(totalMoney);
        return coins;
    }

    private static void makeCoinsUntilSameAsInput(int totalMoney) {
        while (totalMoney > 0) {
            int selectedCoinValue = Randoms.pickNumberInList(Arrays.asList(500, 100, 50, 10));
            Coin selectedCoin = Coin.valueOf(selectedCoinValue);
            if (selectedCoinValue <= totalMoney) {
                totalMoney -= selectedCoinValue;
                coins.put(selectedCoin, coins.get(selectedCoin) + 1);
            }
        }
    }

    private static void initialize(LinkedHashMap<Coin, Integer> temp) {
        temp.clear();
        Arrays.stream(Coin.values()).forEach(coin -> coins.put(coin, 0));
    }
}
