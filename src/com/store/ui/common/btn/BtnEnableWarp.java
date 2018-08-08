/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common.btn;

import com.store.util.StringUtil;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JButton;

/**
 *
 * @author chenjingjun
 */
public class BtnEnableWarp {
    
    private final JButton[] buttonList;
    private final Set<String> status;

    public BtnEnableWarp(JButton... buttonList) {
        this.buttonList = buttonList;
        this.status = new HashSet<>();
    }
    
    public void enable(boolean value , String key, Object... objects){
        key = String.format("%s_%s", key, StringUtil.join(objects, ","));
        if(value){
            status.add(key);
        }
        else{
            status.remove(key);
            Iterator<String> iterator = status.iterator();
            while(iterator.hasNext()){
                //有关后缀的都删除
                if(iterator.next().indexOf(key) == 0){
                    iterator.remove();
                }
            }
        }
        enable(!status.isEmpty());
    }
    
    public void enable(boolean value){
        for(JButton btn : buttonList){
            ButtonUtil.enable(btn, value);
        }
        status.clear();
    }
    
    public boolean isEnable(){
        return buttonList[0].isEnabled();
    } 
}
