/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.user;

import com.store.data.user.UserDao;
import com.store.ui.TabUIType;
import com.store.ui.common.table.JEditTable;
import com.store.ui.common.panel.UIEditJPanel;

/**
 *
 * @author chenjingjun
 */
public class UserJPanel extends UIEditJPanel{

    public UserJPanel() {
        super(new UserModel(), true);
    }

    @Override
    protected JEditTable newJTable() {
        return new UserJTable(this);
    }

   @Override
    protected UserDao getDataCatalog() {
        return UserDao.getInstance();
    }

    @Override
    public TabUIType getTabUIType() {
        return TabUIType.User;
    }
}
