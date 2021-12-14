package vendingmachine.repository;

import java.util.HashMap;

import vendingmachine.domain.Product;

public class ProductRepository {
    private HashMap<String, Product> productRepository = new HashMap<>();
    
    public void add(HashMap<String, Product> productsNameGoingToPutIn) {
        productsNameGoingToPutIn.keySet().stream().forEach(productsName -> validateOverlapProduct(productsName));
        productRepository.putAll(productsNameGoingToPutIn);
    }

    private void validateOverlapProduct(String productNameGoingToPutIn) {
        if (productRepository.containsKey(productNameGoingToPutIn)) {
            throw new IllegalArgumentException("이미 자판기 내에 존재하는 상품을 넣으려 합니다.");
        }
    }
}
