/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.goods;

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
public class GoodsData extends Data{
    
    private static final List<DataField> FIELD = new ArrayList<>();
    static {
        FIELD.add(new DataField("商品", "Name", false, false));
        FIELD.add(new DataField("商品ID", "Goods", false, false, Authority.System, new DataChangeGoods("Id")));
        FIELD.add(new DataField("删除", "GoodsDelete", false, false));
        FIELD.add(new DataField("类型", "Approach", false, true));
        FIELD.add(new DataField("经手人", "User", false, true));
        FIELD.add(new DataField("订单号", "Order", false, true));
        FIELD.add(new DateField("日期", "Date", false, true));
        FIELD.add(new DataField("数量", "Num", false, false));
        FIELD.add(new DataField("价格", "Price", false, false, Authority.Staff, new DataChangeGoods("SalePrice")));
        FIELD.add(new DataField("金额", "Amount", false, false, Authority.Staff, new DataChangeExpr("Num*Price")));
        FIELD.add(new DataField("备注", "Comment", true, false));
        FieldReader.init(FIELD, GoodsData.class);
    }
    public static final DataDetail DATA_DETAIL = new DataDetail(FIELD);
    
    protected Long Goods = 0L;
    
    protected String GoodsDelete = "否";
            
    protected String User = "";
    
    protected Long Order = 0L;
    
    protected String Approach = "";
    
    protected Integer Num = 0;
    
    protected Timestamp Date = new Timestamp(System.currentTimeMillis());
    
    protected Double Price = new Double(0);
     
    protected Double Amount = new Double(0);
    
    protected String Comment = "";

    public Long getGoods() {
        return Goods;
    }

    public void setGoods(Long Goods) {
        this.Goods = Goods;
    }

    public String getGoodsDelete() {
        return GoodsDelete;
    }

    public void setGoodsDelete(String GoodsDelete) {
        this.GoodsDelete = GoodsDelete;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }

    public Long getOrder() {
        return Order;
    }

    public void setOrder(Long Order) {
        this.Order = Order;
    }

    public String getApproach() {
        return Approach;
    }

    public void setApproach(String Approach) {
        this.Approach = Approach;
    }

    public Integer getNum() {
        return Num;
    }

    public void setNum(Integer Num) {
        this.Num = Num;
    }

    public Timestamp getDate() {
        return Date;
    }

    public void setDate(Timestamp Date) {
        this.Date = Date;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double Amount) {
        this.Amount = Amount;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }
}
