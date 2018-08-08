/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.ui.goods;

import com.store.data.DataField;
import com.store.data.common.Approach;
import com.store.data.goods.GoodsData;
import com.store.data.stock.StockDao;
import com.store.data.stock.StockData;
import com.store.data.user.UserManager;
import com.store.ui.common.table.TableInputDetail;
import com.store.ui.common.table.TableInputField;
import com.store.ui.common.table.TableInputModel;
import com.store.util.DateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author chenjingjun
 */
public class EntryBillModel extends TableInputModel<GoodsData> {
    
    public EntryBillModel() {
        super(GoodsData.DATA_DETAIL);
    }

    @Override
    protected void initInputDetail(TableInputDetail detail) {
        detail.add(GoodsData.DATA_DETAIL.getByUIName("类型"), TableInputField.COMBOIX, Approach.getNames());
        detail.add(GoodsData.DATA_DETAIL.getByUIName("经手人"), TableInputField.COMBOIX, UserManager.getUnderUsers());
        List<String> dateList = new ArrayList<>();
        Date date = new Date();
        int count = 30 * 24 * 60;
        for(int i = 0; i > -count; i--){
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.MINUTE, i);
            dateList.add(DateUtil.formatYMDHMS(c.getTime()));
        }
        detail.add(GoodsData.DATA_DETAIL.getByUIName("日期"), TableInputField.COMBOIX, dateList);
    }
    
      @Override
    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column);
        DataField setField = detail.getAuthoFileds().get(column);
        if (setField.UIName.equals("商品")) {
            StockData data = StockDao.getInstance().get(aValue.toString());
            if (data != null) {
                
            }
            else{
            }
        }
    }
    
    @Override
    protected boolean _isCellEditable(int rowIndex, int columnIndex) {
        DataField setField = detail.getAuthoFileds().get(columnIndex);
        if (setField.UIName.equals("订单号") || setField.UIName.equals("商品ID") || setField.UIName.equals("删除")) {
            return false;
        }
        return super._isCellEditable(rowIndex, columnIndex) ;
    }
}
