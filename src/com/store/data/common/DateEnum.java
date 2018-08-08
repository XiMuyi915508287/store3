/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.data.common;

import com.store.util.DateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author chenjingjun
 */
public enum DateEnum {
    A0("今天") {
        @Override
        protected boolean match(Date current, Date history) {
            return DateUtil.isSameDate(current, history);
        }
    },
    A2("昨天") {
        @Override
        protected boolean match(Date current, Date history) {
            return DateUtil.isYesterday(current, history);
        }
    },
    B1("一个星期") {
        @Override
        protected boolean match(Date current, Date history) {
             return DateUtil.isSameWeely(current, history);
        }
    },
    C1("今年") {
        @Override
        protected boolean match(Date current, Date history) {
            return DateUtil.isSameYear(current, history);
        }
    },
    D1("一个月内") {
        @Override
        protected boolean match(Date current, Date history) {
            return DateUtil.isSameMonth(current, history);
        }
    },
    D2("两个月内") {
        @Override
        protected boolean match(Date current, Date history) {
           return DateUtil.differMonth(current, history) <= 2;
        }
    },
    D3("三个月内") {
        @Override
        protected boolean match(Date current, Date history) {
           return DateUtil.differMonth(current, history) <= 3;
        }
    },
    E1("半年内") {
        @Override
        protected boolean match(Date current, Date history) {
           return DateUtil.differMonth(current, history) <= 6;
        }
    },
    E2("半年前") {
        @Override
        protected boolean match(Date current, Date history) {
           return DateUtil.differMonth(current, history) > 6;
        }
    },
    E3("2017年") {
        @Override
        protected boolean match(Date current, Date history) {
             Calendar calendar1 = Calendar.getInstance();
             return calendar1.get(Calendar.YEAR) == 2017;
        }
    },
    E4("2018年") {
        @Override
        protected boolean match(Date current, Date history) {
            Calendar calendar1 = Calendar.getInstance();
            return calendar1.get(Calendar.YEAR) == 2018;
        }
    },
    E5("2019年") {
        @Override
        protected boolean match(Date current, Date history) {
            Calendar calendar1 = Calendar.getInstance();
            return calendar1.get(Calendar.YEAR) == 2019;
        }
    },
    E6("2020年") {
        @Override
        protected boolean match(Date current, Date history) {
            Calendar calendar1 = Calendar.getInstance();
            return calendar1.get(Calendar.YEAR) == 2020;
        }
    },
    ;
    public final String name;

    private DateEnum( String name) {
        this.name = name;
    }
    
    protected abstract boolean match(Date current, Date history);
    
    @Override
    public String toString(){
        return name;
    }
    
    public static DateEnum get(String name){
        for(DateEnum dateEnum : values()){
            if(dateEnum.name.equals(name)){
                return dateEnum;
            }
        }
        return null;
    }
    
    public static List<String> matchNames(Date current, Date history){
        List<String> matchList = new ArrayList<>(10);
        for(DateEnum dateEnum : values()){
            if(dateEnum.match(current, history)){
                matchList.add(dateEnum.name);
            }
        }
        return matchList;
    }
}
