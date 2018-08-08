/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.launch;

import com.store.config.Config;
import com.store.data.user.UserManager;
import com.store.db.DaoMamanger;
import com.store.logger.StoreLogger;
import com.store.ui.LoginFrame;
import com.store.ui.MainFrame;
import com.store.ui.MainLoading;
import com.store.ui.common.ConfirmDialog;

/**
 * @author chenjingjun
 */
public class StoreStarter implements Runnable{
  
    private final String args[];
    private final Thread thread;
    
    public StoreStarter(String[] args) {
        this.args = args;
        this.thread = new Thread(this);
    }
    
    public void init() throws Exception{
         //配置文件的路径
        String configPath = args[0];
         //数据文件的路径
        String xmlPath = args[1];
         //日志路径
        String logPath = args[2];
         //系统变量
        System.setProperty("store.log.pathroot", logPath);
         //配置信息
        Config.getInstance().init(configPath, xmlPath);
         //日志初始化
        StoreLogger.initLogger(Config.getInstance().getConfigPath("logConfig.xml"));
        //用户初始化
        UserManager.init();
         //初始化提示用户名
        LoginFrame.instance.initUser(UserManager.getUsers());
        //初始化确认回调
        LoginFrame.instance.initOk(new Runnable() {
            @Override
            public void run() {
                LoginFrame.instance.getLoading().start(new Runnable() {
                    @Override
                    public void run() {
                        //初始化完成了
                        LoginFrame.instance.dispose();
                        MainFrame.getInstance().finish();
                    }
                });
                thread.start();
            }
        });
    }

    @Override
    public void run() {
         try {
            DaoMamanger.beforeInit(LoginFrame.instance.getLoading());
            MainFrame.getInstance().beforeInit(LoginFrame.instance.getLoading());
            DaoMamanger.onOnit(LoginFrame.instance.getLoading());
            MainFrame.getInstance().onOnit(LoginFrame.instance.getLoading());
            StoreLogger.info("欢迎使用UI测试......");
        } catch (Exception ex) {
            ConfirmDialog.show("错误信息：%s", ex.getMessage());
            StoreLogger.error("启动报错", ex);
            System.exit(0);
        }
    }
    
    public static void main(String args[]) throws Exception {
        new StoreStarter(args).init();
    }
}
