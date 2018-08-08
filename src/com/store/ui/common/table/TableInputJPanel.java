/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common.table;

import com.store.data.Data;
import com.store.data.DataField;
import com.store.data.FieldReader;
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.event.TableModelEvent;

/**
 *
 * @author chenjingjun
 * @param <T>
 */
public abstract class TableInputJPanel <T extends Data> extends TableDataJPanel<T>{
    private BtnEnableWarp comfirmButtons;                   
    private BtnEnableWarp editButtons;
    private final Map<String, ComboBoxProxy<String>> updateComboxList;
    private final Map<String, TableTextComboBox> updateTextList;

    /**
     * Creates new form VenderJPanel
     * @param dataDetail
     */
    public TableInputJPanel(TableInputModel<T> model) {
        super(model);
        updateComboxList = new HashMap<>(0);
        updateTextList = new HashMap<>(0);
    }

    @Override
    public void initUI(boolean realod) {
        super.initUI(realod);
        resetJTableUIData(getDataCatalog().getList(), true);
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
                clickBtnSave();
            }
            else if(source == getDeleteBtn()){
                 clickBtnDelete(e);
            }
            else if(source == getCancelBtn()){
                onConfirmInitUI();
            }
            else if(source == getReloadBtn()){
                onConfirmInitUI();
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
    protected void initUpdateJComboBox(DataField field, JComboBox<String> jComboBox, List<String> valueList){
        ItemListener itemListener = (ItemEvent e) -> {
            if(e.getStateChange() != ItemEvent.SELECTED){
                return;
            }
            JComboBox<String> comboBox = (JComboBox<String>)e.getSource();
            ComboBoxProxy<String> proxy = getComBoxProxy(comboBox);
            Object value = comboBox.getSelectedItem();
            if(proxy.setSelectItem(value.toString())){
                updateValue4JCombox();
            }
        };
        updateComboxList.put(field.Name, new ComboBoxProxy<>(jComboBox, valueList.get(0)));
        updateComboxList.get(field.Name).resetItems(valueList);
        jComboBox.addItemListener(itemListener);
    }
    
    /**
     * 
     * @param field
     * @param textComboBox
     * @param valueList 
     */
    protected void initUpdateJFiled(DataField field, TableTextComboBox textComboBox, List<String> valueList){
        if (updateTextList.containsKey(field.Name)) {
            throw new UnsupportedOperationException();
        }
        textComboBox.register(new TableTextComboBox.ITableTextUpdate() {
            @Override
            public void update(String value) {
                List<T> dataList = getJTable().getDataList();
                List<DataField> fieldList = getDataCatalog().getDataDetail().getAuthoFileds();
                int index = fieldList.indexOf(field);
                Object objetc = FieldReader.getObject(field, value);
                for(int i = 0; i < dataList.size(); i++){
                    T data = dataList.get(i);
                    getJTable().getModel().setValueAt(objetc, i, index);
                }
            }
        });
        textComboBox.changeItems(valueList.toArray());
    }
            
    /**
     * 保存
     */
    protected boolean clickBtnSave() {   
        JEditTable<T> jtEditTable = getJTable();
        List<T> updateDataList = new ArrayList<>();
        Collection<T> dataCollection = jtEditTable.getAddedCollection();
        Set<Long> catalogDeleted = jtEditTable.getDeleteID();
        Set<String> nameSet = new HashSet<>();
        for(T addData : dataCollection){
            String message = getDataCatalog().getBuilder().valid(addData);
            if(message == null){
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
        //确认
        onConfirm(getDataCatalog().getList());
        //刷新
        List<T> currentList = new ArrayList<>(getDataCatalog().getList());
        resetJTableUIData(currentList, true);
        return true;
    }                 
    
    /**
     * 处理确认之后的事情
     * @param daCollection 
     */
    protected void onConfirm(List<T> daCollection){
        
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
        updateTableUI(true);
        comfirmButtons.enable(getJTable().isEditable());
    }    
    
    /**
     * @param evt 
     */
    protected void onConfirmInitUI() {  
        boolean success = ConfirmDialog.confirm("确认撤销之前的左右操作嘛？");
        if(success){
            initUI(true);
        }
    }  
    
    /**
     * @param evt 
     */
    protected void clickBtnNew(java.awt.event.ActionEvent evt) {        
        T addData  = getDataCatalog().getBuilder().constructor();
        onNewData(addData);
        JEditTable table =  getJTable();
        int index = table.getSelectedRow() + 1;
        index = table.addData(index, addData);
        table.recorldAdded(addData);
        updateValue4JCombox();
        selectJTableRow(index);
        updateJTextArea("", "");
        comfirmButtons.enable(table.isEditable());
    }  
    
    /**
     *
     * @return
     */
    @Override
    protected TableInputModel<T> getJTableModel(){
        return (TableInputModel<T>)super.getJTableModel();
    }
    
    /**
     * 增加类
     * @param data 
     */
    protected abstract void onNewData(T data);
    
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

    /***
     * 获取代理的JComBox
     * @param comboBox
     * @return 
     */
    private ComboBoxProxy<String> getComBoxProxy(JComboBox<String> comboBox){
        for(ComboBoxProxy<String> proxy : updateComboxList.values()){
            if(proxy.comboBox == comboBox){
               return proxy;
            }
        }
       return null;
    }
    
    @Override
    protected void initJTableComponent(){
        super.initJTableComponent();
        getJTableModel().init();
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
     * 
     */
    private void updateValue4JCombox(){
        List<T> dataList = getJTable().getDataList();
        List<DataField> fieldList = getDataCatalog().getDataDetail().getAuthoFileds();
        for (Map.Entry<String, ComboBoxProxy<String>> entry : updateComboxList.entrySet()) {
            String key = entry.getKey();
            ComboBoxProxy<String> comboBox = entry.getValue();
            DataField field = getDataCatalog().getDataDetail().getByName(key);
            int index = fieldList.indexOf(field);
            String stringValue = comboBox.getSelectItem();
            if (StringUtil.isNullOrEmpty(stringValue)) {
                TableInputField inputField = getJTableModel().getInputDetail().get(field);
                stringValue = inputField.getDefaultValue();
            }
            Object objetc = FieldReader.getObject(field, stringValue);
            for(int i = 0; i < dataList.size(); i++){
                T data = dataList.get(i);
                getJTable().getModel().setValueAt(objetc, i, index);
            }
        }
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
}

