/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common.table;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author chenjingjun
 */
public class TableComboBoxRenderer extends JComboBox implements TableCellRenderer{ 
    
    public TableComboBoxRenderer(Object[] items){  
        super(items);  
    }  

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,  boolean isSelected, boolean hasFocus, int row, int column) {  
       if(isSelected){  
           setForeground(table.getForeground());  
           super.setBackground(table.getBackground());  
       }else{  
           setForeground(table.getForeground());  
           setBackground(table.getBackground());  
       }  
       setSelectedItem(value);
       return this;  
    }  
}
