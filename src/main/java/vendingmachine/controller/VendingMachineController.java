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
        //관리자는 자판기에 금액을 넣는다.
        putMoneyByAdmin();
    }

    private void putMoneyByAdmin() {
        try {
            vendingMachineService.putMoneyByAdmin(InputView.enterMoneyVendingMachineHave());
        } catch (IllegalArgumentException e) {
            // OutputView.showErrorMessage(e);
            System.out.println(e.getMessage());
            putMoneyByAdmin();
        }
    }
}
