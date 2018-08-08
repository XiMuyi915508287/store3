/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data;

import com.altratek.altraserver.extensions.db.ResultObjectBuilder;
import com.store.id.IdGenerator;
import com.store.id.IdType;
import com.store.util.StringUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author chenjingjun
 */
public abstract class DataBuilder<T extends Data> implements ResultObjectBuilder{

    private final DataDetail detail;
    private final IdType idType;

    public DataBuilder(DataDetail detail, IdType idType) {
        this.detail = detail;
        this.idType = idType;
    }
    
    @Override
    public T build(ResultSet rs) throws SQLException {
        T data = _constructor();
        data.setId(rs.getLong(DbDao.ID));
        data.setDelete(rs.getInt(DbDao.DELETE));
        List<DataField> fields = detail.getFiledList();
        for(DataField field : fields){
            if (field.Name.equals(DbDao.ID)) {
                continue;
            }
            Object object = rs.getObject(field.Name);
            FieldReader.setObject(data, field, object);
        }
        return data;
    }
    
    /**
     * 
     * @return 
     */
    public T constructor(){
        T data = _constructor();
        long id = IdGenerator.getInstance().generateId(idType);
        data.setId(id);
        return data;
    }
    
    /***
     * 
     * @return 
     */
    public abstract T _constructor();
    
    
    /***
     * 检查数据是否合法
     * @param data
     * @return null 成功
     */
    public  String valid(T data){
        List<DataField> fields = detail.getFiledList();
        for(DataField field : fields){
             if (field.Null) {
                 continue;
             }
             Object object = FieldReader.getObject(field, data);
             if (object == null || StringUtil.isNullOrEmpty(object.toString())) {
                 return String.format("【%s】不能为空", field.UIName);
             }
         }
         return _valid(data);
    }
    
    /**
     * 检查数据是否合法
     * @param data
     * @return 
     */
    protected abstract String _valid(T data);
}
