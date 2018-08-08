/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.user;

import com.store.data.Authority;
import com.store.data.Data;
import com.store.data.DataDetail;
import com.store.data.DataField;
import com.store.data.FieldReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chenjingjun
 */
public class UserData extends Data{
    
    private static final List<DataField> FIELD = new ArrayList<>();
    static {
        FIELD.add(new DataField("名称", "Name", false, false));
        FIELD.add(new DataField("权限", "Autho", false, true));
        FIELD.add(new DataField("密码", "Password", false, false, Authority.Admin));
        FIELD.add(new DataField("联系方式", "Phone", true, false));
        FIELD.add(new DataField("地址", "Address", true, false));
        FIELD.add(new DataField("备注", "Comment", true, false));
        FieldReader.init(FIELD, UserData.class);
    }
    public static final DataDetail DATA_DETAIL = new DataDetail(FIELD);
    
    private String Autho = Authority.Staff.Name;
    private String Password = "";
    private String Phone = "";
    private String Address = "";
    private String Comment = "";

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getAutho() {
        return Autho;
    }

    public void setAutho(String Autho) {
        this.Autho = Autho;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }
}
