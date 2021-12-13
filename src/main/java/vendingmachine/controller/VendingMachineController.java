package vendingmachine.controller;

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
        putMoneyInVendingMachine();
    }

    private void putMoneyInVendingMachine() {
        try {
            int inputAccount = StringUtils.convertStringToInt(InputView.putMoneyInVendingMachine());
            Price price = new Price(inputAccount);
            vendingMachineService.makeCoins(price);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            putMoneyInVendingMachine();
        }

    }
}
