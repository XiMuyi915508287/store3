/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data;

import com.pjfun.common.eval.TExprParams;

/**
 *
 * @author chenjingjun
 */
public interface IDataChange {
    
    /***
     * 
     * @param exprParams
     * @return 
     */
    public Object getValue(TExprParams exprParams);
    
    /**
     * @return 
     */
    public boolean containKeys(String Name);
}
