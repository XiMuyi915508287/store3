/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.bill;

import com.store.data.bill.BillData;
import com.store.ui.common.table.TableEditModel;

/**
 *
 * @author chenjingjun
 */
public class BillModel extends TableEditModel<BillData> {
    
    public BillModel() {
        super(BillData.DATA_DETAIL);
    }
   
    @Override
    protected boolean _isCellEditable(int rowIndex, int columnIndex) {
        if (panel.getJTable().isAdded(rowIndex)) {
            return columnIndex > 0;
        }
        else{
            return columnIndex == 5 || columnIndex == 6 || columnIndex == 9;
        }
    }
}
