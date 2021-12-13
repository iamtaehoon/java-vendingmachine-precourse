package vendingmachine.utils;

import java.util.Arrays;
import java.util.LinkedHashMap;

import camp.nextstep.edu.missionutils.Randoms;
import vendingmachine.domain.Coin;
import vendingmachine.domain.Price;

public class CoinGenerator {
    static LinkedHashMap<Coin, Integer> temp = new LinkedHashMap<>();

    public static LinkedHashMap<Coin, Integer> makeCoins(Price price) {
        int totalMoney = price.getValue();
        System.out.println(totalMoney);
        initialize(temp);

        while (totalMoney > 0) {
            int selectedCoinValue = Randoms.pickNumberInList(Arrays.asList(500, 100, 50, 10));
            System.out.println(selectedCoinValue);
            Coin selectedCoin = Coin.valueOf(selectedCoinValue);
            if (selectedCoinValue <= totalMoney) {
                totalMoney -= selectedCoinValue;
                temp.put(selectedCoin, temp.get(selectedCoin) + 1);
            }
        }
        return temp;
    }

    private static void initialize(LinkedHashMap<Coin, Integer> temp) {
        temp.clear();
        Arrays.stream(Coin.values()).forEach(coin -> temp.put(coin, 0));
    }
}
