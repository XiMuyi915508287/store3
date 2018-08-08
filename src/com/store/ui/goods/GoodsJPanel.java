/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.goods;

import com.store.data.goods.GoodsDao;
import com.store.data.goods.GoodsData;
import com.store.ui.TabUIType;
import com.store.ui.common.DateTableCellRenderer;
import com.store.ui.common.table.JEditTable;
import com.store.ui.common.panel.UIEditJPanel;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author chenjingjun
 */
public class GoodsJPanel extends UIEditJPanel<GoodsData>{

    public GoodsJPanel() {
        super(new GoodsModel(), false);
    }

    
    @Override
    protected void initBtnComponent(){
        super.initBtnComponent(); 
        getEditButtons().enable(false);
        enableExJButton("总金额");
    }
    
    @Override
    protected void onClickExButton(int index) {
        Map<String, Double> map = new HashMap<>();
        List<GoodsData> daList = getJTable().getDataList();
        Double caculate = 0.0;
        for(GoodsData data : daList){
            Double value = map.get(data.getApproach());
            if (value == null) {
                value = 0.0;
            }
            value += data.getAmount();
            caculate += data.getAmount();
            map.put(data.getApproach(), value);
        }
        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            buffer.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
        }
        updateJTextArea("计算总额:" + caculate + " (人民币)" , buffer.toString());
    }
    
    
    @Override
    protected void initJTableComponent(){
        super.initJTableComponent();
        getJTable().setDefaultRenderer(Timestamp.class, new DateTableCellRenderer());
    }

    @Override
    protected JEditTable newJTable() {
        return new GoodsJTable(this);
    }

   @Override
    protected GoodsDao getDataCatalog() {
        return GoodsDao.getInstance();
    }

    @Override
    public TabUIType getTabUIType() {
        return TabUIType.Goods;
    }
}
