/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.db;

import com.altratek.altraserver.extensions.db.DbManager;
import com.store.util.DateUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

/**
 *
 * @author chenjingjun
 */
public class DbBackup {
    
    /**
     * @param executorPath
     * @param dictoryPath
     * @throws IOException 
     */
    public static String backup(String executorPath, String dictoryPath) throws IOException{
        Runtime runtime = Runtime.getRuntime();
        //-u后面是用户名，-p是密码-p后面最好不要有空格，-family是数据库的名字
        String executor = executorPath + "/" + "mysqldump";
        Process process = runtime.exec(executor +" -uroot -p000000 store_data");
        InputStream input = process.getInputStream();//得到输入流，写成.sql文件
        InputStreamReader reader = new InputStreamReader(input);
        BufferedReader buffer = new BufferedReader(reader);
        String str = null;
        StringBuffer sb = new StringBuffer();
        while((str = buffer.readLine()) != null){
            sb.append(str).append("\r\n");
        }
        str = sb.toString();
        System.out.println(str);
        String current = DateUtil.formatYMDHMSChinese(new Date());
        String filePath = dictoryPath + "/" + current + ".sql";
        OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(filePath),"utf-8");
        writer.write(str);
        writer.flush();
        writer.close();
        buffer.close();
        input.close();
        return filePath;
    }
    
    /**
     * @param executorPath
     * @param filePath
     * @throws IOException 
     */
    public static void recover(String executorPath, String filePath) throws IOException{
        Runtime runtime = Runtime.getRuntime();
        //-u后面是用户名，-p是密码-p后面最好不要有空格，-family是数据库的名字，--default-character-set=utf8，这句话一定的加
        //我就是因为这句话没加导致程序运行成功，但是数据库里面的内容还是以前的内容，最好写上完成的sql放到cmd中一运行才知道报错了
        //错误信息：
        //mysql: Character set 'utf-8' is not a compiled character set and is not specified in the '
        //C:\Program Files\MySQL\MySQL Server 5.5\share\charsets\Index.xml' file ERROR 2019 (HY000): Can't
        // initialize character set utf-8 (path: C:\Program Files\MySQL\MySQL Server 5.5\share\charsets\)，
        //又是讨人厌的编码问题，在恢复的时候设置一下默认的编码就可以了。
        Process process = runtime.exec("mysql -uroot -p000000 --default-character-set=utf8 store_data");
        OutputStream output = process.getOutputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)));
        String str = null;
        StringBuffer sb = new StringBuffer();
        while((str = br.readLine()) != null){
            sb.append(str).append("\r\n");
        }
        str = sb.toString();
        System.out.println(str);
        OutputStreamWriter writer = new OutputStreamWriter(output, "utf-8");
        writer.write(str);
        writer.flush();
        writer.close();
        br.close();
        output.close();
    }
    
    /***
     * 
     */
    public static void init(){
        for (IDao dao : DaoMamanger.getDaos()) {
            DbManager.getWorkDb().executeCommand(String.format("truncate %s", dao.getName()));
        }
    }
    
    /**
     * 
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException{
//        backup("E:\\software\\mysql-5.7.18-win32\\bin", "F:");
//        recover("E:\\software\\mysql-5.7.18-win32\\bin", "F:\\2017年07月20日 20时24分06秒.sql");
    }
}
