/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.supplier;

import com.store.data.supplier.SupplierData;
import com.store.ui.common.table.TableEditModel;

/**
 *
 * @author chenjingjun
 */
public class SupplierModel extends TableEditModel<SupplierData> {
    
    public SupplierModel() {
        super(SupplierData.DATA_DETAIL);
    }
    
    @Override
    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column); //To change body of generated methods, choose Tools | Templates.
    }
}
