/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common.table;

import com.store.data.DataField;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chenjingjun
 */
public class TableInputDetail {
    
    private final Map<String, TableInputField> fieldMap = new HashMap<>();

    public TableInputDetail() {
    }
    
    public void add(DataField field, int type, List<String> valueList){
        fieldMap.put(field.Name, new TableInputField(field, type, valueList));
    }
    
    public void add(DataField field, int type, List<String> valueList, String defaultValue){
       fieldMap.put(field.Name, new TableInputField(field, type, valueList, defaultValue));
    }
    
    public boolean contain(DataField field){
        return fieldMap.containsKey(field.Name);
    }
    
    public Map<String, TableInputField> get(){
        return new HashMap<>(fieldMap);
    }
    
    public TableInputField get(DataField field){
        return fieldMap.get(field.Name);
    }
}
