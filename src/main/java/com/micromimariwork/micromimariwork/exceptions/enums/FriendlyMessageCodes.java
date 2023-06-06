package com.micromimariwork.micromimariwork.exceptions.enums;

public enum FriendlyMessageCodes  implements  IFriendlyMessageCode{
    OK(1000),
    ERROR(1001),
    SUCCESS(1002),
    PRODUCT_NOT_CREATED_EXCEPTION(1500),
    PRODUCT_SUCCESSFULLY_CREATED(1501),
    PRODUCT_NOT_FOUND_EXCEPTION(1502),
    PRODUCT_SUCCESSFULLY_FOUNDED(1503),
    FriendlyMessageCodes__PRODUCT_SUCCESSFULLY_UPDATED(1504),
    FriendlyMessageCodes__PRODUCT_SUCCESSFULLY_DELETED(1505);
    private final int value;

    FriendlyMessageCodes(int value) {
        this.value = value;
    }


    ;

    @Override
    public int getFriendlyMessageCode() {
        return value;
    }
}
