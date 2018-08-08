/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data;

import com.pjfun.common.eval.TExprParams;
import com.pjfun.common.eval.TExprParser;

/**
 *
 * @author chenjingjun
 */
public class DataChangeExpr implements IDataChange{

    private final TExprParser parser;

    public DataChangeExpr(String expresssion) {
         parser = new TExprParser(expresssion);
    }
    
    @Override
    public Object getValue(TExprParams exprParams) {
        return parser.calculate(exprParams);
    }

    @Override
    public boolean containKeys(String Name) {
        return parser.getExpression().contains(Name);
    }
}
