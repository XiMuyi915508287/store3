/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.goods;

import com.store.data.DataDao;
import com.store.data.DataDetail;
import java.util.Collection;
import java.util.List;

/**
 * @author chenjingjun
 */
public class GoodsCache extends DataDao<GoodsData>{
    
    private static final GoodsCache instance = new GoodsCache();
     
    public static GoodsCache getInstance(){
        return instance;
    }
    
    private GoodsCache() {
        super(new GoodsBuilder());
    }
    
    @Override
    public DataDetail getDataDetail() {
        return GoodsData.DATA_DETAIL;
    }

    @Override
    protected void beforeRemove(List<GoodsData> deleteList) {
       GoodsDao.getInstance().remove(deleteList);
    }

    @Override
    protected void beforeUpdate(Collection<GoodsData> addList) {
       GoodsDao.getInstance().update(addList);
    }
}
