package org.koreait.yumyum.entity;

public enum OrderState {
    PENDING("0"),
    IN_PROGRESS("1"),
    COMPLETED("2");

    private final String code;

    OrderState(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static OrderState fromCode(String code) {
        for (OrderState state : OrderState.values()) {
            if (state.getCode().equals(code)) {
                return state;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}
