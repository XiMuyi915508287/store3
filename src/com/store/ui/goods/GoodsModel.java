/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.goods;

import com.store.data.goods.GoodsData;
import com.store.ui.common.table.TableEditModel;

/**
 *
 * @author chenjingjun
 */
public class GoodsModel extends TableEditModel<GoodsData> {
    
    public GoodsModel() {
        super(GoodsData.DATA_DETAIL);
    }
    
    @Override
    protected boolean _isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
