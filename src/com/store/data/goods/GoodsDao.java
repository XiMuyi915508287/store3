/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.goods;

import com.store.data.DataDetail;
import com.store.data.DbDao;
import com.store.data.common.Approach;
import com.store.data.stock.StockDao;
import com.store.data.stock.StockData;
import com.store.logger.StoreLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author chenjingjun
 */
public class GoodsDao extends DbDao<GoodsData>{
    
    private static final GoodsDao instance = new GoodsDao();
    
    public static GoodsDao getInstance(){
        return instance;
    }
    
    public GoodsDao() {
        super("Goods", new GoodsBuilder());
        Comparator<GoodsData> Compare = (GoodsData o1, GoodsData o2) -> {
            return (int)(o2.getDate().getTime() - o1.getDate().getTime());
        };
        setCompare(Compare);
    }
    
    @Override
    public void reload(){
        super.reload();
        //可能会修改
        updateDb(getList());
    }

    @Override
    public DataDetail getDataDetail() {
        return GoodsData.DATA_DETAIL;
    }

    @Override
    protected void beforeUpdate(Collection<GoodsData> addList) {
        super.beforeUpdate(addList); 
        List<StockData> updateList = new ArrayList<>();
        for (GoodsData gd : addList) {
            Approach gda =  Approach.get(gd.getApproach());
            StockData data = StockDao.getInstance().get(gd.getName());
            if (data == null) {
                StoreLogger.error("<GoodsCache::beforeUpdate> error:" + gd.getName());
            }
            else{
                GoodsData current = get(gd.getId());
                Integer changeNum = 0;
                if (current == null) {
                    changeNum = gda.stockOperator * gd.Num;
                }
                else{
                     Approach cda =  Approach.get(current.getApproach());
                     changeNum = (gd.Num * gda.stockOperator) - (current.Num * cda.stockOperator);
                }
                Integer num = data.getNum();
                num += changeNum;
                data.setNum(num);
                updateList.add(data);
            }
        }
        StockDao.getInstance().update(updateList);
    }

    @Override
    protected void beforeRemove(List<GoodsData> deleteList) {
        List<StockData> updateList = new ArrayList<>();
        for (GoodsData gd : deleteList) {
            Approach a =  Approach.get(gd.getApproach());
            StockData data = StockDao.getInstance().get(gd.getName());
            if (data == null) {
                StoreLogger.error("<GoodsCache::beforeRemove> error:" + StockDao.getInstance().toString(data));
            }
            else{
                Integer num = data.getNum();
                num -= (a.stockOperator * gd.Num);
                data.setNum(num);
                updateList.add(data);
            }
        }
        super.beforeRemove(deleteList); 
        StockDao.getInstance().update(updateList);
    }
}
