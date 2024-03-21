package com.pe.nttdata.bootcamp.bootcoin.commons;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum OperationEnum {

    OK("OK"),
    ERROR("ERROR"),
    SEND("SEND"),
    RECEIVE("RECEIVE"),
    YANKI("YANKI"),
    BOOTCOIN("BOOTCOIN"),
    TRANSFER("TRANSFER");

    private String value;

    OperationEnum(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public OperationEnum fromValue(String value) {
        for (OperationEnum e : OperationEnum.values()) {
            if (e.value.equalsIgnoreCase(value)) {
                return e;
            }
        }
        throw new IllegalArgumentException(value);
    }

    @Override
    public String toString() {
        return this.value;
    }

}
