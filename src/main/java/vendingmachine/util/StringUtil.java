package vendingmachine.util;

import static vendingmachine.Constant.*;

public class StringUtil {
    public static int parseStringToInt(String input) {
        if (!input.matches(NUMBER_REGEX)) {
            throw new IllegalArgumentException("숫자가 입력되어야 합니다.");
        }
        return Integer.parseInt(input);
    }

    public static boolean isEmpty(String name) {
        name = name.trim();
        return name.isEmpty();
    }
}
