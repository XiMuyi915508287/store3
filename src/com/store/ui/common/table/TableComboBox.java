/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common.table;

import java.awt.Component;
import java.util.EventObject;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author chenjingjun
 */
public class TableComboBox extends JComboBox implements TableCellEditor{

    protected EventListenerList listenerList = new EventListenerList();
    protected ChangeEvent changeEvent = new ChangeEvent(this);
    
    
    public TableComboBox(Object[] items) {
        super(items);
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        setSelectedItem(value);  
        return this;
    }

    @Override
    public Object getCellEditorValue() {
        return getSelectedItem();
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return true;
    }

    @Override
    public boolean stopCellEditing() {
        fireEditingStopped();
        return true;
    }

    @Override
    public void cancelCellEditing() {
        fireEditingCanceled();
    }

    @Override
    public void addCellEditorListener(CellEditorListener listener) {
        listenerList.add(CellEditorListener.class, listener);
    }

    @Override
    public void removeCellEditorListener(CellEditorListener listener) {
       listenerList.remove(CellEditorListener.class, listener);
    }
    
    protected void fireEditingCanceled() {
        CellEditorListener listener;
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i] == CellEditorListener.class) {
                listener = (CellEditorListener) listeners[i + 1];
                listener.editingCanceled(changeEvent);
            }
        }
    }
    
    protected void fireEditingStopped() {
        CellEditorListener listener;
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i++) {
            if (listeners[i] == CellEditorListener.class) {
                listener = (CellEditorListener) listeners[i + 1];
                listener.editingStopped(changeEvent);
            }
        }
    }
}
