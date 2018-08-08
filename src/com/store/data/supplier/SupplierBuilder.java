/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.supplier;

import com.store.data.DataBuilder;
import com.store.id.IdType;

/**
 *
 * @author chenjingjun
 */
public class SupplierBuilder extends DataBuilder<SupplierData>{

    public SupplierBuilder() {
        super(SupplierData.DATA_DETAIL, IdType.SUPPLIRE);
    }

    @Override
    public SupplierData _constructor() {
         return new SupplierData();
    }
    
    @Override
    protected String _valid(SupplierData data) {
         return null;
    }
}
