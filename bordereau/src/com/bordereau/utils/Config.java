package com.bordereau.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {

    private static Properties prop = new Properties();
    
    public static String getListMandatPath(){
        String listMandatPath = "";
        
        try {
            prop.load(new FileInputStream("resources/config.properties"));
            listMandatPath = prop.getProperty("listMandats");
        } catch (Exception ex) {
            Log.msg(1, "getListMandatPath EX : " + ex.getMessage());
        }
        
        return listMandatPath;
    }    
}
