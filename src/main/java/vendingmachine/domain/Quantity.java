package vendingmachine.domain;

import static vendingmachine.Constant.*;

public class Quantity {
    private int value;

    public Quantity(int value) {
        validate(value);
        this.value = value;
    }

    private void validate(int value) {
        validateItIsPositive(value);
        validateExceedMaxQuantity(value);
    }

    private void validateExceedMaxQuantity(int value) {
        if (value > QUANTITY_MAX_VALUE) {
            throw new IllegalArgumentException("상품의 수량은 100개를 넘을 수 없습니다.");
        }
    }

    private void validateItIsPositive(int value) {
        if (value < QUANTITY_MIN_VALUE) {
            throw new IllegalArgumentException("상품의 수량은 0보다 작을 수 없습니다.");
        }
    }

    public int getValue() {
        return value;
    }

    public void decrease() {
        if (value <= 0) {
            throw new IllegalArgumentException("상품의 수량은 0보다 작을 수 없습니다.");
        }
        this.value -= 1;
    }
}
