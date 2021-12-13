package vendingmachine.domain;

import static org.junit.jupiter.api.Assertions.*;
import static vendingmachine.Constant.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PriceTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 10, 20, 100, 400, 1000, 51000, 61230, 5690})
    @DisplayName("금액이 0 이상이고, 10으로 나누어떨어지는 경우 정상적으로 반환한다.")
    void 금액_정상_로직(int inputPrice) {
        Assertions.assertThat(new Price(inputPrice).getValue()).isEqualTo(inputPrice);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -10, -29, -101, -400, -1010, -51000, -61233, -5690})
    @DisplayName("금액이 0보다 작으면 예외를 반환한다.")
    void 금액_음수_예외(int inputPrice) {
        Assertions.assertThatThrownBy(() -> new Price(inputPrice))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("금액은 음수가 될 수 없습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 7, 11, 24, 101, 124, 613, 457, 8889, 78124919})
    @DisplayName("금액이 동전의 최소단위인 10으로 나누어 떨어지지 않는 경우 예외를 반환한다.")
    void 금액_단위_예외(int inputPrice) {
        Assertions.assertThatThrownBy(() -> new Price(inputPrice))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(String.format("금액은 %d로 나누어 떨어져야 합니다.", COIN_MIN_VALUE));
    }
}