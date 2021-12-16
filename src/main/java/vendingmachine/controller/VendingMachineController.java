package vendingmachine.controller;

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
