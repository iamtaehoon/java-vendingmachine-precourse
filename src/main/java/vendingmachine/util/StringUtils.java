package vendingmachine.util;

import static vendingmachine.Constant.*;

public class StringUtils {
    public static int convertStringToInt(String input) {
        if (!input.matches(CHECK_NUMBER_REGEX)) {
            throw new IllegalArgumentException("숫자를 입력해 주세요.");
        }
        return Integer.parseInt(input);
    }
}
