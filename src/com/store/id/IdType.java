/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.id;

/**
 *
 * @author chenjingjun
 */
public enum IdType {
    STOCK(1, "库存", 1, 0, ""),
    BILL(2, "订单", 1, 0, ""),
    SUPPLIRE(3, "供应", 1, 0, ""),
    USER(4, "用户", 1, 100, ""),
    GOODS(5, "物品", 1, 0, ""),
    ;

    public final int typeId;
    public final String name;
    public final int step;
    public final int initValue;
    public final String describe;

    IdType(int typeId, String name, int step, int initValue, String describe) {
            this.typeId = typeId;
            this.name = name;
            this.step = step;
            this.initValue = initValue;
            this.describe = describe;
    }
}
