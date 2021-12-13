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
        int inputAccount = StringUtils.convertStringToInt(InputView.putMoneyInVendingMachine());
        Price price = new Price(inputAccount);
        // 입력받은 금액은 정상인가?
        // 정상이면 service에 넣어주자.
    }
}
