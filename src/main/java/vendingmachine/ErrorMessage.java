package vendingmachine;

public abstract class ErrorMessage {
    public static String MONEY_NOT_POSITIVE_ERROR = "금액은 음수가 될 수 없습니다.";
    public static String QUANTITY_NOT_POSITIVE_ERROR = "재고는 0보다 작을 수 없습니다.";
    public static String MONEY_UNIT_ERROR = "금액은 10으로 나누어 떨어져야 합니다.";

    public static String PRODUCT_PRICE_LESS_ERROR = "상품의 가격은 100원 이상이어야 합니다.";
    public static String PRODUCT_NAME_EMPTY_ERROR = "상품의 이름이 존재하지 않습니다.";
    public static String PRODUCT_NAME_TOO_LONG_ERROR = "상품의 이름이 너무 깁니다.";

    public static String INVALID_PRODUCT_INFO_MESSAGE = "상품 정보를 제대로 입력하세요.";
    public static String NOT_PACKAGING_MESSAGE = "각 상품은 [ 와 ] 로 포장되어야 합니다.";

    public static String NO_MONEY_SO_CANT_BUY_MESSAGE = "이 금액으로는 살 수 있는 물건이 존재하지 않습니다.";
    public static String NO_STOCK_MESSAGE = "재고가 존재하지 않습니다.";
}
