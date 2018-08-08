/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common.table;

import com.pjfun.common.eval.TExprParams;
import com.store.data.Data;
import com.store.data.DataDetail;
import com.store.data.DataField;
import com.store.data.FieldReader;
import com.store.data.user.UserManager;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author chenjingjun
 * @param <T>
 */
public abstract class TableEditModel<T extends Data> extends DefaultTableModel {
    
    protected TableDataJPanel<T> panel;
    protected DataDetail detail;
    
    public TableEditModel(DataDetail detail) {
        super(detail.getUINames(), 0);
        this.detail = detail;
    }

    public void setPanel(TableDataJPanel<T> panel) {
        this.panel = panel;
    }

    public DataDetail getDetail() {
        return detail;
    }
    
    public void initData(List<T> dataList){
        clearUIData();
        for(T data : dataList){
             addUIData(data);
        }
    }
    
     @Override
    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column);
        DataField setField = detail.getAuthoFileds().get(column);
        List<DataField> updateFields = detail.getExpreFileds(setField);
        if (updateFields.size() > 0) {
            List<DataField> auFields = detail.getAuthoFileds();
            for(DataField field : updateFields){
                TExprParams params = new TExprParams();
                for(int i = 0; i < auFields.size(); i++){
                    params.put(auFields.get(i).Name, getValueAt(row, i));
                }
                int index = auFields.indexOf(field);
                Object caculate = field.getParser().getValue(params);
                setValueAt(caculate, row, index);
            }
        }
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return detail.getAuthoFileds().get(columnIndex).getFiledClass();
    }
    
    @Override
    public final boolean isCellEditable(int rowIndex, int columnIndex) {
        boolean success = UserManager.isAdmin() && _isCellEditable(rowIndex, columnIndex);
        return success;
    }
    
    protected boolean _isCellEditable(int rowIndex, int columnIndex) {
        return panel.getJTable().isAdded(rowIndex) || columnIndex > 0;
    }
    
    public void addUIData(T data){
        Object[] rowObject = getRowObject(data);
        addRow(rowObject);
    }
    
    public void removeUIData(int index){
        removeRow(index);
    }
    
    private void clearUIData(){
        int count = getRowCount();
        for (int row = count - 1; row >= 0; row--) {
            this.removeRow(row);
        }
    }
    
    public Object[] getRowObject(T data){
         List<DataField> fieldList = detail.getAuthoFileds();
         Object[] objects = new Object[fieldList.size()];
         for(int i = 0; i < fieldList.size(); i++){
             objects[i] = FieldReader.getObject(fieldList.get(i), data);
         }
         return objects;
    }
}
