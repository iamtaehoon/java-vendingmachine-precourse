package vendingmachine.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static vendingmachine.ErrorMessage.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class MoneyTest {
    @ParameterizedTest
    @ValueSource(strings = {"1000", "2000", "100", "10", "4590"})
    void 금액_생성_정상(String value) {
        Money money = new Money(value);
    }

    @Test
    void 금액_음수_예외() {
        assertThatThrownBy(() -> new Money("-100")).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(MONEY_NOT_POSITIVE_ERROR);
    }

    @Test
    void 금액_10으로_나누어떨어지지_않음_예외() {
        assertThatThrownBy(() -> new Money("999")).isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining(MONEY_UNIT_ERROR);
    }
}