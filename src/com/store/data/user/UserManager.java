/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.user;

import com.store.data.Authority;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author chenjingjun
 */
public class UserManager {
    private static String UserName;
    private static Authority UserAuthority = Authority.System;

    /**
     * 初始化
     */
    public static void init(){
        UserDao.getInstance().reload();
    }
    
    /**
     * 获取所有用户名
     * @return 
     */
    public static Collection<String> getUsers(){
        return UserDao.getInstance().getNames();
    }
    
    /**
     * @param UserName
     * @return 
     */
    public static boolean isUser(String UserName){
        UserData userData = UserDao.getInstance().get(UserName);
        return userData != null;
    }
    
    /**
     * @param UserName
     * @param Password
     * @return 
     */
    public static boolean login(String UserName, String Password){
        UserData userData = UserDao.getInstance().get(UserName);
        boolean success =  userData.getPassword().equals(Password);
        if (success) {
             UserManager.UserName = UserName;
             UserAuthority  = Authority.get(userData.getAutho());
        }
        return success;
    }
   
    /**
     * 
     * @return 
     */
    public static boolean isAdmin(){
        return UserAuthority.enable(Authority.Admin);
    }
    
    /**
     * 
     * @return 
     */
    public static boolean isSystem(){
        return UserAuthority.enable(Authority.System);
    }

    /**
     * 获取用户名
     * @return 
     */
    public static String getUser() {
        return UserName;
    }

    /**
     * 
     * @return 
     */
    public static Authority getAuthority() {
        return UserAuthority;
    }
    
    
    /**
     * 获取下属用户名
     * @return 
     */
    public static List<String> getUnderUsers(){
        //刷新一下
        UserDao.getInstance().reload();
        List<String> userList = UserDao.getInstance().getNames();
        for(int i = userList.size() - 1; i >=0; i--){
            UserData data = UserDao.getInstance().get(userList.get(i));
            Authority authority  = Authority.get(data.getAutho());
            if (UserAuthority.enable(authority)) {
                continue;
            }
            userList.remove(i);
        }
        return userList;
    }
}
