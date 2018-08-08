/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common.table;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.EventObject;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author chenjingjun
 */
public class TableTextComboBox extends JTextField implements DocumentListener, TableCellEditor{
    
    protected EventListenerList listenerList = new EventListenerList();
    protected ChangeEvent changeEvent = new ChangeEvent(this);
    
    private final JComboBox jComboBox;
    private final DefaultComboBoxModel model;  
    private Object[] items;
    private ITableTextUpdate textUpdate;

    public TableTextComboBox() {
        this(new Object[0]);
    }

    public TableTextComboBox(Object[] items) {
        this.items = items;
        this.model = new DefaultComboBoxModel();
        jComboBox = new JComboBox(model){  
            @Override
            public Dimension getPreferredSize() {  
                return new Dimension(super.getPreferredSize().width , 0);  
            }  
        };   
        jComboBox.setSelectedItem(null);
        jComboBox.setFont(new Font("宋体", 0, 14));
        setLayout(new BorderLayout());  
        add(jComboBox, BorderLayout.SOUTH); 
        addTextKeyListener();
        addTComboxActionListener();
    }
    
    public void changeItems(Object[] items){
        this.items = items;
    }
    
    public void register(ITableTextUpdate textUpdate){
        this.textUpdate = textUpdate;
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        return this;
    }

    @Override
    public Object getCellEditorValue() {
        return getText();
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

    private void addTextKeyListener(){
        addKeyListener(new KeyAdapter(){
            @Override  
            public void keyPressed(KeyEvent e) {  
                setAdjusting(true);  
                if (e.getKeyCode() == KeyEvent.VK_SPACE ) {  
                    if (jComboBox.isPopupVisible()) {  
                        e.setKeyCode(KeyEvent.VK_ENTER);  
                    }  
                }  
                if( e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN){
                     e.setSource(jComboBox);  
                     jComboBox.dispatchEvent(e);  
                }
                else if(e.getKeyCode() == KeyEvent.VK_ENTER ){
                     if(jComboBox.getSelectedItem() != null){
                        onTextChange(jComboBox.getSelectedItem().toString());
                        jComboBox.setPopupVisible(false);  
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {  
                    jComboBox.setPopupVisible(false);  
                }  
                setAdjusting(false);  
            }
        });
        getDocument().addDocumentListener(this);
    }
    
    private void addTComboxActionListener(){
        jComboBox.addActionListener((ActionEvent e) -> {
            Object value = jComboBox.getSelectedItem();
            if(isAdjusting() || value == null){
                return;
            }
            onTextChange(value.toString());
        });  
    }
    
    private void onTextChange(String value){
        setText(value);
        if(textUpdate != null){
            textUpdate.update(value);
        }
    }
    
    @Override
    public void insertUpdate(DocumentEvent e) {
         updateComboxList();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
         updateComboxList();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateComboxList();
    }
    
    private void updateComboxList() {  
        setAdjusting(true);  
        model.removeAllElements();
        String string = getText();  
        if (!string.isEmpty()) {  
            for (Object item : items) {  
                String value = item.toString();
                if (value.toLowerCase().contains(string)) {  
                    model.addElement(item);
                }  
            }  
        }  
        jComboBox.setPopupVisible(model.getSize() > 0);  
        setAdjusting(false);  
    }  
    
    private void setAdjusting(boolean adjusting){
         jComboBox.putClientProperty("is_adjusting", adjusting);  
    }
    
    private boolean isAdjusting() {  
        if (jComboBox.getClientProperty("is_adjusting") instanceof Boolean) {  
            return (Boolean) jComboBox.getClientProperty("is_adjusting");  
        }  
        return false;  
    }  
    
    
    public interface ITableTextUpdate{
        public void update(String value);
    }
}
