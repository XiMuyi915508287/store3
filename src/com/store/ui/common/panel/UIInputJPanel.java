/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common.panel;

import com.store.data.Data;
import com.store.data.DataField;
import com.store.ui.common.DateTableCellRenderer;
import com.store.ui.common.table.JEditTable;
import com.store.ui.common.table.TableInputField;
import com.store.ui.common.table.TableInputJPanel;
import com.store.ui.common.table.TableInputModel;
import com.store.ui.common.table.TableTextComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
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
public abstract class UIInputJPanel<T extends Data> extends TableInputJPanel<T>{

    private JButton[] exButtons;
    private final List<JComboBox<String>> jComboBoxs;
    private final List<JLabel> jComboBoxsLabels;
    private final List<TableTextComboBox> textComboBoxs;
    private final List<JLabel> textComboBoxsLabels;
        
    /**
     * Creates new form VenderJPanel
     * @param dataDetail
     */
    public UIInputJPanel(TableInputModel<T> model) {
        super(model);
        initComponents();
        this.exButtons = new JButton[0];
        this.jComboBoxs = new ArrayList<>();
        this.jComboBoxsLabels = new ArrayList<>();
        this.textComboBoxs = new ArrayList<>();
        this.textComboBoxsLabels = new ArrayList<>();
    }
    
    /**
     * 初始化按钮的限制
     * @param names 
     */
    protected void enableExJButton(String... names){
        for(int i = 0; i < exButtons.length; i++){
            if (i < names.length) {
                exButtons[i].setText(names[i]);
                exButtons[i].setVisible(true);
            }
            else{
                exButtons[i].setVisible(false);
            }
        }
    }
    
     /**
     * 初始化按钮的限制
     * @param names 
     */
    protected void enableJCombox(DataField field, List<String> valueList){
        JLabel jLabel = jComboBoxsLabels.remove(0);
        jLabel.setText(field.UIName + ":");
        jLabel.setVisible(true);
        JComboBox<String> jComboBox = jComboBoxs.remove(0);
        jComboBox.setVisible(true);
        initUpdateJComboBox(field, jComboBox, valueList);
    }
    
    /**
     * 初始化按钮的限制
     * @param names 
     */
    protected void enableJText(DataField field, List<String> valueList){
        JLabel jLabel = textComboBoxsLabels.remove(0);
        jLabel.setText(field.UIName + ":");
        jLabel.setVisible(true);
        TableTextComboBox jComboBox = textComboBoxs.remove(0);
        jComboBox.setVisible(true);
        initUpdateJFiled(field, jComboBox, valueList);
    }
    
    @Override
    protected void initJTableComponent(){
        super.initJTableComponent(); 
        JComboBox[] comboBoxs0 = new JComboBox[]{combox_0, combox_1, combox_2, combox_3};
        JLabel[] labels0 = new JLabel[]{lable_0, lable_1, lable_2, lable_3 };
        for(int i = 0; i < labels0.length; i++){
            labels0[i].setVisible(false);
            comboBoxs0[i].setVisible(false);
            jComboBoxsLabels.add(labels0[i]);
            jComboBoxs.add(comboBoxs0[i]);
        }
        TableTextComboBox[] comboBoxs1 = new TableTextComboBox[]{txt_0, txt_1, txt_2, txt_3};
        JLabel[] labels1 = new JLabel[]{lable_4, lable_5, lable_6, lable_7};
        for(int i = 0; i < labels1.length; i++){
            labels1[i].setVisible(false);
            comboBoxs1[i].setVisible(false);
            textComboBoxsLabels.add(labels1[i]);
            textComboBoxs.add(comboBoxs1[i]);
        }
        List<DataField> fields = getDataCatalog().getDataDetail().getAuthoFileds();
        for (DataField field : fields) {
           TableInputField inputField = getJTableModel().getInputDetail().get(field);
            if (inputField == null) {
                continue;
            }
            enableJCombox(field, inputField.getValueList());
        }
        getJTable().setDefaultRenderer(Timestamp.class, new DateTableCellRenderer());
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
        lable_vender = new javax.swing.JLabel();
        btn_add = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        txtCombox_search = new com.store.ui.common.table.TableTextComboBox();
        btn_reload = new javax.swing.JButton();
        btn_ex0 = new javax.swing.JButton();
        btn_ex1 = new javax.swing.JButton();
        btn_ex2 = new javax.swing.JButton();
        lable_0 = new javax.swing.JLabel();
        combox_0 = new javax.swing.JComboBox<>();
        lable_1 = new javax.swing.JLabel();
        combox_1 = new javax.swing.JComboBox<>();
        lable_2 = new javax.swing.JLabel();
        combox_2 = new javax.swing.JComboBox<>();
        lable_3 = new javax.swing.JLabel();
        combox_3 = new javax.swing.JComboBox<>();
        txt_0 = new com.store.ui.common.table.TableTextComboBox();
        lable_4 = new javax.swing.JLabel();
        lable_5 = new javax.swing.JLabel();
        txt_1 = new com.store.ui.common.table.TableTextComboBox();
        lable_6 = new javax.swing.JLabel();
        txt_2 = new com.store.ui.common.table.TableTextComboBox();
        lable_7 = new javax.swing.JLabel();
        txt_3 = new com.store.ui.common.table.TableTextComboBox();

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
        scroll_vender.setViewportView(table_vender);

        btn_save.setText("保存");
        btn_save.setFocusPainted(false);
        btn_save.setHideActionText(true);

        btn_cancel.setText("取消");
        btn_cancel.setFocusPainted(false);
        btn_cancel.setHideActionText(true);

        lable_vender.setFont(new java.awt.Font("宋体", 1, 15)); // NOI18N
        lable_vender.setText("查找:");
        lable_vender.setFocusable(false);
        lable_vender.setPreferredSize(new java.awt.Dimension(40, 16));

        btn_add.setText("新增");
        btn_add.setFocusPainted(false);

        btn_delete.setText("删除");
        btn_delete.setFocusPainted(false);

        txtCombox_search.setFont(new java.awt.Font("宋体", 0, 14)); // NOI18N

        btn_reload.setText("刷新");
        btn_reload.setFocusPainted(false);
        btn_reload.setHideActionText(true);

        btn_ex0.setText("取消");
        btn_ex0.setFocusPainted(false);
        btn_ex0.setHideActionText(true);

        btn_ex1.setText("取消");
        btn_ex1.setFocusPainted(false);
        btn_ex1.setHideActionText(true);

        btn_ex2.setText("取消");
        btn_ex2.setFocusPainted(false);
        btn_ex2.setHideActionText(true);
        btn_ex2.setPreferredSize(new java.awt.Dimension(56, 25));

        lable_0.setFont(new java.awt.Font("宋体", 1, 15)); // NOI18N
        lable_0.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lable_0.setText("种类:");
        lable_0.setFocusable(false);
        lable_0.setPreferredSize(new java.awt.Dimension(41, 25));

        combox_0.setPreferredSize(new java.awt.Dimension(32, 28));

        lable_1.setFont(new java.awt.Font("宋体", 1, 15)); // NOI18N
        lable_1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lable_1.setText("提醒:");
        lable_1.setName(""); // NOI18N

        lable_2.setFont(new java.awt.Font("宋体", 1, 15)); // NOI18N
        lable_2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lable_2.setText("供应商:");
        lable_2.setFocusable(false);
        lable_2.setPreferredSize(new java.awt.Dimension(40, 16));

        combox_2.setFocusable(false);

        lable_3.setFont(new java.awt.Font("宋体", 1, 15)); // NOI18N
        lable_3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lable_3.setText("供应商:");
        lable_3.setFocusable(false);
        lable_3.setPreferredSize(new java.awt.Dimension(40, 16));

        combox_3.setFocusable(false);

        txt_0.setPreferredSize(new java.awt.Dimension(6, 23));

        lable_4.setFont(new java.awt.Font("宋体", 1, 15)); // NOI18N
        lable_4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lable_4.setText("种类:");
        lable_4.setFocusable(false);

        lable_5.setFont(new java.awt.Font("宋体", 1, 15)); // NOI18N
        lable_5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lable_5.setText("种类:");
        lable_5.setFocusable(false);

        lable_6.setFont(new java.awt.Font("宋体", 1, 15)); // NOI18N
        lable_6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lable_6.setText("种类:");
        lable_6.setFocusable(false);

        lable_7.setFont(new java.awt.Font("宋体", 1, 15)); // NOI18N
        lable_7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lable_7.setText("种类:");
        lable_7.setFocusable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lable_0, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(combox_0, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lable_1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(combox_1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lable_2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(combox_2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lable_3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(combox_3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(lable_vender, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txtCombox_search, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lable_4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_0, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lable_5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(lable_6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(lable_7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(txt_3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(btn_reload, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(scroll_vender, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lable_0, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lable_1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combox_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lable_2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combox_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lable_3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combox_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lable_vender, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCombox_search, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combox_0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lable_4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_0, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lable_5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lable_6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lable_7, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll_vender, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scroll_describe)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_ex0)
                                .addGap(6, 6, 6)
                                .addComponent(btn_ex1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_ex2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(btn_delete)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_reload, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(btn_add)
                                        .addGap(6, 6, 6)
                                        .addComponent(btn_save)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btn_add, btn_cancel, btn_delete, btn_ex0, btn_ex1, btn_ex2, btn_save});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {combox_0, combox_1, combox_2, combox_3, lable_0, lable_1, lable_2, lable_3, lable_4, lable_5, lable_6, lable_7, lable_vender, txtCombox_search, txt_0, txt_1, txt_2, txt_3});

        txtCombox_search.getAccessibleContext().setAccessibleName("");
        txt_3.getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents


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
    private javax.swing.JLabel lable_4;
    private javax.swing.JLabel lable_5;
    private javax.swing.JLabel lable_6;
    private javax.swing.JLabel lable_7;
    private javax.swing.JLabel lable_vender;
    private javax.swing.JScrollPane scroll_describe;
    private javax.swing.JScrollPane scroll_vender;
    private javax.swing.JTable table_vender;
    private javax.swing.JTextArea txtArea_describe;
    private com.store.ui.common.table.TableTextComboBox txtCombox_search;
    private com.store.ui.common.table.TableTextComboBox txt_0;
    private com.store.ui.common.table.TableTextComboBox txt_1;
    private com.store.ui.common.table.TableTextComboBox txt_2;
    private com.store.ui.common.table.TableTextComboBox txt_3;
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
