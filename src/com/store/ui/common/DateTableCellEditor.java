/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common;

import com.store.util.DateUtil;
import java.awt.Component;
import java.util.Calendar;
import java.util.Date;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author chenjingjun
 */
public class DateTableCellEditor extends AbstractCellEditor implements TableCellEditor{
    private JTextField tfDate = new JTextField();
    private Date date;
     
    @Override
    public Object getCellEditorValue() {
        return date;
    }
 
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        Date date = (Date) value;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        this.date = date;
        tfDate.setText(DateUtil.formatYMDHM(date));
        return tfDate;
    }   
}
