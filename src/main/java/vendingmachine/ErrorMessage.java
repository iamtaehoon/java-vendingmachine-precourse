package vendingmachine;

public abstract class ErrorMessage {
    public static String MONEY_NOT_POSITIVE_ERROR = "금액은 음수가 될 수 없습니다.";
    public static String QUANTITY_NOT_POSITIVE_ERROR = "재고는 0보다 작을 수 없습니다.";
    public static String MONEY_UNIT_ERROR = "금액은 10으로 나누어 떨어져야 합니다.";
}
