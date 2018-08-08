/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.common;

import static com.store.data.common.Approach.values;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chenjingjun
 */
public enum Unit {
    件(),
    套(),
    只(),
    个(),
    条(),
    米(),
    厘米(),
    ;
    
      /**
     * 
     * @param name
     * @return 
     */
    public static List<String> getNames(){
        List<String> matchList = new ArrayList<>(10);
        for(Unit unit : values()){
            matchList.add(unit.name());
        }
        return matchList;
    }
}
