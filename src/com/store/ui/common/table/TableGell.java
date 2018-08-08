/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common.table;

import com.store.data.Data;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author chenjingjun
 */
public class TableGell<T extends Data > {
    
    private Set<Integer> columns;
    public final T data;

    public TableGell(T data) {
        this.data = data;
        this.columns = new HashSet<>();
    }
    
    public void addColumn(int column){
        columns.add(column);
    }
    
    public void removeColumn(int column){
        columns.remove(column);
    }
    
    public boolean containColumn(int column){
        return columns.contains(column);
    }
   
    public boolean empty(){
        return columns.isEmpty();
    }

    public Set<Integer> getColumns() {
        return columns;
    }
}
