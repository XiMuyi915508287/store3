/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.stock;

import com.store.data.DataDao;
import com.store.data.goods.GoodsData;
import com.store.data.stock.StockDao;
import com.store.data.stock.StockData;
import com.store.ui.TabUIType;
import com.store.ui.common.table.JEditTable;
import com.store.ui.common.panel.UIEditJPanel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chenjingjun
 */
public class StockJPanel extends UIEditJPanel{

    public StockJPanel() {
       super(new StockModel(), true);
    }

    @Override
    protected JEditTable newJTable() {
        return new StockJTable(this);
    }
    
     @Override
    protected void initBtnComponent(){
        super.initBtnComponent(); 
        enableExJButton("总资产金额");
    }
    
    @Override
    protected void onClickExButton(int index) {
        Map<String, Double> map = new HashMap<>();
        List<StockData> daList = getJTable().getDataList();
        Double caculate = 0.0;
        for(StockData data : daList){
            Double value = map.get(data.getType());
            if (value == null) {
                value = 0.0;
            }
            Double amount = data.getGetPrice() * data.getNum();
            value += amount;
            caculate += amount;
            map.put(data.getType(), value);
        }
        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            buffer.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
        }
        updateJTextArea("计算总额:" + caculate + " (人民币)" , buffer.toString());
    }
    

    @Override
    protected DataDao<StockData> getDataCatalog() {
        return StockDao.getInstance();
    }

    @Override
    public TabUIType getTabUIType() {
        return TabUIType.Stock;
    }
}
