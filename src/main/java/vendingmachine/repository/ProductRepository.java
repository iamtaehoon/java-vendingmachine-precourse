package vendingmachine.repository;

import java.util.HashMap;

import vendingmachine.domain.Price;
import vendingmachine.domain.Product;
import vendingmachine.domain.ReturnCode;

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

    public Price sellProduct(String productName, Price userInsertAmount) {
        Product product = validateProductCanBuy(productName, userInsertAmount);
        product.decreaseStock();
        return userInsertAmount.decreaseAmount(product.getPriceValue());
    }

    private Product validateProductCanBuy(String productName, Price userInsertAmount) {
        Product product = checkProductExist(productName);
        checkHaveSufficientAmount(userInsertAmount, product.getPriceValue());
        checkProductHaveStock(productName);
        return product;
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

    public ReturnCode checkPurchaseIsAvailable(Price userInsertAmount) {
        //모든 상품의 개수가 0이면 끝
        boolean isEmptyVendingMachine = isEmptyVendingMachine();
        boolean noProductCanPurchase = isNoProductCanPurchase(userInsertAmount);

        // System.out.println("noProductCanPurchase = " + noProductCanPurchase);
        // System.out.println("isEmptyVendingMachine = " + isEmptyVendingMachine);
        // System.out.println(ReturnCode.valueOf(isEmptyVendingMachine || noProductCanPurchase));
        boolean isReturn = isEmptyVendingMachine || noProductCanPurchase;

        return ReturnCode.valueOf(isReturn);
    }

    public boolean isNoProductCanPurchase(Price userInsertAmount) {
        int moneyUserHave = userInsertAmount.getValue();
        boolean noProductCanPurchase = productRepository.keySet()
            .stream()
            .filter(name -> productRepository.get(name).getPriceValue() <= moneyUserHave)
            .allMatch(name -> productRepository.get(name).getQuantityValue() == 0);
        return noProductCanPurchase;
    }

    private boolean isEmptyVendingMachine() {
        boolean isEmptyVendingMachine = productRepository.keySet()
            .stream()
            .allMatch(name -> productRepository.get(name).getQuantityValue() == 0);
        return isEmptyVendingMachine;
    }
}
