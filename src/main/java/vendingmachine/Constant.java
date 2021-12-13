package vendingmachine;

public abstract class Constant {
    public static String PUT_MONEY_IN_VENDING_MACHINE_MESSAGE = "자판기가 보유하고 있는 금액을 입력해 주세요.";
    public static String PUT_PRODUCTS_IN_VENDING_MACHINE_MESSAGE = "상품명과 가격, 수량을 입력해 주세요.";
    public static String CHECK_NUMBER_REGEX = "^[0-9]*$";
    public static Integer COIN_MIN_VALUE = 10;

    public static String PRODUCT_DELIMETER = ";";

}
