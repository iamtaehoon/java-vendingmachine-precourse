package vendingmachine.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Price;

class CoinGeneratorTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 10, 100, 70, 180, 230, 990, 1000, 1980, 40000, 310200, 999000})
    @DisplayName("CoinGenerator에 넣은 금액과 만들어진 동전의 합이 같아야 한다.")
    void 넣은금액_만들어진_동전의_합_같은지_검증(int inputMoney) {
        LinkedHashMap<Coin, Integer> coins = CoinGenerator.makeCoins(new Price(inputMoney));

        Assertions.assertThat(coins.keySet().stream().mapToInt(coin -> coin.getAmount() * coins.get(coin)).sum())
            .isEqualTo(inputMoney);

    }
}