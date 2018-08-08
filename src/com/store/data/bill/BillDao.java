/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.bill;

import com.store.data.DataDetail;
import com.store.data.DbDao;
import com.store.data.goods.GoodsDao;
import com.store.util.StringUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author chenjingjun
 */
public class BillDao extends DbDao<BillData>{
    
    private static final BillDao instance = new BillDao();
    
    public static BillDao getInstance(){
        return instance;
    }
    
    public BillDao() {
        super("Bill", new BillBuilder());
        Comparator<BillData> Compare = (BillData o1, BillData o2) -> {
            return (int)(o2.getDate().getTime() - o1.getDate().getTime());
        };
        setCompare(Compare);
    }

    @Override
    public DataDetail getDataDetail() {
        return BillData.DATA_DETAIL;
    }

    @Override
    public List<BillData> remove(Collection<Long> ids) {
        List<BillData> dataList =  super.remove(ids); 
        if (dataList != null) {
            List<Long> goodses = new ArrayList<>();
            for(BillData data : dataList){
                goodses.addAll(StringUtil.partToLongList(data.getGoods(), "#"));
            }
            GoodsDao.getInstance().remove(goodses);
        }
        return dataList;
    }
}
