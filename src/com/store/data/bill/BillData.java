/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.bill;

import com.store.data.Authority;
import com.store.data.Data;
import com.store.data.DataChangeExpr;
import com.store.data.DataDetail;
import com.store.data.DataField;
import com.store.data.FieldReader;
import com.store.data.common.DateField;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjingjun
 */
public class BillData extends Data{
    
    private static final List<DataField> FIELD = new ArrayList<>();
    static {
        FIELD.add(new DataField("订单号", "Id", false, false));
        FIELD.add(new DataField("经手人", "Name", false, true));
        FIELD.add(new DataField("类型", "Approach", false, true));
        FIELD.add(new DateField("日期", "Date", false, true));
        FIELD.add(new DataField("计算金额", "Caculate", false, false));
        FIELD.add(new DataField("应收金额", "Amount", false, false, Authority.Staff, new DataChangeExpr("Caculate")));
        FIELD.add(new DataField("已收金额", "Received", false, false, Authority.Staff, new DataChangeExpr("Amount")));
        FIELD.add(new DataField("还欠金额", "Remain", false, false, Authority.Staff, new DataChangeExpr("Amount-Received")));
        FIELD.add(new DataField("相关商品", "Goods", true, false, Authority.System));
        FIELD.add(new DataField("备注", "Comment", true, false));
        FieldReader.init(FIELD, BillData.class);
    }
    public static final DataDetail DATA_DETAIL = new DataDetail(FIELD);
    
    protected String Approach = "";
    
    protected Timestamp Date = new Timestamp(System.currentTimeMillis());
    
    protected Double Caculate = new Double(0);
     
    protected Double Amount = new Double(0);
    
    protected Double Received = new Double(0);
     
    protected Double Remain = new Double(0);
    
    protected String Goods = "";
    
    protected String Comment = "";
    
    public Long getOrder() {
        return getId();
    }
    
    public void setOrder(Long Order) {
        this.setId(Order);
    }
    
    public String getUser() {
        return getName();
    }

    public void setUser(String User) {
        this.setName(User);
    }

    public String getApproach() {
        return Approach;
    }

    public void setApproach(String Approach) {
        this.Approach = Approach;
    }

    public Timestamp getDate() {
        return Date;
    }

    public void setDate(Timestamp Date) {
        this.Date = Date;
    }

    public Double getCaculate() {
        return Caculate;
    }

    public void setCaculate(Double Caculate) {
        this.Caculate = Caculate;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double Amount) {
        this.Amount = Amount;
    }

    public Double getReceived() {
        return Received;
    }

    public void setReceived(Double Received) {
        this.Received = Received;
    }

    public Double getRemain() {
        return Remain;
    }

    public void setRemain(Double Remain) {
        this.Remain = Remain;
    }

    public String getGoods() {
        return Goods;
    }

    public void setGoods(String Goods) {
        this.Goods = Goods;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }
}
