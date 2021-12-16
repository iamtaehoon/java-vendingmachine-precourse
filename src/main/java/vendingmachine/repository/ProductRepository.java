package vendingmachine.repository;

import java.util.HashMap;

import vendingmachine.domain.Product;

public class ProductRepository {
    private HashMap<String, Product> productRepository = new HashMap<>();

    public void addProducts(HashMap<String, Product> products) {
        productRepository.putAll(products);
    }


}
