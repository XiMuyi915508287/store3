/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common.table;

import com.store.data.Data;
import com.store.data.DataDetail;
import com.store.data.DataField;

/**
 *
 * @author chenjingjun
 * @param <T>
 */
public abstract class TableInputModel<T extends Data> extends TableEditModel<T> {
    
    private TableInputDetail inputDetail;

    public TableInputModel(DataDetail detail) {
        super(detail);
    }

    public void init(){
        inputDetail = new TableInputDetail();
        initInputDetail(inputDetail);
    }
    
    @Override
    protected boolean _isCellEditable(int rowIndex, int columnIndex) {
        boolean sucesss =  super._isCellEditable(rowIndex, columnIndex);
        if (sucesss) {
            DataField setField = detail.getAuthoFileds().get(columnIndex);
            sucesss = !inputDetail.contain(setField);
        }
        return sucesss ;
    }

    public TableInputDetail getInputDetail() {
        return inputDetail;
    }
    
    protected abstract void initInputDetail(TableInputDetail detail);
}
