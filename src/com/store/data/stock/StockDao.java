/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.stock;

import com.store.data.DataDetail;
import com.store.data.DbDao;
import java.util.Comparator;

/**
 *
 * @author chenjingjun
 */
public class StockDao extends DbDao<StockData>{
    
    private static final StockDao instance = new StockDao();
    
    public static StockDao getInstance(){
        return instance;
    }
    
    public StockDao() {
        super("Stock", new StockBuilder());
        Comparator<StockData> Compare = (StockData o1, StockData o2) -> {
            return CompareName.compare(o1.getVender(), o2.getVender());
        };
        setCompare(Compare);
    }

    @Override
    public DataDetail getDataDetail() {
        return StockData.DATA_DETAIL;
    }
}
