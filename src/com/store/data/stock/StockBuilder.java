/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.stock;

import com.store.data.DataBuilder;
import com.store.id.IdType;

/**
 *
 * @author chenjingjun
 */
public class StockBuilder extends DataBuilder<StockData>{

    public StockBuilder() {
        super(StockData.DATA_DETAIL, IdType.STOCK);
    }

    @Override
    public StockData _constructor() {
         return new StockData();
    }

    @Override
    protected String _valid(StockData data) {
        if (data.getGetPrice() >= data.getSalePrice()) {
            return "销售价 必须大于 进货价";
        }
        return null;
    }
}
