/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data;

import com.altratek.altraserver.extensions.db.DbManager;
import com.store.db.IDao;
import com.store.util.StringUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author chenjingjun
 */
public abstract class DbDao<T extends Data> extends DataDao<T> implements IDao{
    public static final String ID = "Id";
    public static final String DELETE = "Delete";
    private static final String SELECT_CMD = "SELECT * FROM %s WHERE `Delete`=0;";
    private static final String REPLACE_CMD = "REPLACE INTO %s VALUES (%s)";
   
    public final String tableName;
    private final String cmd_select;
    private final String cmd_replace;
    
    /**
     * @param builder
     * @param fields 
     */
    public DbDao(String tableName, DataBuilder<T> builder) {
        super(builder);
        this.tableName = tableName;
        //一般不会这样子调用
        List<DataField> fields = getDataDetail().getFiledList();
        List<String> symbolitems =  new ArrayList<>(fields.size() + 2);
        int count = 0;
        for (DataField field : fields) {
            if(!field.Name.equals(ID)){
                count++;
            }
        }
        count += 2;
        while (count--> 0) {            
            symbolitems.add("?");
        }
        String symbolString = StringUtil.join(symbolitems, ",");
        cmd_select = String.format(SELECT_CMD, tableName);
        cmd_replace = String.format(REPLACE_CMD, tableName, symbolString);
    }

    @Override
    public String getName() {
        return tableName;
    }
    
    @Override
    public void reload(){
        super.reload();
        List<T> dataList = loadList();
        for(T data : dataList){
            data.setDelete(0);
            dataMap.put(data.getId(), data);
        }
    }
    
    @Override
    protected void beforeRemove(List<T> deleteList){
        updateDb(deleteList);
    }
    
    @Override
    protected void beforeUpdate(Collection<T> deleteList){
        updateDb(deleteList);
    }
    
    /**
     * @return
     */
    public Object[] toObjects(T data){
        List<Object> objects = new ArrayList<>();
        objects.add(data.getId());
        objects.addAll(toExtLis(data));
        objects.add(data.getDelete());
        return objects.toArray();
    }
    
     /**
     * @return
     */
    private List<Object> toExtLis(T data){
        List<Object> objects = new ArrayList<>();
        List<DataField> fields = getDataDetail().getFiledList();
        for(DataField field : fields){
            if(field.Name.equals(ID)){
                continue;
            }
            objects.add(FieldReader.getObject(field, data));
        }
        return objects;
    }
    
    protected List<T> loadList(){
        return DbManager.getWorkDb().executeQuery_ObjectListEx(cmd_select, getBuilder());
    }
    
    protected void updateDb(Collection<T> dataList){
        Collection<Object[]> params = new ArrayList<>(dataList.size());
        for (T data : dataList) {
            params.add(toObjects(data));
        }
        DbManager.getWorkDb().executeBatchCommandEx(cmd_replace, params);
    }
}
