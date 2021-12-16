package vendingmachine.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Money;

public class CoinGenerator {
    static LinkedHashMap<Coin, Integer> coins = new LinkedHashMap<>();

    public static LinkedHashMap<Coin,Integer> makeCoins(Money money) {

        int moneyValue = initialize(money);
        while (moneyValue != 0) {
            moneyValue = makeCoinUntilSpendAllMoney(moneyValue);
        }
        return coins;
    }

    private static int initialize(Money money) {
        coins.clear();
        Arrays.stream(Coin.values()).forEach(coin -> coins.put(coin, 0));
        return money.getValue();
    }

    private static int makeCoinUntilSpendAllMoney(int moneyValue) {
        Coin randomCoin = makeRandomCoin();
        int randomCoinValue = randomCoin.getAmount();
        if (randomCoinValue <= moneyValue) {
            coins.put(randomCoin, coins.get(randomCoin)+1);
            moneyValue -= randomCoinValue;
        }
        return moneyValue;
    }

    protected static Coin makeRandomCoin() {
        int randomCoinValue = Randoms.pickNumberInList(Arrays.asList(10, 50, 100, 500));
        return Coin.find(randomCoinValue);
    }
}
