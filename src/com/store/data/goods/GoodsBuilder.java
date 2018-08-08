/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.goods;

import com.store.data.DataBuilder;
import com.store.data.stock.StockDao;
import com.store.data.stock.StockData;
import com.store.id.IdType;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *;
 * @author chenjingjun
 */
public class GoodsBuilder extends DataBuilder<GoodsData>{

    public GoodsBuilder() {
        super(GoodsData.DATA_DETAIL, IdType.GOODS);
    }

    @Override
    public GoodsData _constructor() {
         return new GoodsData();
    }
    
    
    @Override
    public GoodsData build(ResultSet rs) throws SQLException {
        GoodsData data = super.build(rs);
        StockData stock = StockDao.getInstance().get(data.getGoods());
        if (stock == null) {
            data.setGoodsDelete("是");
        }
        return data;
    }

    @Override
    protected String _valid(GoodsData data) {
         return null;
    }
}
