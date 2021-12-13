package vendingmachine.util;

import static vendingmachine.Constant.*;

import java.util.ArrayList;

public class StringUtils {
    public static int convertStringToInt(String input) {
        if (!input.matches(CHECK_NUMBER_REGEX)) {
            throw new IllegalArgumentException("숫자를 입력해 주세요.");
        }
        return Integer.parseInt(input);
    }

    public static ArrayList<String> splitProducts(String input) {
        ArrayList<String> products = new ArrayList<>();
        for (String product : input.split(PRODUCT_DELIMETER, -1)) {
            products.add(product);
        }
        return products;
    }

    public static String removeBracket(String eachProductInfo) {
        if (!(eachProductInfo.charAt(0) == '[' & eachProductInfo.charAt(eachProductInfo.length() - 1) == ']')) {
            throw new IllegalArgumentException("각 상품의 정보는 [] 로 감싸져야 합니다.");
        }
        if (eachProductInfo.length() - 2 < 1) {
            throw new IllegalArgumentException("상품의 정보가 제대로 입력되지 않았습니다.");
        }
        return eachProductInfo.substring(1, eachProductInfo.length() - 2);
    }
}
