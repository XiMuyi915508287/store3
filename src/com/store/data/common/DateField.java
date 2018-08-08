/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.common;

import com.store.data.Data;
import com.store.data.DataField;
import com.store.data.KeyReader;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 *
 * @author chenjingjun
 */
public class DateField extends DataField{
    
    public DateField(String UIName, String Name, boolean Null, boolean Classify) {
        super(UIName, Name, Null, Classify);
    }

    @Override
    public final <T extends Data> KeyReader getKeyReader(T data){
        return super.getKeyReader(data);
    }
    
    @Override
    public <T extends Data> KeyReader getKeyReader(T data, Object object) {
        Date date = (Date)object;
        List<String> matchList = DateEnum.matchNames(date, new Date());
        return new KeyReader(matchList);
    }
    
    @Override
    public void sortClassifyKey(List<String> keys ){
        keys.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                DateEnum e1 = DateEnum.get(o1);
                DateEnum e2 = DateEnum.get(o2);
                return e1.ordinal() - e2.ordinal();
            }
        });
    };
}
