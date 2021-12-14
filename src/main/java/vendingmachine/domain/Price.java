package vendingmachine.domain;

import static vendingmachine.Constant.*;

public class Price {
    private int value;

    public Price(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        validateItIsPositive(value);
        validateUnitIsCorrect(value);
    }

    private void validateUnitIsCorrect(int value) {
        if (value % COIN_MIN_VALUE != 0) {
            throw new IllegalArgumentException(String.format("금액은 %d로 나누어 떨어져야 합니다.", COIN_MIN_VALUE));
        }
    }

    private void validateItIsPositive(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("금액은 음수가 될 수 없습니다.");
        }
    }

    public int getValue() {
        return value;
    }

    public Price decreaseAmount(int usedAmount) {
        if (value < usedAmount) {
            throw new IllegalArgumentException("해당 금액만큼 존재하지 않습니다.");
        }
        this.value -= usedAmount;
        return this;
    }
}
