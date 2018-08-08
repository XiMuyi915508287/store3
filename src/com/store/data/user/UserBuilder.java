/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.user;

import com.store.data.DataBuilder;
import com.store.id.IdType;

/**
 *
 * @author chenjingjun
 */
public class UserBuilder extends DataBuilder<UserData>{

    public UserBuilder() {
        super(UserData.DATA_DETAIL, IdType.USER);
    }

    @Override
    public UserData _constructor() {
         return new UserData();
    }

    @Override
    protected String _valid(UserData data) {
        return null;
    }
}
