package vendingmachine;

public abstract class Constant {
    public static String NUMBER_REGEX = "^-?[0-9]*$";
    public static String PRODUCT_INFO_DELIMETER = ";";
    public static String PRODUCT_DETAIL_DELIMETER= ",";

    public static char LEFT_BRACKET = '[';
    public static char RIGHT_BRACKET = ']';

    public static int MIN_COIN_VALUE = 10;
    public static int PRODUCT_PRICE_MIN_VALUE = 100;
    public static int PRODUCT_NAME_MAX_SIZE = 10;

    public static int PRODUCT_DETAIL_CNT = 3;
    public static int PRODUCT_NAME_IDX = 0;
    public static int PRODUCT_PRICE_IDX = 1;
    public static int PRODUCT_QUANTITY_IDX = 2;



}
