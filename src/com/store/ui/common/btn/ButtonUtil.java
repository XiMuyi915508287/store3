/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common.btn;

import javax.swing.JButton;

/**
 *
 * @author chenjingjun
 */
public class ButtonUtil {
    
    public static void enable(JButton button, boolean value){
        button.setEnabled(value);
        button.setHideActionText(false);
        button.updateUI();
    }
}
