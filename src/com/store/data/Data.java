/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data;

/**
 *
 * @author chenjingjun
 */
public class Data {
    private long Id = 0;
    private String Name = "";
    private int Delete = 0;

    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name == null ? String.valueOf(Id) : Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    
    public int getDelete() {
        return Delete;
    }

    public void setDelete(int Delete) {
        this.Delete = Delete;
    }
}
