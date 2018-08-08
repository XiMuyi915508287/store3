/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data;

import com.store.logger.StoreLogger;
import com.store.util.DateUtil;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author chenjingjun
 */
public class FieldReader {
    
    /**
     * 筛选
     * @param fields
     * @param UIName
     * @return 
     */
    public static DataField getField(List<DataField> fields, String UIName){
        for(DataField field : fields){
            if (field.UIName.equals(UIName)) {
                return field;
            }
        }
        return null;
    }
    
    /**
     * @param dataField
     * @param data
     * @return 
     */
    public static <T extends Data> Object getObject(DataField dataField, T data){
        Class<?> cls = data.getClass();
        while(cls != Object.class){
            java.lang.reflect.Field[] fileds = cls.getDeclaredFields();
            for(Field field : fileds){
                if (field.getName().equals(dataField.Name)) {
                    return getValue(data, dataField.Name);
                }
            }
            cls = cls.getSuperclass();
        }
        return null;
    }
    
      /**
     * 
     * @param dataField
     * @param data
     * @return 
     */
    public static <T extends Data> boolean setObject(T data, DataField dataField, Object value){
        Class<?> cls = data.getClass();
        while(cls != Object.class){
            Field[] fileds = cls.getDeclaredFields();
            for(Field field : fileds){
                if (field.getName().equals(dataField.Name)) {
                    return setValue(data, dataField.Name, value);
                }
            }
            cls = cls.getSuperclass();
        }
        return false;
    }
    /**
     * 
     * @param data
     * @param name
     * @param value
     * @return 
     */
    public static <T extends Data> boolean setValue(T data, String name, Object value){
        String upName = name.toUpperCase();
        name = upName.charAt(0) + name.substring(1);
        try {
            if (name.equals(DbDao.ID)) {
                data.setId((Long)value);
            }
            else{
                Method method = data.getClass().getMethod(String.format("set%s", name), value.getClass());
                method.invoke(data, value);
            }
            return true;
        }
        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            StoreLogger.errorf("%s:setValue(String %s, %s) error.", e, data.getClass().getSimpleName(), name, value.toString());
            return false;
        }
    }
    
    /**
     * 
     * @param data
     * @param name
     * @return 
     */
    public static <T extends Data> Object getValue(T data, String name){
        String upName = name.toUpperCase();
        name = upName.charAt(0) + name.substring(1);
        try {
            Method method = data.getClass().getMethod(String.format("get%s", name));
            return method.invoke(data);
        }
        catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            StoreLogger.errorf("%s:getValue(String name) error.", e, data.getClass().getSimpleName());
            return null;
        }
    }
    
    /**
     * 
     * @param <T>
     * @param field
     * @param value
     * @return 
     */
    public static <T extends Data> Object getObject(DataField field, String value){
        Class<?> cls = field.getFiledClass();
        if(cls == Integer.class){
            return Integer.parseInt(value);
        }
        else if(cls == Long.class){
             return Long.parseLong(value);
        }
        else if(cls == Double.class){
            return Double.parseDouble(value);
        }
        else if(cls == String.class){
            return value;
        }
        else if(cls == Timestamp.class){
             Date date = DateUtil.parseYMDHMS(value);
             return new Timestamp(date.getTime());
        }
        else if(cls == Date.class){
             return DateUtil.parseYMDHMS(value);
        }
        else {
            throw new UnsupportedOperationException();
        }
    }
    
    /**
     * 
     * @param object
     * @return 
     */
    public static boolean empty(Object object){
        Class<?> cls = object.getClass();
        if(cls == Integer.class){
            return ((Integer)object) == 0;
        }
        else if(cls == Long.class){
             return ((Long)object) == 0;
        }
        else if(cls == Double.class){
             return ((Double)object) == 0;
        }
        else if(cls == String.class){
            return ((String)object).length() == 0;
        }
        else {
            throw new UnsupportedOperationException();
        }
    }
    
    /**
     * 初始化
     * @param dataFields
     * @param cls 
     */
    public static void init(List<DataField> dataFields, Class<?> cls){
        List<Field> fieldList = new ArrayList<>();
        while(cls != Object.class){
            Field[] fileds = cls.getDeclaredFields();
            for(Field field : fileds){
                fieldList.add(field);
            }
            cls = cls.getSuperclass();
        }
        for(DataField dataField : dataFields){
            for(Field field : fieldList){
                 if (field.getName().equals(dataField.Name)) {
                     dataField.setField(field);
                     break;
                 }
             }
        }
    }
}
