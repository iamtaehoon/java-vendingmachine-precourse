package vendingmachine.service;

import static vendingmachine.Constant.*;
import static vendingmachine.ErrorMessage.*;

import java.util.ArrayList;

import vendingmachine.domain.Price;
import vendingmachine.domain.Product;
import vendingmachine.repository.CoinRepository;
import vendingmachine.repository.ProductRepository;
import vendingmachine.utils.CoinGenerator;
import vendingmachine.utils.ProductValidator;
import vendingmachine.view.OutputView;

public class VendingMachineService {
    private CoinRepository coinRepository;
    private ProductRepository productRepository = new ProductRepository();
    private Price userMoney;

    private VendingMachineService(int initializeMoney) {
        putInitialAmount(initializeMoney);
    }

    public static VendingMachineService makeVendingMachineHasMoney(int initializeMoney) {
        return new VendingMachineService(initializeMoney);
    }

    public void putInitialAmount(int inputMoney) {
        coinRepository = new CoinRepository(CoinGenerator.makeCoins(new Price(inputMoney)));
        OutputView.showAllCoinsMachineHave(coinRepository);

    }

    public void putProducts(ArrayList<String> productsInfo) {
        productRepository.clear();
        for (String productInfoInput : productsInfo) {
            Product product = ProductValidator.validateForm(productInfoInput);
            productRepository.add(product);
        }
        if (productRepository.hasNoQuantity()) {
            throw new IllegalArgumentException(VENDING_MACHINE_NO_INITIAL_ERROR_MESSAGE);
        }
    }

    public void putUserMoney(String userMoneyInput) {
        userMoney = new Price(userMoneyInput);
        if (productRepository.cantBuyBecauseOfNoMoney(userMoney)) {
            throw new IllegalArgumentException(MONEY_LACK_ERROR_MESSAGE);
        }
        OutputView.showUserMoney(userMoney);
    }

    public void sellProduct(String productName) {
        validateProductExist(productName);
        int productPrice = productRepository.takeout(productName,userMoney);
        userMoney.use(productPrice);
        OutputView.showUserMoney(userMoney);
    }

    public boolean isContinue() {
        if (productRepository.cantBuyBecauseOfNoMoney(userMoney) || productRepository.hasNoQuantity()) {
            return false;
        }
        return true;
    }

    private void validateProductExist(String productName) {
        if (!productRepository.has(productName)) {
            throw new IllegalArgumentException(NO_PRODUCT_ERROR_MESSAGE);
        }
    }

    public void giveChange() {
        OutputView.showChange(coinRepository.giveChange(userMoney));
    }

}
