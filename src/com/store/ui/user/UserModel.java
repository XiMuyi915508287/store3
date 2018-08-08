/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.user;

import com.store.data.user.UserData;
import com.store.ui.common.table.TableEditModel;

/**
 *
 * @author chenjingjun
 */
public class UserModel extends TableEditModel<UserData> {
    
    public UserModel() {
        super(UserData.DATA_DETAIL);
    }
}
