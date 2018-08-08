/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aobi.common;

import com.store.logger.StoreLogger;

/**
 *
 * @author chenjingjun
 */
public class MyApplicationLocal extends ApplicationLocal {

    @Override
    public void info(String string) {
        StoreLogger.info(string);
    }

    @Override
    public void error(String string) {
        StoreLogger.error(string);
    }

    @Override
    public void error(String string, Throwable thrwbl) {
        StoreLogger.error(string, thrwbl);
    }
}
