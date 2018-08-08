/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.logger;

import com.store.config.Config;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 *
 * @author chenjingjun
 */
public class StoreLogger {
    private static Logger logger;
    private static Logger behavior;

    public static void initLogger(String log4jxml) {
        DOMConfigurator.configure(log4jxml);
        logger = Logger.getLogger("Logger");
        behavior = Logger.getLogger("Behavior");
    }
    
    public static void debug(String msg) {
        logger.debug(msg);
    }

    public static void debugf(String msg, Object... args) {
        debug(String.format(msg, args));
    }

    public static void info(String msg) {
        logger.info(msg);
    }

    public static void infof(String msg, Object... args) {
        info(String.format(msg, args));
    }

    public static void error(String msg) {
        logger.error(msg);
    }

    public static void errorf(String msg, Object... args) {
        error(String.format(msg, args));
    }

    public static void error(String msg, Throwable t) {
        logger.error(msg, t);
    }

    public static void errorf(String msg, Throwable t, Object... args) {
        error(String.format(msg, args), t);
    }
    
    public static void behavior(String msg) {
         behavior.info(msg);
    }
    
    public static void behaviorf(String msg, Object... args) {
        behavior(String.format(msg, args));
    }
}
