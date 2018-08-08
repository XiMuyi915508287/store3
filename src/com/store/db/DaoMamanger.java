/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.db;

import com.store.data.bill.BillDao;
import com.store.data.goods.GoodsDao;
import com.store.data.stock.StockDao;
import com.store.data.supplier.SupplierDao;
import com.store.id.IdGenerator;
import com.store.ui.MainLoading;

/**
 *
 * @author chenjingjun
 */
public class DaoMamanger {
    
    private static IDao[] daos = new IDao[]{
        IdGenerator.getInstance(),
        StockDao.getInstance(),
        SupplierDao.getInstance(),
        GoodsDao.getInstance(),
        BillDao.getInstance(),
    };

    /**
     * 
     * @param loading 
     */
    public static void beforeInit(MainLoading loading){
        if (loading == null) {
            return;
        }
        loading.initCount(MainLoading.DB_DATA, daos.length);
    }

    public static IDao[] getDaos() {
        return daos;
    }
    
    /**
     * 
     * @param loading 
     */
    public static void onOnit(MainLoading loading){
        if (loading == null) {
            return;
        }
        for(IDao dao :daos){
            dao.reload();
            loading.updateCount(MainLoading.DB_DATA, 1);
        }
    }
}
