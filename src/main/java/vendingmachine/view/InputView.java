package vendingmachine.view;

import static vendingmachine.Constant.*;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static String putMoneyInVendingMachineByAdmin() {
        System.out.println(PUT_MONEY_IN_VENDING_MACHINE_ADMIN_MESSAGE);
        return Console.readLine().trim();
    }

    public static String putProductsInVendingMachine() {
        System.out.println(PUT_PRODUCTS_IN_VENDING_MACHINE_MESSAGE);
        return Console.readLine().trim();
    }

    public static String putMoneyToBuyProduct() {
        System.out.println(PUT_MONEY_TO_BUY_PRODUCT_MESSAGE);
        return Console.readLine().trim();
    }
}
