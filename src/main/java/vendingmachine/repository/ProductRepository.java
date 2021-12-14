package vendingmachine.repository;

import java.util.HashMap;

import vendingmachine.domain.Product;

public class ProductRepository {
    private HashMap<String, Product> productRepository = new HashMap<>();
    
    public void add(HashMap<String, Product> productsNameGoingToPutIn) {
        //temp를 productRepo에 한개씩 넣는다.
        for (String productNameGoingToPutIn : productsNameGoingToPutIn.keySet()) {
            if (productRepository.containsKey(productNameGoingToPutIn)) {
                throw new IllegalArgumentException("이미 자판기 내에 존재하는 상품을 넣으려 합니다.");
            }
        }
        productRepository.putAll(productsNameGoingToPutIn);
        for (String s : productRepository.keySet()) {
            System.out.println(s+": "+productRepository.get(s).getPrice()+", "+productRepository.get(s).getQuantity());
        }
    }
}
