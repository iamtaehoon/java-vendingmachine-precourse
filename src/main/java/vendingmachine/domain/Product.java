package vendingmachine.domain;

import static vendingmachine.Constant.*;

import java.util.ArrayList;

import vendingmachine.util.StringUtils;

public class Product {
    public Product(String eachProductInfo) {
        validate(eachProductInfo);
    }

    private void validate(String eachProductInfo) {
        // 1. 세 개로 나눈다.
        ArrayList<String> params = validateParamCnt(eachProductInfo);
        // 2. 이름을 검사한다.
        // 3. 금액을 검사한다.
        // 4. 개수를 검사한다.
    }

    private ArrayList<String> validateParamCnt(String eachProductInfo) {
        ArrayList<String> temp = new ArrayList<>();
        String[] productInfo = StringUtils.splitProductInfo(eachProductInfo);
        if (productInfo.length != PRODUCT_INFO_CNT) {
            throw new IllegalArgumentException("상품의 정보가 제대로 입력되지 않았습니다.");
        }
        for (String detail : productInfo) {
            temp.add(detail);
        }
        return temp;
    }
}
