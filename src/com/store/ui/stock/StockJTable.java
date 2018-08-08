/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.stock;

import com.store.data.common.Unit;
import com.store.data.stock.StockData;
import com.store.data.supplier.SupplierDao;
import com.store.ui.common.table.TableComboBox;
import com.store.ui.common.table.JEditTable;
import com.store.ui.common.table.TableDataJPanel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author chenjingjun
 */
public class StockJTable extends JEditTable<StockData>{

    public StockJTable(TableDataJPanel parent) {
        super(parent);
    }
    
    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        String UIName = getColumnName(column);
        if(UIName.equals("供应商")){
            return new TableComboBox(SupplierDao.getInstance().getNames().toArray());
        }
        else if(UIName.equals("单位")){
            return new TableComboBox(Unit.getNames().toArray());
        }
        return super.getCellEditor(row, column);
    }
}
