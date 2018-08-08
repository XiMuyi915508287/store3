/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.util;

import com.store.config.Config;
import java.io.File;

/**
 *
 * @author chenjingjun
 */
public class PathHelper {
    
    /**
     * xml数据文件
     * @param fileName
     * @return
     */
    public static String joinDataPath(String fileName) {
        return Config.getInstance().getXmlPath()+ '/' + fileName;
    }
    
    /**
     * 配置的路径
     * @param fileName
     * @return 
     */
    public static String joinConfigPath(String fileName) {
         return Config.getInstance().getConfigPath()+ File.separator + fileName;
    }
}
