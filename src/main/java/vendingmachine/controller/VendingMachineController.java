package vendingmachine.controller;

import vendingmachine.domain.Price;
import vendingmachine.service.VendingMachineService;
import vendingmachine.util.StringUtils;
import vendingmachine.view.InputView;

public class VendingMachineController {
    private VendingMachineService vendingMachineService;

    public VendingMachineController(VendingMachineService vendingMachineService) {

    }

    public void run() {
        // 자판기에서 금액을 입력받는다.
        putMoneyInVendingMachine();
    }

    private void putMoneyInVendingMachine() {
        try {
            int inputAccount = StringUtils.convertStringToInt(InputView.putMoneyInVendingMachine());
            Price price = new Price(inputAccount);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            putMoneyInVendingMachine();
        }

    }
}
