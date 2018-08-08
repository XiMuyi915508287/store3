/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.user;

import com.store.data.DataDetail;
import com.store.data.DbDao;

/**
 *
 * @author chenjingjun
 */
public class UserDao extends DbDao<UserData>{
    
    private static final UserDao instance = new UserDao();
    
    public static UserDao getInstance(){
        return instance;
    }
    
    public UserDao() {
        super("User", new UserBuilder());
    }

    @Override
    public DataDetail getDataDetail() {
        return UserData.DATA_DETAIL;
    }
}
