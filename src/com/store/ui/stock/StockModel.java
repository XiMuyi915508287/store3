/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.stock;

import com.store.data.DataField;
import com.store.data.stock.StockData;
import com.store.ui.common.table.TableEditModel;

/**
 *
 * @author chenjingjun
 */
public class StockModel extends TableEditModel<StockData> {
    
    public StockModel() {
        super(StockData.DATA_DETAIL);
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column);
    }

    @Override
    public Object getValueAt(int row, int column) {
        return super.getValueAt(row, column); 
    }
    
    @Override
     protected boolean _isCellEditable(int rowIndex, int columnIndex) {
        DataField setField = detail.getAuthoFileds().get(columnIndex);
        if (setField.UIName.equals("商品ID")) {
            return false;
        }
        return super._isCellEditable(rowIndex, columnIndex) ;
    }
}
