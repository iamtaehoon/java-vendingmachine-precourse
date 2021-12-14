package vendingmachine.domain;

public enum ReturnCode {
    CONTINUE, END;

    public static ReturnCode valueOf(boolean isReturn) {
        if (isReturn) {
            return END;
        }
        return CONTINUE;
    }
}
