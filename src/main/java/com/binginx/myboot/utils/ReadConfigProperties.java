package com.binginx.myboot.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @ClassName ReadProperties
 * @Description TODO
 * @Author glory
 * @Date 2018/11/20 18:43
 * @Version v1.0
 */
public class ReadConfigProperties {
	private static ReadConfigProperties readDbProperties = null;
    private Properties properties = new Properties();
        
    private ReadConfigProperties() {
         try {
            InputStream inputStream = this.getClass().getResourceAsStream("/config.properties");
            properties.load(inputStream);
            if (inputStream != null){
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单例静态工厂方法
     * yangyang
     * 2017-12-21
     */
    public static ReadConfigProperties getInstance() {
        if (readDbProperties == null) {
        	readDbProperties = new ReadConfigProperties();
        }
        return readDbProperties;
    }

  
    /**
     * 读取配置信息key - value
     * yangyang
     * 2017-12-21
     */
    public String getConfig(String key) {
        return properties.getProperty(key);
    }

}
