package vendingmachine.domain;

import static vendingmachine.Constant.*;
import static vendingmachine.ErrorMessage.*;

import vendingmachine.util.StringUtil;

public class Quantity implements Comparable<Quantity> {
    private int value;

    public Quantity(String input) {
        int value = StringUtil.parseStringToInt(input);
        if (value < 0) {
            throw new IllegalArgumentException(QUANTITY_NOT_POSITIVE_ERROR);
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(Quantity o) {
        if (this.value > o.getValue()) {
            return 1;
        } else if (this.value == o.getValue()) {
            return 0;
        } else if (this.value < o.getValue()) {
            return -1;
        }
        throw new IllegalArgumentException("로직 오류");
    }
}
