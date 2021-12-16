package vendingmachine.domain;

import static vendingmachine.Constant.*;
import static vendingmachine.ErrorMessage.*;

import vendingmachine.util.StringUtil;

public class Money {
    private int value;

    public Money(String input) {
        int value = StringUtil.parseStringToInt(input);
        if (value < 0) {
            throw new IllegalArgumentException(MONEY_NOT_POSITIVE_ERROR);
        }
        if (value % MIN_COIN_VALUE != 0) {
            throw new IllegalArgumentException(MONEY_UNIT_ERROR);
        }
        this.value = value;

    }

    public int getValue() {
        return value;
    }
}
