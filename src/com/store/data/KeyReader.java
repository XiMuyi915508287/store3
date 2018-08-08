/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author chenjingjun
 */
public class KeyReader {
    private Collection<String> keys;

    public KeyReader(String key) {
        this.keys = new ArrayList<>(1);
        this.keys.add(key);
    }
    
    public KeyReader(List<String> keys) {
        this.keys = keys;
    }

    public List<String> getKeys() {
        return new ArrayList<>(keys);
    }
    
    public boolean match(String key){
        return keys.contains(key);
    }
}
