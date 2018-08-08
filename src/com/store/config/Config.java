/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.config;

import com.aobi.common.config.ConfReaderFactory;
import com.aobi.common.config.ConfigReader;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author chenjingjun
 */
public class Config {
    private static final Config instance = new Config();
    private static final String configFile = "conf.properties";
    
    public static Config getInstance(){
        return instance;
    }
    
    private String configPath;
    private String dataPath;
    /**
     * 用于存储配置的properties
     */
    private Properties configProperties;
    /**
     * 文本格式
     */
    private String encoding;
    
    private Config(){
    }
    
    public void init(String configPath, String dataPath) throws Exception{
        this.dataPath = dataPath;
        this.configPath = configPath;
        this.Configuration(configFile);
        ConfigReader reader = ConfReaderFactory.createConfigReader(this.configProperties);
        this.encoding = getValue("encoding").trim();
    }

    public String getXmlPath() {
        return dataPath;
    }

    public String getConfigPath() {
        return configPath;
    }
    
     public String getXmlPath(String filename) {
        return String.format("%s" + File.separator + "%s", dataPath, filename);
    }

    public String getConfigPath(String filename) {
       return String.format("%s" + File.separator + "%s", configPath, filename);
    }
    
    public String getValue(String key) {
        if (configProperties.containsKey(key)) {
            String value = configProperties.getProperty(key);
            return value;
        } else {
            return "";
        }
    }

    public String getEncoding() {
        return encoding;
    }

    private void Configuration(String filename) throws Exception {
        String filePath = getConfigPath(filename);
        configProperties = new Properties();
        FileInputStream inputFile = new FileInputStream(filePath);
        configProperties.load(inputFile);
        inputFile.close();
    }
}
