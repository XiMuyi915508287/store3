/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.goods;

import com.pjfun.common.eval.TExprParams;
import com.store.data.FieldReader;
import com.store.data.IDataChange;
import com.store.data.stock.StockDao;
import com.store.data.stock.StockData;
import java.util.Map;

/**
 *
 * @author chenjingjun
 */
public class DataChangeGoods implements IDataChange{

    /**
     */
    private final String Name;

    public DataChangeGoods(String Name) {
        this.Name = Name;
    }
    
    @Override
    public Object getValue(TExprParams exprParams) {
        Map<String, Object> map = exprParams.getAllParams();
        String stockName = map.get("name").toString();
        StockData data =  StockDao.getInstance().get(stockName);
        return FieldReader.getValue(data, Name);
    }

    @Override
    public boolean containKeys(String Name) {
        return "Name".equals(Name);
    }
}
