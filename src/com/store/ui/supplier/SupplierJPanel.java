/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.supplier;

import com.store.data.supplier.SupplierDao;
import com.store.ui.TabUIType;
import com.store.ui.common.table.JEditTable;
import com.store.ui.common.panel.UIEditJPanel;

/**
 *
 * @author chenjingjun
 */
public class SupplierJPanel extends UIEditJPanel{

    public SupplierJPanel() {
        super(new SupplierModel(), true);
    }

    @Override
    protected JEditTable newJTable() {
        return new JEditTable(this);
    }

   @Override
    protected SupplierDao getDataCatalog() {
        return SupplierDao.getInstance();
    }

    @Override
    public TabUIType getTabUIType() {
        return TabUIType.Supprier;
    }
}
