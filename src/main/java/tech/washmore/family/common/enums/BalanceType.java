package tech.washmore.family.common.enums;

/**
 * @author Washmore
 * @version V1.0
 * @summary TODO
 * @Copyright (c) 2018, Lianjia Group All Rights Reserved.
 * @since 2018/1/15
 */
public enum BalanceType {
    payment("支出", 0),
    receipt("收入", 1);

    private String name;
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    BalanceType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static BalanceType of(int value) {
        for (BalanceType balanceType : BalanceType.values()) {
            if (balanceType.getValue() == value) {
                return balanceType;
            }
        }
        throw new IllegalArgumentException(String.format("无效的参数value:%d", value));
    }
}
