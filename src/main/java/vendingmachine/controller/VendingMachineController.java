package vendingmachine.controller;

import java.util.ArrayList;

import vendingmachine.domain.Price;
import vendingmachine.domain.ReturnCode;
import vendingmachine.service.VendingMachineService;
import vendingmachine.util.StringUtils;
import vendingmachine.view.InputView;

public class VendingMachineController {
    private VendingMachineService vendingMachineService;
    private ReturnCode returnCode = ReturnCode.CONTINUE;

    public VendingMachineController(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    public void run() {
        makeChangeInVendingMachine();
        putProductsInVendingMachine();
        putMoneyToBuyProduct();
        buyProductsUntilEnd();
    }

    private void buyProductsUntilEnd() {
        while (returnCode == ReturnCode.CONTINUE) {
            buyProduct();
        }
    }

    private void buyProduct() {
        try {
            String productName = InputView.putProductToPurchase();
            vendingMachineService.sellProduct(productName);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            buyProduct();
        }
    }

    private void putMoneyToBuyProduct() {
        try {
            int inputMoney = StringUtils.convertStringToInt(InputView.putMoneyToBuyProduct());
            vendingMachineService.putMoneyToBuyProduct(new Price(inputMoney));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            putMoneyToBuyProduct();
        }

    }

    private void putProductsInVendingMachine() {
        try {
            ArrayList<String> everyProductInfo = StringUtils.splitProducts(InputView.putProductsInVendingMachine());
            vendingMachineService.putProducts(everyProductInfo);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            putProductsInVendingMachine();
        }
    }

    private void makeChangeInVendingMachine() {
        try {
            int inputAccount = StringUtils.convertStringToInt(InputView.putMoneyInVendingMachineByAdmin());
            Price price = new Price(inputAccount);
            vendingMachineService.makeCoins(price);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            makeChangeInVendingMachine();
        }
    }
}
