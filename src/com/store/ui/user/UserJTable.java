/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.user;

import com.store.data.Authority;
import com.store.data.user.UserData;
import com.store.data.user.UserManager;
import com.store.ui.common.table.TableComboBox;
import com.store.ui.common.table.JEditTable;
import com.store.ui.common.table.TableDataJPanel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author chenjingjun
 */
public class UserJTable extends JEditTable<UserData>{

    public UserJTable(TableDataJPanel parent) {
        super(parent);
    }
    
    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        String UIName = getColumnName(column);
        if(UIName.endsWith("权限")){
            return new TableComboBox(Authority.getEnable(UserManager.getAuthority()).toArray());
        }
        return super.getCellEditor(row, column);
    }
}
