package vendingmachine.domain;

import static vendingmachine.Constant.*;
import static vendingmachine.ErrorMessage.*;

import vendingmachine.util.StringUtil;

public class Quantity {
    private int value;

    public Quantity(String input) {
        int value = StringUtil.parseStringToInt(input);
        if (value < 0) {
            throw new IllegalArgumentException(QUANTITY_NOT_POSITIVE_ERROR);
        }
        this.value = value;

    }

}
