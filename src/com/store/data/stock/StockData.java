/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.stock;

import com.store.data.Authority;
import com.store.data.Data;
import com.store.data.DataDetail;
import com.store.data.DataField;
import com.store.data.FieldReader;
import com.store.data.KeyReader;
import com.store.data.common.Unit;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjingjun
 */
public class StockData extends Data{
    
    private static final List<DataField> FIELD = new ArrayList<>();
    static {
        FIELD.add(new DataField("商品ID", "Id", false, false, Authority.System));
        FIELD.add(new DataField("商品", "Name", false, false));
        FIELD.add(new DataField("类型", "Type", false, true));
        FIELD.add(new NumField("数量", "Num", false, true));
        FIELD.add(new DataField("单位", "Unit", false, false));
        FIELD.add(new DataField("进货价格", "GetPrice", false, false, Authority.Admin));
        FIELD.add(new DataField("销售价格", "SalePrice", false, false));
        FIELD.add(new DataField("提醒数量", "NoticeNum", false, false));
        FIELD.add(new DataField("供应商", "Vender", false, true));
        FIELD.add(new DataField("备注", "Comment", true, false));
        FieldReader.init(FIELD, StockData.class);
    }
    public static final DataDetail DATA_DETAIL = new DataDetail(FIELD);
    
    protected String Type = "";
    
    protected Integer Num = 0;
    
    protected String Unit = "件";
      
    protected Double GetPrice = new Double(0);
     
    protected Double SalePrice = new Double(0);
    
    protected Integer NoticeNum  = 0;
     
    protected String Vender= "";
    
    protected String Comment = "";

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public Integer getNum() {
        return Num;
    }

    public void setNum(Integer Num) {
        this.Num = Num;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }
    
    public Double getGetPrice() {
        return GetPrice;
    }

    public void setGetPrice(Double GetPrice) {
        this.GetPrice = GetPrice;
    }

    public Double getSalePrice() {
        return SalePrice;
    }

    public void setSalePrice(Double SalePrice) {
        this.SalePrice = SalePrice;
    }

    public Integer getNoticeNum() {
        return NoticeNum;
    }

    public void setNoticeNum(Integer NoticeNum) {
        this.NoticeNum = NoticeNum;
    }

    public String getVender() {
        return Vender;
    }

    public void setVender(String Vender) {
        this.Vender = Vender;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }
    
    public static class NumField extends DataField{
        public static final String NONE = "无货";
        public static final String LESS = "提醒";
        public static final String MORE = "足够";

        public NumField(String UIName, String Name, boolean Null, boolean Classify) {
            super(UIName, Name, Null, Classify);
        }
        
        @Override
        public <T extends Data> KeyReader getKeyReader(T data,Object object) {
            Integer Num = (Integer)object;
            if (Num <= 0) {
                return new KeyReader(NONE);
            }
            DataField field = getDetail().getByName("NoticeNum");
            Integer NoticeNum = (Integer)FieldReader.getObject(field, data);
            return Num > NoticeNum ? new KeyReader(MORE) : new KeyReader(LESS);
        }
    }
}
