package vendingmachine.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Money;

class CoinGeneratorTest {

    @Test
    void 입력한_금액만큼_동전이_만들어지는지_테스트() {
        HashMap<Coin, Integer> coins = CoinGenerator.makeCoins(new Money("10000"));
        Assertions.assertThat(coins.keySet().stream().mapToInt(coin -> coin.getAmount() * coins.get(coin)).sum())
            .isEqualTo(10000);
    }

}