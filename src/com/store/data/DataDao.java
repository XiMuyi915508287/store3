/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data;

import com.store.logger.StoreLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author chenjingjun
 */
public abstract class DataDao<T extends Data> implements IReloader{
    
    public final static Comparator<String> CompareName = (String o1, String o2) -> {
        int length0 = o1.length();
        int length1 = o2.length();
        int length = Math.min(length0, length1);
        for(int i = 0; i < length; i++){
            if(o1.charAt(i) == o2.charAt(i)){
                continue;
            }
            return o1.charAt(i) - o2.charAt(i);
        }
        return length0 - length1;
    };
   
    private final DataBuilder<T> builder;
    protected Map<Long, T> dataMap;
    
    private Comparator<T> compare = (T o1, T o2) -> {
       return CompareName.compare(o1.getName(), o2.getName());
    };
    
    /**
     * @param builder
     * @param fields 
     */
    public DataDao(DataBuilder<T> builder) {
        this.builder = builder;
        this.dataMap = new ConcurrentHashMap<>();
    }
    
    public ArrayList<T> getList(){
        return new ArrayList<>(dataMap.values());
    }
    
    public List<String> getNames(){
        HashSet<String> nameset = new HashSet<>();
        for (Map.Entry<Long, T> entry : dataMap.entrySet()) {
            nameset.add(entry.getValue().getName());
        }
        List<String> nameList = new ArrayList<>(nameset);
        nameList.sort(CompareName);
        return nameList;
    }
    
    @Override
    public void reload(){
        dataMap.clear();
    }

    public DataBuilder<T> getBuilder() {
        return builder;
    }
    
    public T get(long id){
        return dataMap.get(id);
    }
    
    public T get(String name){
        List<T> dataList = DataDao.this.getList(name);
        return dataList.isEmpty() ? null : dataList.get(0);
    }
    
     public List<T> getList(String name){
        List<T> dataList = new ArrayList<>();
        for (Map.Entry<Long, T> entry : dataMap.entrySet()) {
            if(entry.getValue().getName().equals(name)){
                dataList.add(entry.getValue());
            }
        }
        return dataList;
    }
    
    public List<T> remove(List<T> deleteList){
        if (deleteList.isEmpty()) {
            return deleteList;
        }
        try {
            beforeRemove(deleteList);
            for (T data : deleteList) {
                dataMap.remove(data.getId());
            }
        } catch (Exception e) {
            for (T data : deleteList) {
                data.setDelete(0);
            }
            deleteList = null;
            StoreLogger.error(String.format("删除%s数据错误", getClass().getSimpleName()), e);
        }
        return deleteList;
    }
    /**
     * 
     * @param ids
     * @return  null失败
     */
    public List<T> remove(Collection<Long> ids){
        List<T> deleteList = new ArrayList<>();
        for (Long id : ids) {
            T data = dataMap.get(id);
            if (data != null) {
                data.setDelete(1);
                deleteList.add(data);
            }
        }
        return remove(deleteList);
    }
    
     /**
     * 删除
     * @param data 
     */
    protected abstract void beforeRemove(List<T> deleteList);
    
    
    /**
     * 增加
     * @param data 
     */
    public List<T> update(Collection<T> dataList){
        if (dataList.isEmpty()) {
            return new ArrayList<>(0);
        }
        try {
            for(T data : dataList){
                data.setDelete(0);
            }
            beforeUpdate(dataList);
            for(T data : dataList){
                dataMap.put(data.getId(), data);
            }
            return new ArrayList<>(dataList);
        } catch (Exception e) {
            StoreLogger.error(String.format("更新%s数据错误", getClass().getSimpleName()), e);
            return null;
        }
    }
    
       /**
     * 删除
     * @param data 
     */
    protected abstract void beforeUpdate(Collection<T> deleteList);
    
    /**
     * 
     * @param dataList 
     */
    public void sort(List<T> dataList){
        dataList.sort(compare);
    }

    /**
     * 
     * @param compare 
     */
    protected void setCompare(Comparator<T> compare) {
        this.compare = compare;
    }
    
    /**
     * 
     * @param data
     * @return 
     */
     public String toString(T data){
        StringBuilder builder = new StringBuilder();
        List<DataField> fields = getDataDetail().getFiledList();
        builder.append("{ID:").append(data.getId());
        for (DataField field : fields) {
            builder.append(", ").append(field.Name).append(":");
            builder.append(FieldReader.getObject(field, data));
        }
        builder.append("}");
        return builder.toString();
     }

    /**
     * 返回数据定义
     * @return 
     */
    public abstract DataDetail getDataDetail();
}
