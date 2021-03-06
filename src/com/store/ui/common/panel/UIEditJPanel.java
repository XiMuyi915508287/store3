/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common.panel;

import com.store.data.Data;
import com.store.data.DataField;
import com.store.ui.common.btn.ButtonUtil;
import com.store.ui.common.table.JEditTable;
import com.store.ui.common.table.TableEditJPanel;
import com.store.ui.common.table.TableEditModel;
import com.store.ui.common.table.TableTextComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;


/**
 *
 * @author chenjingjun
 */
public abstract class UIEditJPanel<T extends Data> extends TableEditJPanel<T>{

    private JButton[] exButtons;
        
    /**
     * Creates new form VenderJPanel
     * @param dataDetail
     */
    public UIEditJPanel(TableEditModel<T> model, boolean onlyName) {
        super(model, onlyName);
        initComponents();
        this.exButtons = new JButton[0];
    }
    
    /**
     * 初始化按钮的限制
     * @param names 
     */
    protected void enableExJButton(String... names){
        for(int i = 0; i < exButtons.length; i++){
            if (i < names.length) {
                exButtons[i].setText(names[i]);
                ButtonUtil.enable( exButtons[i], true);
                exButtons[i].setVisible(true);
            }
            else{
                ButtonUtil.enable( exButtons[i], false);
            }
        }
    }
    
    @Override
    protected void initJTableComponent(){
        super.initJTableComponent(); 
        List<DataField> fields = getJTableModel().getDetail().getClassify();
        JComboBox[] comboBoxs = new JComboBox[]{combox_0, combox_1, combox_2, combox_3};
        JLabel[] labels = new JLabel[]{lable_0, lable_1, lable_2, lable_3};
        List<JComboBox> enableList = new ArrayList<>();
        for(int i = 0; i < labels.length; i++){
            if (i < fields.size()) {
                labels[i].setText(fields.get(i).UIName + ":");
                enableList.add(comboBoxs[i]);
            }
            else{
                labels[i].setVisible(false);
                comboBoxs[i].setVisible(false);
            }
        }
        initClassifyComponents(enableList.toArray(new JComboBox[0]));
    }

    @Override
    protected void initBtnComponent() {
        super.initBtnComponent(); 
        this.exButtons = new JButton[]{btn_ex0, btn_ex1, btn_ex2};
        ActionListener actionListener = (ActionEvent e) -> {
            Object source = e.getSource();
            for(int i = 0; i < exButtons.length; i++){
                if(source == exButtons[i]){
                    onClickExButton(i);
                    break;
                }
            }
        }; 
        for(int i = 0; i < exButtons.length; i++){
            exButtons[i].addActionListener(actionListener);
            exButtons[i].setVisible(false);
        }
    }
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll_describe = new javax.swing.JScrollPane();
        txtArea_describe = new javax.swing.JTextArea();
        scroll_vender = new javax.swing.JScrollPane();
        table_vender = newJTable();
        btn_save = new javax.swing.JButton();
        btn_cancel = new javax.swing.JButton();
        combox_2 = new javax.swing.JComboBox<>();
        combox_0 = new javax.swing.JComboBox<>();
        lable_0 = new javax.swing.JLabel();
        lable_vender = new javax.swing.JLabel();
        btn_add = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        lable_1 = new javax.swing.JLabel();
        combox_1 = new javax.swing.JComboBox<>();
        txtCombox_search = new com.store.ui.common.table.TableTextComboBox();
        lable_2 = new javax.swing.JLabel();
        btn_reload = new javax.swing.JButton();
        btn_ex0 = new javax.swing.JButton();
        btn_ex1 = new javax.swing.JButton();
        btn_ex2 = new javax.swing.JButton();
        lable_3 = new javax.swing.JLabel();
        combox_3 = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(800, 600));

        txtArea_describe.setEditable(false);
        txtArea_describe.setBackground(new java.awt.Color(204, 204, 204));
        txtArea_describe.setColumns(5);
        txtArea_describe.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        txtArea_describe.setLineWrap(true);
        txtArea_describe.setRows(5);
        txtArea_describe.setAutoscrolls(false);
        txtArea_describe.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtArea_describe.setRequestFocusEnabled(false);
        scroll_describe.setViewportView(txtArea_describe);

        table_vender.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        table_vender.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table_vender.setRowHeight(22);
        table_vender.setSelectionForeground(new java.awt.Color(153, 255, 153));
        table_vender.setShowVerticalLines(false);
        table_vender.setUpdateSelectionOnSort(false);
        scroll_vender.setViewportView(table_vender);

        btn_save.setText("保存");
        btn_save.setFocusPainted(false);
        btn_save.setHideActionText(true);

        btn_cancel.setText("取消");
        btn_cancel.setFocusPainted(false);
        btn_cancel.setHideActionText(true);

        combox_2.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        combox_2.setFocusable(false);

        combox_0.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        combox_0.setPreferredSize(new java.awt.Dimension(32, 28));

        lable_0.setFont(new java.awt.Font("宋体", 1, 15)); // NOI18N
        lable_0.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lable_0.setText("种类:");
        lable_0.setFocusable(false);
        lable_0.setPreferredSize(new java.awt.Dimension(41, 25));

        lable_vender.setFont(new java.awt.Font("宋体", 1, 15)); // NOI18N
        lable_vender.setText("查找:");
        lable_vender.setFocusable(false);
        lable_vender.setPreferredSize(new java.awt.Dimension(40, 16));

        btn_add.setText("新增");
        btn_add.setFocusPainted(false);

        btn_delete.setText("删除");
        btn_delete.setFocusPainted(false);

        lable_1.setFont(new java.awt.Font("宋体", 1, 15)); // NOI18N
        lable_1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lable_1.setText("提醒:");
        lable_1.setName(""); // NOI18N

        combox_1.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N

        txtCombox_search.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N

        lable_2.setFont(new java.awt.Font("宋体", 1, 15)); // NOI18N
        lable_2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lable_2.setText("供应商:");
        lable_2.setFocusable(false);
        lable_2.setPreferredSize(new java.awt.Dimension(40, 16));

        btn_reload.setText("刷新");
        btn_reload.setFocusPainted(false);
        btn_reload.setHideActionText(true);

        btn_ex0.setText("取消");
        btn_ex0.setFocusPainted(false);
        btn_ex0.setHideActionText(true);
        btn_ex0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ex0ActionPerformed(evt);
            }
        });

        btn_ex1.setText("取消");
        btn_ex1.setFocusPainted(false);
        btn_ex1.setHideActionText(true);

        btn_ex2.setText("取消");
        btn_ex2.setFocusPainted(false);
        btn_ex2.setHideActionText(true);
        btn_ex2.setPreferredSize(new java.awt.Dimension(56, 25));

        lable_3.setFont(new java.awt.Font("宋体", 1, 15)); // NOI18N
        lable_3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lable_3.setText("供应商:");
        lable_3.setFocusable(false);
        lable_3.setPreferredSize(new java.awt.Dimension(40, 16));

        combox_3.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N
        combox_3.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lable_0, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(combox_0, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lable_1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(combox_1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lable_2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(combox_2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lable_3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(combox_3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(lable_vender, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtCombox_search, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scroll_describe, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_ex0, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ex1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_ex2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_reload, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addComponent(scroll_vender)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(combox_0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lable_1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combox_1)
                    .addComponent(lable_2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combox_2)
                    .addComponent(lable_3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combox_3)
                    .addComponent(lable_vender, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCombox_search, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lable_0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll_vender, javax.swing.GroupLayout.DEFAULT_SIZE, 720, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll_describe, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_ex0)
                        .addGap(6, 6, 6)
                        .addComponent(btn_ex1)
                        .addGap(6, 6, 6)
                        .addComponent(btn_ex2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_add)
                        .addGap(6, 6, 6)
                        .addComponent(btn_save)
                        .addGap(6, 6, 6)
                        .addComponent(btn_cancel))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_delete)
                        .addGap(6, 6, 6)
                        .addComponent(btn_reload)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_add, btn_cancel, btn_delete, btn_ex0, btn_ex1, btn_ex2, btn_reload, btn_save});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {combox_0, combox_1, combox_2, combox_3, lable_0, lable_1, lable_2, lable_3, lable_vender, txtCombox_search});

        lable_1.getAccessibleContext().setAccessibleName("提醒");
        lable_1.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ex0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ex0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ex0ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_cancel;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_ex0;
    private javax.swing.JButton btn_ex1;
    private javax.swing.JButton btn_ex2;
    private javax.swing.JButton btn_reload;
    private javax.swing.JButton btn_save;
    private javax.swing.JComboBox<String> combox_0;
    private javax.swing.JComboBox<String> combox_1;
    private javax.swing.JComboBox<String> combox_2;
    private javax.swing.JComboBox<String> combox_3;
    private javax.swing.JLabel lable_0;
    private javax.swing.JLabel lable_1;
    private javax.swing.JLabel lable_2;
    private javax.swing.JLabel lable_3;
    private javax.swing.JLabel lable_vender;
    private javax.swing.JScrollPane scroll_describe;
    private javax.swing.JScrollPane scroll_vender;
    private javax.swing.JTable table_vender;
    private javax.swing.JTextArea txtArea_describe;
    private com.store.ui.common.table.TableTextComboBox txtCombox_search;
    // End of variables declaration//GEN-END:variables

    @Override
    public JEditTable<T> getJTable() {
         return (JEditTable<T>)table_vender;
    }

    @Override
    protected JButton getAddBtn() {
        return btn_add;
    }

    @Override
    protected JButton getSaveBtn() {
         return btn_save;
    }

    @Override
    protected JButton getDeleteBtn() {
         return btn_delete;
    }

    @Override
    protected JButton getCancelBtn() {
        return btn_cancel;
    }

    @Override
    public JButton getReloadBtn() {
        return btn_reload;
    }

    @Override
    protected JTextArea getJTextArea() {
         return txtArea_describe;
    }

    @Override
    protected TableTextComboBox getSearchTextComboBox() {
        return txtCombox_search;
    }
    
    protected abstract JEditTable<T> newJTable();
    
    
    protected void onClickExButton(int index){
        
    } 
}
