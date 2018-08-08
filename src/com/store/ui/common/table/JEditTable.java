/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common.table;

import com.store.data.Data;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JTable;

/**
 *
 * @author chenjingjun
 * @param <T>
 */
public class JEditTable<T extends Data> extends JTable{
    protected List<T> currentDataList ;   
    protected final TableDataJPanel parent;
    
    private final Map<Long, TableGell<T>> editCell;
    private final Set<Long> deleteID;
    private final Map<Long, T> addedData;
    private TableEditModel<T> editModel;

    public JEditTable(TableDataJPanel parent) {
        editCell = new HashMap<>();
        deleteID = new HashSet<>();
        addedData = new HashMap<>();
        this.parent = parent;
        currentDataList = new ArrayList<>();
    }

    public void setEditModel(TableEditModel<T> editModel) {
        super.setModel(editModel);
        this.editModel = editModel;
    }

    public TableEditModel<T> getEditModel() {
        return editModel;
    }
    
    public List<T> getDataList(){
        return currentDataList;
    }
    
    public void updateDataList(List<T> currentDataList){
        this.currentDataList = currentDataList;
        editModel.initData(currentDataList);
        clearEditable();
    }
    
    public void recorldAdded(T data){
        addedData.put(data.getId(), data);
    }
    
    public Collection<T> getAddedCollection(){
        return addedData.values();
    }
    
    public boolean isAdded(int row){
        T data = getData(row);
        return addedData.containsKey(data.getId());
    }
    
    public int getRow(T data){
        return currentDataList.indexOf(data);
    }
    
    public void recorldDelete(T data){
        if(addedData.remove(data.getId()) == null){
            deleteID.add(data.getId());
        }
        editCell.remove(data.getId());
    }
    
    public boolean isEditable(){
        return deleteID.size() > 0 || editCell.size() > 0 || addedData.size() > 0;
    }
    
    protected T removeData(int index){
        if(index < 0 || index >= currentDataList.size()){
            return null;
        }
        T data = currentDataList.remove(index);
        getEditModel().removeUIData(index);
        return data;
    }
    
    protected int addData(int index, T data){
        if(index < 0 || index > currentDataList.size()){
            index =  currentDataList.size();
        }
        currentDataList.add(index, data);
        Object[] objects = getEditModel().getRowObject(data);
        getEditModel().insertRow(index, objects);
        return index;
    }
    
    public T getData(int index){
        if(index < 0 || index > currentDataList.size()){
            return null;
        }
        return currentDataList.get(index);
    }
    
    public void setValueEdit(T data, int column, boolean value){
        if(addedData.containsKey(data.getId())){
            return;
        }
        TableGell<T> cGell = editCell.get(data.getId());
        if(cGell == null){
            cGell = new TableGell<>(data);
            editCell.put(data.getId(), cGell);
        }
        if(value){
            cGell.addColumn(column);
        }
        else{
            cGell.removeColumn(column);
            if(cGell.empty()){
                editCell.remove(data.getId());
            }
        }
    }

    public Collection<TableGell<T>> getEditCell() {
        return editCell.values();
    }

    public Set<Long> getDeleteID() {
        return deleteID;
    }
  
    public void clearEditable(){
        editCell.clear();
        deleteID.clear();
        addedData.clear();
    }

    @Override  
    public java.awt.Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {    
        java.awt.Component comp = super.prepareRenderer(renderer, row, column);   
        T data = getData(row);
        if(addedData.containsKey(data.getId())){
            comp.setBackground(getAddedBackground(data, row, column));  
        }
        else {
            TableGell<T> cGell = editCell.get(data.getId());
            if(cGell != null && cGell.containColumn(column)) {
                comp.setBackground(java.awt.Color.RED);  
            }
            else if(getSelectedRow() == row){
                comp.setBackground(new Color(204, 226, 229));  
            }
            else{
                comp.setBackground(this.getBackground()); 
            }
        }
        return comp;  
    }  
    
    protected Color getAddedBackground(T data, int row, int column){
        return new Color(153, 184, 154);
    }
}
