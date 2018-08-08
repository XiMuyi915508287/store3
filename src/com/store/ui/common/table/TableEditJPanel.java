/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common.table;

import com.store.data.Data;
import com.store.data.DataField;
import com.store.data.FieldReader;
import com.store.data.KeyReader;
import com.store.ui.common.ConfirmDialog;
import com.store.ui.common.btn.BtnEnableWarp;
import com.store.ui.common.btn.ButtonUtil;
import com.store.ui.common.combox.ComboBoxProxy;
import com.store.data.user.UserManager;
import com.store.util.StringUtil;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.event.TableModelEvent;

/**
 *
 * @author chenjingjun
 * @param <T>
 */
public abstract class TableEditJPanel <T extends Data> extends TableDataJPanel<T>{
    private BtnEnableWarp comfirmButtons;                   
    private BtnEnableWarp editButtons;
    private List<ComboBoxProxy<String>> classifyComBoxList;          
    private final boolean onlyName;

    /**
     * Creates new form VenderJPanel
     * @param dataDetail
     */
    public TableEditJPanel(TableEditModel<T> model, boolean onlyName) {
        super(model);
        this.onlyName = onlyName;
        classifyComBoxList = new ArrayList<>(0);
    }

    @Override
    public void initUI(boolean realod) {
        super.initUI(realod);
        resetClassifyKeys();
        List<T> currentList = onUpdateClassifyKeys();
        resetJTableUIData(currentList, true);
    }

    @Override
    public void initOne() {
        super.initOne(); 
        initBtnComponent();
    }

    @Override
    public boolean siwtchEnanble() {
        return false == comfirmButtons.isEnable();
    }
    
    /**
     * 初始化按钮
     */
    protected void initBtnComponent(){
        ActionListener actionListener = (ActionEvent e) -> {
            Object source = e.getSource();
            if(source == getAddBtn()){
                 clickBtnNew(e);
            }
            else if(source == getSaveBtn()){
                clickBtnSave(e);
            }
            else if(source == getDeleteBtn()){
                 clickBtnDelete(e);
            }
            else if(source == getCancelBtn()){
                 clickBtnCancel(e);
            }
            else if(source == getReloadBtn()){
               initUI(true);
            }
        }; 
        getAddBtn().addActionListener(actionListener);
        getSaveBtn().addActionListener(actionListener);
        getDeleteBtn().addActionListener(actionListener);
        getCancelBtn().addActionListener(actionListener);
        getReloadBtn().addActionListener(actionListener);
        ButtonUtil.enable(getAddBtn(), UserManager.isAdmin());
        ButtonUtil.enable(getSaveBtn(), UserManager.isAdmin());
        ButtonUtil.enable(getDeleteBtn(), UserManager.isAdmin());
        ButtonUtil.enable(getCancelBtn(), UserManager.isAdmin());
        ButtonUtil.enable(getReloadBtn(), true);
    }
    
    /**
     * 初始化排序的Combox
     * @param classifyComBoxs
     */
    protected void initClassifyComponents(JComboBox<String>... classifyComBoxs){
        classifyComBoxList = new ArrayList<>(classifyComBoxs.length);
        ItemListener itemListener = (ItemEvent e) -> {
            if(e.getStateChange() != ItemEvent.SELECTED){
                return;
            }
            JComboBox<String> comboBox = (JComboBox<String>)e.getSource();
            ComboBoxProxy<String> proxy = getComBoxProxy(comboBox);
            if(getComfirmButtons().isEnable()){
                proxy.updateSelect(false);
            }
            else {
                String value = comboBox.getSelectedItem().toString();
                if(proxy.setSelectItem(value)){
                    List<T> currentList = onUpdateClassifyKeys();
                    resetJTableUIData(currentList, true);
                }
            }
        };
        for(int i = 0; i < classifyComBoxs.length; i++){
            classifyComBoxs[i].addItemListener(itemListener);
            classifyComBoxList.add(new ComboBoxProxy<>(classifyComBoxs[i], ""));
        }
    }
            
    /**
     * 保存
     * @param evt
     */
    protected boolean clickBtnSave(java.awt.event.ActionEvent evt) {   
        Set<String> nameSet = new HashSet<>();
        JEditTable<T> jtEditTable = getJTable();
        List<T> updateDataList = new ArrayList<>();
        Collection<T> dataCollection = jtEditTable.getAddedCollection();
        Set<Long> catalogDeleted = jtEditTable.getDeleteID();
        for(T addData : dataCollection){
            String message = getDataCatalog().getBuilder().valid(addData);
            if(message == null && onlyName){
                T checkData = getDataCatalog().get(addData.getName());
                if((checkData != null && !catalogDeleted.contains(checkData.getId())) || nameSet.contains(addData.getName()) ){
                    message = String.format("【%s】已经存在", addData.getName());
                }
            }
            if(message != null){
                ConfirmDialog.show("新增数据：" + message);
                selectJTableRow(jtEditTable.getRow(addData));
                return false;
            }
            updateDataList.add(addData);
            nameSet.add(addData.getName());
        }
        List<DataField> fieldList = getDataCatalog().getDataDetail().getAuthoFileds();
        Collection<TableGell<T>> collection = jtEditTable.getEditCell();
        for(TableGell<T> cell: collection){
            T copy = getDataCatalog().getBuilder()._constructor();
            for (DataField field : fieldList) {
                Object value = FieldReader.getObject(field, cell.data);
                FieldReader.setObject(copy, field, value);
            }
            int cellRow = getJTable().getRow(cell.data);
            for(int cellColumn : cell.getColumns()){
                String Name = fieldList.get(cellColumn).Name;
                Object value = getJTableModel().getValueAt(cellRow, cellColumn);
                FieldReader.setValue(copy, Name, value);
            }
            copy.setId(cell.data.getId());
            copy.setName(cell.data.getName());
            String message = getDataCatalog().getBuilder().valid(copy);
            if(message != null){
                ConfirmDialog.show("修改数据：" + message);
                selectJTableRow(jtEditTable.getRow(cell.data));
                return false;
            }
            updateDataList.add(copy);
        }   
        boolean success = ConfirmDialog.confirm("确认要保存修改吗？");
        if(!success){
            return false;
        }
        //先删除
        getDataCatalog().remove(catalogDeleted);
        //在增加
        getDataCatalog().update(updateDataList);
        //
        List<DataField> datafields = getDataCatalog().getDataDetail().getAuthoFileds();
        for(TableGell<T> cell: collection){
            int cellRow = getJTable().getRow(cell.data);
            for(int cellColumn : cell.getColumns()){
                T data = jtEditTable.getData(cellRow);
                String Name = datafields.get(cellColumn).Name;
                Object value = getJTableModel().getValueAt(cellRow, cellColumn);
                FieldReader.setValue(data, Name, value);
            }
        }
        //清除编译
        getJTable().clearEditable();
        updateTableUI(true);
        resetClassifyKeys();
        getComfirmButtons().enable(false);
        return true;
    }                               
                              
    /**
     * 删除
     * @param evt
     */
    protected void clickBtnDelete(java.awt.event.ActionEvent evt) {                                  
        int[] indexs = getJTable().getSelectedRows();
        if(indexs.length == 0){
            return;
        }
        boolean success = ConfirmDialog.confirm("确认要删除 %d 行吗？", indexs.length);
        if(!success){
            return;
        }
        for(int i = indexs.length - 1; i >= 0; i--){
            T data = getJTable().removeData(indexs[i]);
            getJTable().recorldDelete(data);
            selectJTableRow(indexs[i] - 1);
        }
        comfirmButtons.enable(getJTable().isEditable());
        updateTableUI(true);
        resetClassifyKeys();
    }    
    
    protected void clickBtnCancel(java.awt.event.ActionEvent evt) {                                  
        boolean success = ConfirmDialog.confirm("确认取消操作吗？");
        if(success){
            JEditTable<T> jtEditTable = getJTable();
            Iterator<T> iterator = jtEditTable.getAddedCollection().iterator();
            while(iterator.hasNext()){
                T data = iterator.next();
                int row = jtEditTable.getRow(data);
                jtEditTable.removeData(row);
            }
            List<T> currentList = onUpdateClassifyKeys();
            resetJTableUIData(currentList, true);
        }
    }  
    
    protected void clickBtnNew(java.awt.event.ActionEvent evt) {        
        T addData  = getDataCatalog().getBuilder().constructor();
        JEditTable table =  getJTable();
        int index = table.getSelectedRow() + 1;
        index = table.addData(index, addData);
        table.recorldAdded(addData);
        selectJTableRow(index);
        updateJTextArea("", "");
        comfirmButtons.enable(table.isEditable());
    }  
    
    protected abstract JButton getAddBtn();
    
    protected abstract JButton getSaveBtn();
     
    protected abstract JButton getDeleteBtn();
    
    protected abstract JButton getCancelBtn();
    
    protected abstract JButton getReloadBtn();
    
    protected BtnEnableWarp getComfirmButtons(){
        if(comfirmButtons == null){
            comfirmButtons = new BtnEnableWarp(getSaveBtn(), getCancelBtn());
            comfirmButtons.enable(false);
        }
        return comfirmButtons;
    }
    
     protected BtnEnableWarp getEditButtons(){
        if(editButtons == null){
            editButtons = new BtnEnableWarp(getAddBtn(), getDeleteBtn());
            editButtons.enable(true);
        }
        return editButtons;
    }
           
    /**
     * 检测目前的数据是否符合筛选条件
     * @param data
     * @return 
     */
    protected String checkEditableData(T data){
        return null;
    }
    
    
    /***
     * 获取代理的JComBox
     * @param comboBox
     * @return 
     */
    private ComboBoxProxy<String> getComBoxProxy(JComboBox<String> comboBox){
        for(ComboBoxProxy<String> proxy : classifyComBoxList){
            if(proxy.comboBox == comboBox){
               return proxy;
            }
        }
       return null;
    }
    
    @Override
    protected void initJTableComponent(){
        super.initJTableComponent();
        dataEditChangeListener();
    }
    
    @Override
    protected void resetJTableUIData(List<T> currentDataList, boolean defaultSort){
        super.resetJTableUIData(currentDataList, defaultSort);
        getComfirmButtons().enable(false);
    }
    
    @Override
    protected void onClickJTable(int row, int column, Object value){
        updateJTextArea(value.toString(), getChangeMessage(row, column));
    }

     /**
     * 重新设置过滤字段
     */
    private void resetClassifyKeys(){
        List<DataField> datafields = getDataCatalog().getDataDetail().getClassify();
        //获取所有数据
        Collection<T> dataList = getDataCatalog().getList();
        //存储键值的结构
        List<Set<String>> keyList = new ArrayList<>();
        for(int i = 0; i < datafields.size(); i++){
            keyList.add(new HashSet<>());
        }
        //建立键值
        for(T data : dataList){
            for(int i = 0; i < datafields.size(); i++){
                KeyReader reader = datafields.get(i).getKeyReader(data);
                keyList.get(i).addAll(reader.getKeys());
            }
        }
        //设置键值
        for(int i = 0; i < classifyComBoxList.size(); i++){
            List<String> itemList = new ArrayList<>(keyList.get(i));
            datafields.get(i).sortClassifyKey(itemList);
            classifyComBoxList.get(i).resetItems(itemList);
            classifyComBoxList.get(i).updateSelect(true);
        }
    }
    
    /***
     * 获取当前过滤结果
     * @return 
     */
    private List<T> onUpdateClassifyKeys(){
        List<DataField> datafields = getDataCatalog().getDataDetail().getClassify();
        //获取所有数据
        Collection<T> dataList = getDataCatalog().getList();
        List<T> matchList = new ArrayList<>();
        //建立键值
        if (classifyComBoxList.isEmpty()) {
            matchList.addAll(dataList);
        }
        else{
            for(T data : dataList){
                boolean success = true;
                for(int i = 0; i < datafields.size(); i++){
                    KeyReader reader = datafields.get(i).getKeyReader(data);
                    String string = classifyComBoxList.get(i).getSelectItem();
                    if (StringUtil.isNullOrEmpty(string) || reader.match(string)) {

                    }
                    else{
                        success = false;
                        break;
                    }
                }
                if (success) {
                    matchList.add(data);
                }
            }
        }
        return matchList;
    }
    
    private void dataEditChangeListener(){
         getJTable().getModel().addTableModelListener((TableModelEvent e) -> {
            int row = e.getFirstRow();
            int column = e.getColumn();
            if(row == -1 || column == -1){
                return;
            }
            TableEditModel<T> model = getJTableModel();
            Object value = model.getValueAt(row, column);
            String UIName = model.getColumnName(column);
            DataField field = getDataCatalog().getDataDetail().getByUIName(UIName);
            T data = getJTable().getData(row);
            Object dataValue = FieldReader.getObject(field, data);
            if (getJTable().isAdded(row)) {
                FieldReader.setObject(data, field, value);
            }
            boolean enable = !value.equals(dataValue);
            getJTable().setValueEdit(data, column, enable);
            comfirmButtons.enable(getJTable().isEditable());
            updateJTextArea(null, getChangeMessage(row, column));
        });
    }
    
    private String getChangeMessage(int row, int column){
        StringBuilder builder0 = new StringBuilder();
        StringBuilder builder1 = new StringBuilder();
        List<DataField> datafields = getDataCatalog().getDataDetail().getAuthoFileds();
        for(TableGell<T> cell: getJTable().getEditCell()){
            T data = cell.data;
            int cellRow = getJTable().getRow(data);
            for(int cellColumn : cell.getColumns()){
                DataField field = datafields.get(cellColumn);
                Object value = getJTableModel().getValueAt(cellRow, cellColumn);
                Object dataValue = FieldReader.getObject(field, data);
                String string = String.format("%s:原始值[%s] 当前值[%s].\n", data.getName(), dataValue.toString(), value.toString());
                if(row == cellRow && column == cellColumn){
                    builder0.append(string);
                }
                else{
                    builder1.append(string);
                }
                builder1.append(builder0.toString());
            }
        }
        return builder1.toString();
    }
    // Variables declaration - do not modify                     
    // End of variables declaration                   
}

