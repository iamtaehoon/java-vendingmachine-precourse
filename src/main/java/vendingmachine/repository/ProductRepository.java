package vendingmachine.repository;

import java.util.HashMap;

import vendingmachine.domain.Money;
import vendingmachine.domain.Product;
import vendingmachine.domain.Quantity;

public class ProductRepository {
    private HashMap<String, Product> productRepository = new HashMap<>();

    public void addProducts(HashMap<String, Product> products) {
        productRepository.putAll(products);
    }

    public void checkCanBuyProduct(Money inputAmount) {
        productRepository.keySet()
            .stream()
            .filter(productName -> getPrice(productName) <= inputAmount)
            .anyMatch(productName -> productRepository.get(productName).getQuantity() > new Quantity(0));
    }

    private Money getPrice(String productName) {
        return productRepository.get(productName).getPrice();
    }
}
