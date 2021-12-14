package vendingmachine.repository;

import java.util.HashMap;

import vendingmachine.domain.Price;
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

    public Price checkCanPurchase(String productName, Price userInsertAmount) {
        Product product = checkProductExist(productName);
        checkHaveSufficientAmount(userInsertAmount, product.getPriceValue());
        checkProductHaveStock(productName);
        product.decreaseStock();
        return userInsertAmount.decreaseAmount(product.getPriceValue());
    }

    private void checkProductHaveStock(String productName) {
        Product product = productRepository.get(productName);
        if (product.getQuantityValue() <= 0) {
            throw new IllegalArgumentException("해당 상품은 재고가 존재하지 않습니다.");
        }
    }

    private Product checkProductExist(String productName) {
        if (!productRepository.containsKey(productName)) {
            throw new IllegalArgumentException("해당 상품은 존재하지 않습니다.");
        }
        return productRepository.get(productName);
    }

    private void checkHaveSufficientAmount(Price userInsertAmount, int productPrice) {
        if (productPrice > userInsertAmount.getValue()) {
            throw new IllegalArgumentException("남아있는 금액으로 해당 제품을 구매할 수 없습니다.");
        }
    }
}
