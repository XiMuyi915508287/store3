/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.supplier;

import com.store.data.DataDetail;
import com.store.data.DbDao;

/**
 *
 * @author chenjingjun
 */
public class SupplierDao extends DbDao<SupplierData>{
    
    private static final SupplierDao instance = new SupplierDao();
    
    public static SupplierDao getInstance(){
        return instance;
    }
    
    public SupplierDao() {
        super("Supplier", new SupplierBuilder());
    }

    @Override
    public DataDetail getDataDetail() {
        return SupplierData.DATA_DETAIL;
    }
}
