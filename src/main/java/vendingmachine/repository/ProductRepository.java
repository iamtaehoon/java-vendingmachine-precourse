package vendingmachine.repository;

import java.util.HashMap;
import java.util.function.Predicate;

import vendingmachine.domain.Money;
import vendingmachine.domain.Product;
import vendingmachine.domain.Quantity;

public class ProductRepository {
    private HashMap<String, Product> productRepository = new HashMap<>();
    private Quantity noStock = new Quantity("0");

    public void addProducts(HashMap<String, Product> products) {
        productRepository.putAll(products);
    }

    public boolean canBuyProduct(Money inputAmount) { // 살 수 있는 물건이 있으면 true, 없으면 else
        return productRepository.keySet()
            .stream()
            .filter(canBuyBecauseCheap(inputAmount))//입력값이 크거나 같다. 이거 메서드로 뽑아내야함.
            .anyMatch(hasStock());
    }

    private Predicate<String> hasStock() {
        return productName -> getQuantity(productName).compareTo(noStock) == 1;
    }

    private Quantity getQuantity(String productName) {
        return productRepository.get(productName).getQuantity();
    }

    private Predicate<String> canBuyBecauseCheap(Money inputAmount) {
        return productName -> inputAmount.compareTo(getPrice(productName)) != -1;
    }

    private Money getPrice(String productName) {
        return productRepository.get(productName).getPrice();
    }
}
