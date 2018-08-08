/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author chenjingjun
 */
public enum Authority {
    System(1, "系统"),
    
    Admin(101, "管理员"),
    
    Staff(201, "员工"),
    ;
    
    public final int Id;
    public final String Name;

    private Authority(int Id, String Name) {
        this.Id = Id;
        this.Name = Name;
    }
    
    /**
     * 是否满足权限
     * @param authority
     * @return 
     */
    public boolean enable(Authority authority){
        return this.Id <= authority.Id;
    }
    
    /**
     * 
     * @param name
     * @return 
     */
    public static Authority get(String name){
        for(Authority authority : values()){
            if(authority.Name.equals(name)){
                return authority;
            }
        }
        return null;
    }
    
    /**
     * 
     * @param authority
     * @return 
     */
    public static List<String> getEnable(Authority authority){
        List<String> matchList = new ArrayList<>(10);
        for(Authority a : values()){
            if (authority.enable(a)) {
                matchList.add(a.Name);
            }
        }
        return matchList;
    }
}
