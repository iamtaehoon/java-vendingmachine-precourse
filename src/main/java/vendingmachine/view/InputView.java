package vendingmachine.view;

import static vendingmachine.Constant.*;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static String putMoneyInVendingMachine() {
        System.out.println(PUT_MONEY_IN_VENDING_MACHINE_MESSAGE);
        return Console.readLine();
    }
}
