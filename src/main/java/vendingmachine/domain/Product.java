package vendingmachine.domain;

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
        if (price.getValue() < 100) {
            throw new IllegalArgumentException("상품의 가격은 100원 이상이어야 합니다.");
        }
    }

    private static void validateName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("상품의 이름이 존재하지 않습니다.");
        }
        if (name.length() > 10) {
            throw new IllegalArgumentException("상품의 이름이 너무 깁니다.");
        }
    }
}
