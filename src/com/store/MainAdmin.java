package com.store;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.store.launch.StoreStarter;

/**
 *
 * @author chenjingjun
 */
public class MainAdmin {
      public static void main(String[] args) throws Exception{
          StoreStarter.main(new String[]{"./config", "./data", "./logs"});
      }
}

