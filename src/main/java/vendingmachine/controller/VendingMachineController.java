package vendingmachine.controller;

import java.util.ArrayList;

import vendingmachine.domain.Price;
import vendingmachine.service.VendingMachineService;
import vendingmachine.util.StringUtils;
import vendingmachine.view.InputView;

public class VendingMachineController {
    private VendingMachineService vendingMachineService;

    public VendingMachineController(VendingMachineService vendingMachineService) {
        this.vendingMachineService = vendingMachineService;
    }

    public void run() {
        makeChangeInVendingMachine();
        putProductsInVendingMachine();
        putMoneyToBuyProduct();
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
