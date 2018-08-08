/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.goods;

import com.store.data.goods.GoodsData;
import com.store.data.stock.StockDao;
import com.store.ui.common.table.TableComboBox;
import com.store.ui.common.table.JEditTable;
import com.store.ui.common.table.TableDataJPanel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author chenjingjun
 */
public class EntryBillJTable extends JEditTable<GoodsData>{

    public EntryBillJTable(TableDataJPanel parent) {
        super(parent);
    }
    
    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        String UIName = getColumnName(column);
        if(UIName.equals("商品")){
            return new TableComboBox(StockDao.getInstance().getNames().toArray());
        }
        return super.getCellEditor(row, column);
    }
}
