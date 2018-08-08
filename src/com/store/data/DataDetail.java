/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data;

import com.store.data.user.UserManager;
import com.store.logger.StoreLogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chenjingjun
 */
public class DataDetail {
   
    private final List<DataField> filedList;
    private final Map<String, List<DataField>> expression;

    public DataDetail(List<DataField> filedList) {
        this.filedList = new ArrayList<>(filedList);
        for(DataField field : filedList){
           field.setDetail(this);
        }
        this.expression = new HashMap<>();
        for(DataField field : filedList){
            List<DataField> findList = new ArrayList<>();
            for(DataField fieldCk : filedList){
                if (fieldCk.Name.equals(field.Name)) {
                    continue;
                }
                if (fieldCk.getParser().containKeys(field.Name)) {
                    findList.add(fieldCk);
                }
            }
            expression.put(field.Name, findList);
        }
    }
   
    public DataField getByUIName(String name){
        for(DataField field : filedList){
            if(field.UIName.equals(name)){
                return field;
            }
        }
        return null;
    }
    
    public DataField getByName(String name){
        for(DataField field : filedList){
            if(field.Name.equals(name)){
                return field;
            }
        }
        return null;
    }
    
    public DataField getByIndex(int index){
        DataField field =  filedList.get(index);
        if (field == null) {
            StoreLogger.error(index + "");
        }
        return field;
    }

    public List<DataField> getFiledList() {
        return new ArrayList<>(filedList);
    }
    
    public List<DataField> getAuthoFileds() {
        Authority authority = UserManager.getAuthority();
         List<DataField> fields =  new ArrayList<>();
         for(DataField field : filedList){
             if (authority.enable(field.authority)) {
                 fields.add(field);
             }
         }
         return fields;
    }
    
    public List<DataField> getClassify() {
         List<DataField> fields =  new ArrayList<>();
         for(DataField field : filedList){
             if (field.Classify) {
                 fields.add(field);
             }
         }
         return fields;
    }
    
    public String[] getUINames(){
        List<DataField> filedList = getAuthoFileds();
        String[] valueList = new String[filedList.size()];
        for(int i = 0; i < filedList.size(); i++){
            valueList[i] = filedList.get(i).UIName;
        }
        return valueList;
    }
    
    public List<DataField> getExpreFileds(DataField field) {
        return new ArrayList<>(expression.get(field.Name));
    }
}
    
