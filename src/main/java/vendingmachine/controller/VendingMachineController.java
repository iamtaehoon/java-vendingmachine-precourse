package vendingmachine.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;

import vendingmachine.domain.Coin;
import vendingmachine.domain.MoneyStorage;
import vendingmachine.service.VendingMachineService;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class VendingMachineController {
    private VendingMachineService vendingMachineService;
    public VendingMachineController(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    public void run() {
        initializeVendingMachine();
        useVendingMachine();
        giveChange();
    }

    private void giveChange() {
        LinkedHashMap<Coin, Integer> change = vendingMachineService.giveChange();
        OutputView.showChange(change);
    }

    private void useVendingMachine() {
        putMoneyToBuyProduct();
        buyProductUntilEnd();
    }

    private void initializeVendingMachine() {
        putMoneyByAdmin();
        putProductsByAdmin();
    }

    private void buyProductUntilEnd() {
        while (vendingMachineService.canBuyProduct()) {
            buyProduct();
        }
    }

    private void buyProduct() {
        try {
            MoneyStorage moneyStorage = vendingMachineService.buyProduct(InputView.enterBuyingProductName());
            OutputView.showRemainingAmount(moneyStorage);
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
            buyProduct();
        }
    }

    private void putMoneyToBuyProduct() {
        try {
            MoneyStorage moneyStorage = new MoneyStorage(InputView.enterMoneyToBuyProduct());
            vendingMachineService.saveUserInputMoney(moneyStorage);
            OutputView.showRemainingAmount(moneyStorage);
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
            putMoneyToBuyProduct();
        }
    }

    private void putProductsByAdmin() {
        try {
            vendingMachineService.putProductsByAdmin(InputView.enterProductsPutInVendingMachine());
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
            putProductsByAdmin();
        }
    }

    private void putMoneyByAdmin() {
        try {
            LinkedHashMap<Coin, Integer> coins = vendingMachineService.putMoneyByAdmin(
                InputView.enterMoneyVendingMachineHave());
            OutputView.showVendingMachineHaveCoin(coins);
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
            putMoneyByAdmin();
        }
    }
}
