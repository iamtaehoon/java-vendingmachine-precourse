package vendingmachine.domain;

import static vendingmachine.Constant.*;

import java.util.ArrayList;

import vendingmachine.util.StringUtils;

public class Product {
    private String name;
    private Price price;
    private Quantity quantity;

    public Product(String eachProductInfo) {
        initialize(eachProductInfo);
    }

    private void initialize(String eachProductInfo) {
        ArrayList<String> params = validateParamCnt(eachProductInfo);
        this.name = validateName(params.get(PRODUCT_NAME_IDX));
        this.price = validatePrice(params.get(PRODUCT_PRICE_CNT));
        this.quantity = validateQuantity(params.get(PRODUCT_QUANTITY_CNT));
    }


    private Quantity validateQuantity(String quantityInput) {
        quantityInput = validateItIsEmpty(quantityInput);
        int quantity = StringUtils.convertStringToInt(quantityInput);
        return new Quantity(quantity);
    }

    private Price validatePrice(String priceInput) {
        priceInput = validateItIsEmpty(priceInput);
        int price = StringUtils.convertStringToInt(priceInput);
        if (price < 100) {
            throw new IllegalArgumentException("상품의 가격이 100원 미만인 값이 있습니다.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("상품의 가격은 0 보다 커야 합니다.");
        }
        return new Price(price);
    }

    private String validateName(String name) {
        name = validateItIsEmpty(name);
        if (name.length() > 5) {
            throw new IllegalArgumentException("상품의 이름 중 다섯 글자를 초과하는 이름이 있습니다.");
        }
        return name;
    }

    private String validateItIsEmpty(String name) {
        name = name.trim();
        if (name.isEmpty()) {
            throw new IllegalArgumentException("입력된 정보에 공백이 있습니다.");
        }
        return name;
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

    public String getName() {
        return name;
    }

    public int getPriceValue() {
        return price.getValue();
    }

    public int getQuantityValue() {
        return quantity.getValue();
    }
}
