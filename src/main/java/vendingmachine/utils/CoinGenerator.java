package vendingmachine.utils;

import java.util.LinkedHashMap;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Price;

public class CoinGenerator {
    public static LinkedHashMap<Coin, Integer> makeCoins(Price price) {
        // 랜덤하게 동전을 하나 고른다.
        // ( 해당 동전의 가치 < price ) 면, price를 빼준다.
        // 그러고 동전을 저장소에 넣어준다.
        // 0이 될 때까지 반복한다.
        // 되고 나면 임시 저장소를 반환한다.
        return null;
    }
}
