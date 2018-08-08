/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.bill;

import com.store.data.bill.BillDao;
import com.store.data.bill.BillData;
import com.store.data.common.Approach;
import com.store.ui.common.DateTableCellRenderer;
import com.store.ui.TabUIType;
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
public class BillJPanel extends UIEditJPanel<BillData>{

    public BillJPanel() {
        super(new BillModel(), true);
    }
    
    @Override
    protected void initBtnComponent(){
        super.initBtnComponent(); 
        enableExJButton("总金额");
    }
    
    @Override
    protected void onClickExButton(int index) {
        Map<String, BillCount> map = new HashMap<>();
        List<BillData> daList = getJTable().getDataList();
        BillCount caculate = new BillCount();
        for(BillData data : daList){
            BillCount count = map.get(data.getUser());
            if (count == null) {
                count = new BillCount();
                map.put(data.getUser(), count);
            }
            Approach a =  Approach.get(data.getApproach());
            count.addAmount(data.getAmount());
            count.addCaculate(data.getCaculate());
            count.addReceived(data.getReceived());
            count.addRemain(- data.getRemain() * a.stockOperator);
            
            caculate.addAmount(data.getAmount());
            caculate.addCaculate(data.getCaculate());
            caculate.addReceived(data.getReceived());
            caculate.addRemain(- data.getRemain() * a.stockOperator);
        }
        StringBuilder buffer = new StringBuilder();
        for (Map.Entry<String, BillCount> entry : map.entrySet()) {
            buffer.append(entry.getKey()).append("： ").append(toBillString(entry.getValue())).append("\n");
        }
        updateJTextArea("计算经手人详细金额" , buffer.toString());
    }
    
    /**
     * 
     * @param count
     * @return 
     */
    private String toBillString(BillCount count){
        StringBuilder builder = new StringBuilder();
        builder.append("计算金额[").append(count.getCaculate()).append("]");
        builder.append(" 应收金额[").append(count.getAmount()).append("]");
        builder.append(" 已收金额[").append(count.getReceived()).append("]");
        builder.append(" 还欠金额[").append(count.getRemain()).append("]");
        return builder.toString();
    }
    
    @Override
    protected void initJTableComponent(){
        super.initJTableComponent();
        getJTable().setDefaultRenderer(Timestamp.class,new DateTableCellRenderer());
    }

    @Override
    protected JEditTable newJTable() {
        return new BillJTable(this);
    }

   @Override
    protected BillDao getDataCatalog() {
        return BillDao.getInstance();
    }

    @Override
    public TabUIType getTabUIType() {
        return TabUIType.Bill;
    }
}
