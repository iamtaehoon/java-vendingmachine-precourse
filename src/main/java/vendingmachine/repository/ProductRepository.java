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
        checkHaveSufficientAmount(userInsertAmount, product);
        checkProductHaveStock(productName);
        product.decreaseStock();
        return userInsertAmount.decreaseAmount();
    }

    private void checkHaveSufficientAmount(Price userInsertAmount, Product product) {
        if (product.getPriceValue() > userInsertAmount.getValue()) {
            // 남아있는 userInputMoney로 구매 가능한지 확인한다.
            throw new IllegalArgumentException();
        }
    }
}
