package vendingmachine.service;

import static vendingmachine.ErrorMessage.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Money;
import vendingmachine.domain.MoneyStorage;
import vendingmachine.domain.Product;
import vendingmachine.repository.CoinRepository;
import vendingmachine.repository.ProductRepository;
import vendingmachine.util.ProductTransformer;

public class VendingMachineService {
    private CoinRepository coinRepository;
    private ProductRepository productRepository;
    private MoneyStorage moneyStorage;

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
        if (haveNoStock(productsInfo) & productRepository.isEmpty()) {
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

    public void saveUserInputMoney(MoneyStorage moneyStorage) {
        if (!productRepository.canBuyProduct(moneyStorage.getMoney())) {
            throw new IllegalArgumentException(NO_MONEY_SO_CANT_BUY_MESSAGE);
        }
        this.moneyStorage = moneyStorage;
    }

    public MoneyStorage buyProduct(String productName) {
        if (!productRepository.hasProduct(productName)) {
            throw new IllegalArgumentException(PRODUCT_NAME_EMPTY_ERROR);
        }
        Money usedMoney = productRepository.buyProduct(productName);
        moneyStorage.use(usedMoney);
        return moneyStorage;
    }

    public boolean canBuyProduct() {
        return productRepository.canBuyProduct(moneyStorage.getMoney());
    }

    public LinkedHashMap<Coin, Integer> giveChange() {
        return coinRepository.giveChange(moneyStorage);
    }
}
