package vendingmachine.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Price;
import vendingmachine.domain.Product;
import vendingmachine.domain.ReturnCode;
import vendingmachine.repository.CoinRepository;
import vendingmachine.repository.ProductRepository;
import vendingmachine.util.StringUtils;
import vendingmachine.utils.CoinGenerator;
import vendingmachine.view.OutputView;

public class VendingMachineService {
    private CoinRepository coinRepository;
    private ProductRepository productRepository;
    private Price userInsertAmount;

    public VendingMachineService(CoinRepository coinRepository, ProductRepository productRepository) {
        this.coinRepository = coinRepository;
        this.productRepository = productRepository;
    }

    public void makeCoins(Price price) {
        LinkedHashMap<Coin,Integer> coins = CoinGenerator.makeCoins(price);
        coinRepository.putCoins(coins);
    }

    public void putProducts(ArrayList<String> everyProductInfo) {
        HashMap<String, Product> products = makeInputToProduct(everyProductInfo);
        productRepository.add(products);

    }

    private HashMap<String, Product> makeInputToProduct(ArrayList<String> everyProductInfo) {
        HashMap<String, Product> temp = new HashMap<>();
        for (String eachProductInfo : everyProductInfo) {
            eachProductInfo = StringUtils.removeBracket(eachProductInfo);
            Product product = new Product(eachProductInfo);
            validateOverlapProduct(temp, product);
            validateOverlapProduct(temp, product);
            temp.put(product.getName(), product);
        }
        validateEvenOneProduct(temp);
        return temp;
    }

    private void validateEvenOneProduct(HashMap<String, Product> temp) {
        if (temp.keySet().stream().mapToInt(productName -> temp.get(productName).getQuantityValue()).sum() == 0) {
            throw new IllegalArgumentException("자판기에는 최소 한 개의 제품이 들어가야 합니다.");
        }
    }

    private void validateOverlapProduct(HashMap<String, Product> temp, Product product) {
        if (temp.containsKey(product.getName())) {
            throw new IllegalArgumentException("이미 자판기 내에 존재하는 상품을 넣으려 합니다.");
        }
    }

    public void putMoneyToBuyProduct(Price userInsertAmount) {
        if (productRepository.isNoProductCanPurchase(userInsertAmount)) {
            throw new IllegalArgumentException("투입 금액으로 구매할 수 있는 물건은 없습니다.");
        }
        this.userInsertAmount = userInsertAmount;
        OutputView.showUserInsertAmount(userInsertAmount);
    }

    public ReturnCode sellProduct(String productName) {
        userInsertAmount = productRepository.sellProduct(productName,userInsertAmount);
        OutputView.showUserInsertAmount(userInsertAmount);
        return productRepository.checkPurchaseIsAvailable(userInsertAmount);
    }

    public void returnCoins() {
        LinkedHashMap<Coin, Integer> coins = coinRepository.returnCoins(userInsertAmount);
        OutputView.showReturnCoins(coins);
    }
}
