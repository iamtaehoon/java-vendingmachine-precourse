package vendingmachine.util;

import static vendingmachine.Constant.*;
import static vendingmachine.ErrorMessage.*;

import java.util.HashMap;

import vendingmachine.domain.Product;

public class ProductTransformer {
    public static HashMap<String, Product> preProcessing(String data) {
        HashMap<String, Product> temp = new HashMap<>();
        String[] everyProductInfoNotPreProcessing = data.split(PRODUCT_INFO_DELIMETER, -1);
        for (String eachProductInfoNotPreProcessing : everyProductInfoNotPreProcessing) {
            validate(eachProductInfoNotPreProcessing);
            String eachProductInfoRemoveBracket = removeBracket(eachProductInfoNotPreProcessing);
            String[] eachProductDetails = eachProductInfoRemoveBracket.split(PRODUCT_DETAIL_DELIMETER, -1);
            validateDetailsCnt(eachProductDetails);
            temp.put(eachProductDetails[PRODUCT_NAME_IDX],
                new Product(eachProductDetails[PRODUCT_NAME_IDX], eachProductDetails[PRODUCT_PRICE_IDX], eachProductDetails[PRODUCT_QUANTITY_IDX]));
        }
        return temp;
    }

    private static void validateDetailsCnt(String[] eachProductDetails) {
        if (eachProductDetails.length != PRODUCT_DETAIL_CNT) {
            throw new IllegalArgumentException(INVALID_PRODUCT_INFO_MESSAGE);
        }
    }

    private static String removeBracket(String eachProductInfoNotPreProcessing) {
        String eachProductInfoRemovePackaging = eachProductInfoNotPreProcessing.substring(1,
            eachProductInfoNotPreProcessing.length() - 1);
        return eachProductInfoRemovePackaging;
    }

    private static void validate(String eachProductInfoNotPreProcessing) {
        if (eachProductInfoNotPreProcessing.length() < 2) {
            throw new IllegalArgumentException(INVALID_PRODUCT_INFO_MESSAGE);
        }
        if (!(eachProductInfoNotPreProcessing.charAt(0) == LEFT_BRACKET
            & eachProductInfoNotPreProcessing.charAt(eachProductInfoNotPreProcessing.length()-1) == RIGHT_BRACKET)) {
            throw new IllegalArgumentException(NOT_PACKAGING_MESSAGE);
        }
    }
}
