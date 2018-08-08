/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.common;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chenjingjun
 */
public enum Approach {
    Sale("销售", -1),
    Vender("进货", 1),
    Broken("报废", -1),
    Return("退货", 1),;
    
    public final String Name;
    public final int stockOperator;

    private Approach(String Name, int base) {
        this.Name = Name;
        this.stockOperator = base;
    }
    
    public static Approach get(String name){
        for(Approach approach : values()){
            if(approach.Name.equals(name)){
                return approach;
            }
        }
        return null;
    }
    
      /**
     * 
     * @param name
     * @return 
     */
    public static List<String> getNames(){
        List<String> matchList = new ArrayList<>(10);
        for(Approach approach : values()){
            matchList.add(approach.Name);
        }
        return matchList;
    }
}
