/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui;

/**
 *
 * @author chenjingjun
 */
public interface ITabComponent {
    public void initUI(boolean realod);
    public void initOne();
    public boolean siwtchEnanble();
    public TabUIType getTabUIType();
}
