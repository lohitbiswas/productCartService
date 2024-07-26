package com.indusnet.ums.common;

import lombok.Getter;

@Getter
public enum MessageTypeConst {
    ERROR(0),
    SUCCESS(1),
    INTERNAL_ERROR(2),
    OTHERS(3);

    private Integer message;
    private MessageTypeConst(Integer messageType) {
        this.message=messageType;
    }
}
