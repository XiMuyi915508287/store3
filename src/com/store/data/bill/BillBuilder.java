/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.bill;

import com.store.data.DataBuilder;
import com.store.id.IdType;

/**
 *
 * @author chenjingjun
 */
public class BillBuilder extends DataBuilder<BillData>{

    public BillBuilder() {
        super(BillData.DATA_DETAIL, IdType.BILL);
    }

    @Override
    public BillData _constructor() {
        return new BillData();
    }

    @Override
    protected String _valid(BillData data) {
        if (data.Caculate < data.Amount) {
            return "计算金额 < 应收金额 ";
        }
        else if (data.Amount < data.Received) {
              return "应收金额 < 已收金额 ";
        }
        else if(data.Amount != data.Received + data.Remain){
            return "应收金额 != 已收金额 + 未收金额 ";
        }
        return null;
    }
}
