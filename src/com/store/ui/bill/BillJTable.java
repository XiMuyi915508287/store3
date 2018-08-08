/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.bill;

import com.store.data.common.Approach;
import com.store.data.goods.GoodsData;
import com.store.data.stock.StockDao;
import com.store.data.user.UserManager;
import com.store.ui.common.table.TableComboBox;
import com.store.ui.common.table.JEditTable;
import com.store.ui.common.table.TableDataJPanel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author chenjingjun
 */
public class BillJTable extends JEditTable<GoodsData>{

    public BillJTable(TableDataJPanel parent) {
        super(parent);
    }
    
    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        String UIName = getColumnName(column);
        if(UIName.equals("类型")){
            return new TableComboBox(Approach.getNames().toArray());
        }
        else if (UIName.equals("商品")) {
            return new TableComboBox(StockDao.getInstance().getNames().toArray());
        }
        if(UIName.equals("经手人")){
            return new TableComboBox(UserManager.getUnderUsers().toArray());
        }
        return super.getCellEditor(row, column);
    }
}
