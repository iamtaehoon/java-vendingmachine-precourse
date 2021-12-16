package vendingmachine.domain;

import static vendingmachine.Constant.*;
import static vendingmachine.ErrorMessage.*;

import vendingmachine.util.StringUtil;

public class Product {
    private String name;
    private Money price;
    private Quantity quantity;

    public Product(String name, String priceInput, String quantityInput) {
        validateName(name);
        Money price = new Money(priceInput);
        Quantity quantity = new Quantity(quantityInput);
        validatePrice(price);
        initialize(name, price, quantity);
    }

    private void initialize(String name, Money price, Quantity quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    private static void validatePrice(Money price) {
        if (price.getValue() < PRODUCT_PRICE_MIN_VALUE) {
            throw new IllegalArgumentException(PRODUCT_PRICE_LESS_ERROR);
        }
    }

    private static void validateName(String name) {
        if (StringUtil.isEmpty(name)) {
            throw new IllegalArgumentException(PRODUCT_NAME_EMPTY_ERROR);
        }
        if (name.length() > PRODUCT_NAME_MAX_SIZE) {
            throw new IllegalArgumentException(PRODUCT_NAME_TOO_LONG_ERROR);
        }
    }

    public Money getPrice() {
        return price;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void sell() {
        quantity.sell();
    }
}
