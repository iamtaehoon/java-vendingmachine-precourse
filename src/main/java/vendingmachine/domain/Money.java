package vendingmachine.domain;

import static vendingmachine.Constant.*;
import static vendingmachine.ErrorMessage.*;

import vendingmachine.util.StringUtil;

public class Money implements Comparable<Money> {
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

    @Override
    public int compareTo(Money o) {
        if (this.value > o.getValue()) {
            return 1;
        } else if (this.value == o.getValue()) {
            return 0;
        } else if (this.value < o.getValue()) {
            return -1;
        }
        throw new IllegalArgumentException("로직 오류");
    }

    public void minus(Money usedMoney) {
        if (value < usedMoney.getValue()) {
            throw new IllegalArgumentException(MONEY_LACK_MESSAGE);
        }
        value -= usedMoney.getValue();
    }
}
