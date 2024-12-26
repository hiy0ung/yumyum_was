package org.koreait.yumyum.entity;

import lombok.Getter;

@Getter
public enum OrderState {
    PENDING("0"),
    IN_PROGRESS("1"),
    COMPLETED("2");

    private final String code;

    OrderState(String code) {
        this.code = code;
    }
}
