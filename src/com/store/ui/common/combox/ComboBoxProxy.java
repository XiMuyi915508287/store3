/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common.combox;

import java.util.Collection;
import java.util.List;
import javax.swing.JComboBox;

/**
 * 
 * @author chenjingjun
 */
public class ComboBoxProxy<T> {
    public final JComboBox<T> comboBox;
    private final T defaultItem;
    private T selectItem;
    
    public ComboBoxProxy(JComboBox<T> comboBox, T defaultItem) {
        this.comboBox = comboBox;
        this.selectItem = defaultItem;
        this.defaultItem = defaultItem;
    }

    public boolean defaultItem(){
        return selectItem.equals(defaultItem);
    }
    
    public void resetItems(Collection<T> itemList){
        comboBox.removeAllItems();
        comboBox.addItem(defaultItem);
        boolean success = false;
        for(T item : itemList){
            if (defaultItem.equals(item)) {
                continue;
            }
            comboBox.addItem(item);
            if (selectItem.equals(item)) {
                success = true;
            }
        }
        if (false == success) {
            selectItem = defaultItem;
        }
        comboBox.updateUI();
    }
    
    public boolean setSelectItem(T object){
        if(selectItem.equals(object)){
            return false;
        }
        selectItem = object;
        return true;
    }
    
    public void updateSelect(boolean updateUI){
        if(selectItem != null){
           comboBox.setSelectedItem(selectItem);
        }
        if(updateUI){
            comboBox.updateUI();
        }
    }
    
    public T getSelectItem(){
        return selectItem;
    }
}
