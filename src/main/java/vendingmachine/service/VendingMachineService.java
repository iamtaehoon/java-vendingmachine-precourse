package vendingmachine.service;

import java.util.HashMap;

import vendingmachine.domain.Money;
import vendingmachine.domain.Product;
import vendingmachine.repository.CoinRepository;
import vendingmachine.repository.ProductRepository;
import vendingmachine.util.ProductTransformer;
import vendingmachine.util.StringUtil;

public class VendingMachineService {
    private CoinRepository coinRepository;
    private ProductRepository productRepository;

    public VendingMachineService(CoinRepository coinRepository, ProductRepository productRepository) {
        this.coinRepository = coinRepository;
        this.productRepository = productRepository;
    }

    public void putMoneyByAdmin(String moneyInput) {
        Money moneyVendingMachineHave = new Money(moneyInput);
        coinRepository.putCoinInVendingMachine(moneyVendingMachineHave);
    }

    public void putProductsByAdmin(String productInfoNotProcessing) {
        HashMap<String, Product> productsInfo = ProductTransformer.preProcessing(productInfoNotProcessing);
        productRepository.addProducts(productsInfo);
    }
}
