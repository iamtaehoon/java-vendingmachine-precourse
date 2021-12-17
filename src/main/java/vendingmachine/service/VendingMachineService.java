package vendingmachine.service;

import static vendingmachine.ErrorMessage.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Money;
import vendingmachine.domain.Product;
import vendingmachine.repository.CoinRepository;
import vendingmachine.repository.ProductRepository;
import vendingmachine.util.ProductTransformer;

public class VendingMachineService {
    private CoinRepository coinRepository;
    private ProductRepository productRepository;
    private Money remainingMoney;

    public VendingMachineService(CoinRepository coinRepository, ProductRepository productRepository) {
        this.coinRepository = coinRepository;
        this.productRepository = productRepository;
    }

    public LinkedHashMap<Coin, Integer> putMoneyByAdmin(String moneyInput) {
        Money moneyVendingMachineHave = new Money(moneyInput);
        return coinRepository.putCoinInVendingMachine(moneyVendingMachineHave);
    }

    public void putProductsByAdmin(String productInfoNotProcessing) {
        HashMap<String, Product> productsInfo = ProductTransformer.makeProducts(productInfoNotProcessing);
        if (haveNoStock(productsInfo) & productRepository.haveNoStock()) {
            throw new IllegalArgumentException(NO_STOCK_MESSAGE);
        }
        productRepository.addProducts(productsInfo);
    }

    private boolean haveNoStock(HashMap<String, Product> productsInfo) {
        return productsInfo.keySet()
            .stream()
            .mapToInt(productName -> productsInfo.get(productName).getQuantity().getValue())
            .sum() == 0;
    }

    public void saveUserInputMoney(Money inputMoney) {
        if (!productRepository.canBuyProduct(inputMoney)) {
            throw new IllegalArgumentException(NO_MONEY_SO_CANT_BUY_MESSAGE);
        }
        this.remainingMoney = inputMoney;
    }

    public Money buyProduct(String productName) {
        if (!productRepository.hasProduct(productName)) {
            throw new IllegalArgumentException(PRODUCT_NAME_EMPTY_ERROR);
        }
        Money usedMoney = productRepository.buyProduct(productName);
        remainingMoney.minus(usedMoney);
        return remainingMoney;
    }

    public boolean canBuyProduct() {
        return productRepository.canBuyProduct(remainingMoney);
    }

    public LinkedHashMap<Coin, Integer> giveChange() {
        return coinRepository.giveChange(remainingMoney);
    }
}
