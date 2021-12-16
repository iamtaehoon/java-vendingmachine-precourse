package vendingmachine.controller;

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
        putMoneyByAdmin();
        putProductsByAdmin();
        putMoneyToBuyProduct();
        buyProductUntilEnd(); //투입금액: .... 이거 outputView
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
            vendingMachineService.putMoneyByAdmin(InputView.enterMoneyVendingMachineHave());
        } catch (IllegalArgumentException e) {
            OutputView.showErrorMessage(e);
            putMoneyByAdmin();
        }
    }
}
