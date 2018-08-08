/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui;

import com.store.ui.bill.BillJPanel;
import com.store.ui.goods.EntryBillJPanel;
import com.store.ui.goods.GoodsJPanel;
import com.store.ui.setting.SettingJPanel;
import com.store.ui.stock.StockJPanel;
import com.store.ui.supplier.SupplierJPanel;
import com.store.ui.user.UserJPanel;

/**
 *
 * @author chenjingjun
 */
public enum TabUIType {
    Stock("库存", true, new StockJPanel()),
    Goods("出/入库", true, new GoodsJPanel()),
    Bill("账单", true, new BillJPanel()),
    EntryBill("账单操作", false, new EntryBillJPanel()),
    Supprier("供应商", true, new SupplierJPanel()),
    User("用户", true, new UserJPanel()),
    Setting("设置", true, new SettingJPanel()),
    ;
    public final int id;
    public final String name;
    public final boolean initUI;
    public final ITabComponent component;
     
    private TabUIType(String name, boolean initUI, ITabComponent component) {
        this.id = TabUIID.getAngIncrease();
        this.name = name;
        this.initUI = initUI;
        this.component = component;
    }
    
    public <T extends ITabComponent> T getComponent(){
        return (T)component;
    }
    
    public static TabUIType getTabUIType(String name){
          for(TabUIType uIType : TabUIType.values()){
              if(uIType.name.equals(name)){
                  return uIType;
              }
          }
          return null;
    }
    
     public static TabUIType getTabUIType(int index){
          for(TabUIType uIType : TabUIType.values()){
              if(uIType.id == index){
                  return uIType;
              }
          }
          return null;
    }
     
    public static int caculate = 0;
}
