/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common.table;

import com.store.data.DataField;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chenjingjun
 */
public class TableInputField {
    public static final int COMBOIX = 1;
    public static final int TXT = 2;
    
    
    public final DataField field;
    public final int type;
    private final List<String> valueList;
    private final String defaultValue;

    public TableInputField(DataField field, int type, List<String> valueList) {
        this(field, type, valueList, null);
    }
    public TableInputField(DataField field, int type, List<String> valueList, String defaultValue) {
        this.field = field;
        this.type = type;
        this.valueList = new ArrayList<>();
        if (defaultValue != null) {
            this.valueList.add("");
        }
        this.valueList.addAll(valueList);
        this.defaultValue = defaultValue;
    }

    public List<String> getValueList() {
        return new ArrayList<>(valueList);
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
