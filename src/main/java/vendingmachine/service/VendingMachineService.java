package vendingmachine.service;

import static vendingmachine.ErrorMessage.*;

import java.util.HashMap;

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
        productRepository.addProducts(productsInfo);
    }

    public void saveUserInputMoney(MoneyStorage moneyStorage) {
        if (!productRepository.canBuyProduct(moneyStorage.getMoney())) {
            throw new IllegalArgumentException(NO_MONEY_SO_CANT_BUY_MESSAGE);
        }
        this.moneyStorage = moneyStorage;
        //검증. 이 돈으로 살 수 있는 물건이 있는가. 이건 MoneyStorage의 기능.
    }

    public MoneyStorage buyProduct(String productName) {
        // 이 녀석이 실제로 있는 녀석인가 검사.
        if (!productRepository.hasProduct(productName)) {
            throw new IllegalArgumentException(PRODUCT_NAME_EMPTY_ERROR);
        }
        Money usedMoney = productRepository.buyProduct(productName);
        moneyStorage.use(usedMoney);
        return moneyStorage;
    }
}
