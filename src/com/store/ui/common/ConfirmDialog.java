/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.common;

import javax.swing.JOptionPane;

/**
 *
 * @author chenjingjun
 */
public class ConfirmDialog{
    
    public static boolean confirm(String message, Object... objects){
       message = String.format(message, objects);
       int success = JOptionPane.showConfirmDialog(null, message, "确认对话框", JOptionPane.YES_NO_OPTION);   
       return success == JOptionPane.YES_OPTION;
    }
    
    public static void show(String message, Object... objects){
       message = String.format(message, objects);
       JOptionPane.showMessageDialog(null, message);   
    }
}
