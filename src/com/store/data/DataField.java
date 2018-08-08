/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data;

import com.pjfun.common.eval.TExprParser;
import com.pjfun.common.util.StringUtil;
import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * @author chenjingjun
 */
public class DataField {
    
    public final String UIName;
    public final String Name;
    public final boolean  Null;
    public final boolean Classify;
    public final Authority authority;
    private final IDataChange dataChange;
    
    
    private Field field;
    private DataDetail detail;

    public DataField(String UIName, String Name, boolean Null, boolean Classify, Authority authority, IDataChange dataChange) {
        this.UIName = UIName;
        this.Name = Name;
        this.Null = Null;
        this.Classify = Classify;
        this.authority = authority;
        if (dataChange == null) {
            this.dataChange = new DataChangeExpr(Name);
        }
        else{
            this.dataChange = dataChange;
        }
    }
    
    public DataField(String UIName, String Name, boolean Null, boolean Classify, Authority authority) {
       this(UIName, Name, Null, Classify, authority, null);
    }
    
    public DataField(String UIName, String Name, boolean Null, boolean Classify) {
        this(UIName, Name, Null, Classify, Authority.Staff, null);
    }
    
    public Class<?> getFiledClass(){
        return field.getType();
    }
    
    public <T extends Data> KeyReader getKeyReader(T data){
        Object object = FieldReader.getObject(this, data);
        return getKeyReader(data, object);
    };
    
    public <T extends Data> KeyReader getKeyReader(T data, Object object){
        return new KeyReader(object.toString());
    };
    
    public void sortClassifyKey(List<String> keys ){
    };

    public Field getField() {
        return field;
    }

    protected void setField(Field field) {
        this.field = field;
    }

    public DataDetail getDetail() {
        return detail;
    }

    protected void setDetail(DataDetail detail) {
        this.detail = detail;
    }

    public IDataChange getParser() {
        return dataChange;
    }
}
