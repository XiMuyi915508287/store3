/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.goods;

import com.store.data.DataDao;
import com.store.data.bill.BillDao;
import com.store.data.bill.BillData;
import com.store.data.goods.GoodsCache;
import com.store.data.goods.GoodsData;
import com.store.data.stock.StockDao;
import com.store.data.stock.StockData;
import com.store.id.IdGenerator;
import com.store.id.IdType;
import com.store.ui.TabUIType;
import com.store.ui.common.panel.UIInputJPanel;
import com.store.ui.common.table.JEditTable;
import com.store.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chenjingjun
 */
public class EntryBillJPanel extends UIInputJPanel<GoodsData>{       
         
    private Long Order = null;
    
    public EntryBillJPanel() {
       super(new EntryBillModel());
    }

    @Override
    protected JEditTable<GoodsData> newJTable() {
        return new EntryBillJTable(this);
    }

    @Override
    protected DataDao<GoodsData> getDataCatalog() {
        return GoodsCache.getInstance();
    }

    @Override
    public TabUIType getTabUIType() {
        return TabUIType.EntryBill;
    }
    
    @Override
    protected void onNewData(GoodsData data) {
        if (Order == null) {
            Order = IdGenerator.getInstance().generateId(IdType.BILL);
        }
        data.setOrder(Order);
    }
    
     /**
     * 处理确认之后的事情
     * @param daCollection 
     */
    @Override
    protected void onConfirm(List<GoodsData> daCollection){
        List<Long> goodIds = new ArrayList<>();
        double amount = 0;
        double caculate = 0;
        for (GoodsData good : daCollection) {
            goodIds.add(good.getId());
            amount += good.getAmount();
            caculate += (good.getNum() * good.getPrice());
        }
        BillData data = BillDao.getInstance().getBuilder().constructor();
        data.setOrder(Order);
        data.setAmount(amount);
        data.setReceived(0.0);
        data.setRemain(amount);
        data.setCaculate(caculate);
        data.setDate(daCollection.get(0).getDate());
        data.setApproach(daCollection.get(0).getApproach());
        data.setUser(daCollection.get(0).getUser());
        data.setGoods(StringUtil.join(goodIds, ","));
        List<BillData> updates = new ArrayList<>();
        updates.add(data);
        BillDao.getInstance().update(updates);
    }
}
