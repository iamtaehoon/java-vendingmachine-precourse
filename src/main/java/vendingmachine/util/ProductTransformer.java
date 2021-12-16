package vendingmachine.util;

import java.util.HashMap;

import vendingmachine.domain.Product;

public class ProductTransformer {
    public static HashMap<String, Product> preProcessing(String data) {
        HashMap<String, Product> temp = new HashMap<>();
        String[] everyProductInfoNotPreProcessing = data.split(";", -1);
        for (String eachProductInfoNotPreProcessing : everyProductInfoNotPreProcessing) {
            validate(eachProductInfoNotPreProcessing);
            String eachProductInfoRemovePackaging = eachProductInfoNotPreProcessing.substring(1, -1);
            String[] eachProductDetails = eachProductInfoRemovePackaging.split(",", -1);
            temp.put(eachProductDetails[0],
                new Product(eachProductDetails[0], eachProductDetails[1], eachProductDetails[2]));
        }
        return temp;
    }

    private static void validate(String eachProductInfoNotPreProcessing) {
        if (!(eachProductInfoNotPreProcessing.charAt(0) == '['
            & eachProductInfoNotPreProcessing.charAt(-1) == ']')) {
            throw new IllegalArgumentException("각 상품은 [ 와 ] 로 포장되어야 합니다.");
        }
        if (eachProductInfoNotPreProcessing.length() < 2) {
            throw new IllegalArgumentException("상품 정보를 제대로 입력하세요.");
        }
    }
}
