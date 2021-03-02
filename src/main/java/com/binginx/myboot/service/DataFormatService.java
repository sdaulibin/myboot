package com.binginx.myboot.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class DataFormatService {
    private static final XStream xStream;
    
    static {
        xStream = new XStream();
    }
    public static synchronized String dataMaptoXml(String requestString) {
        JSONObject jsonObject = JSON.parseObject(requestString);
        Map<String,Object> map = new HashMap<String,Object>();
        for (Object obj : jsonObject.entrySet()) {
            Map.Entry<String, Object> entry = (Map.Entry<String, Object>) obj;
            map.put(entry.getKey(),entry.getValue());
        }

        String result = xStream.toXML(map);
        String reqXml = "";
        if(!StringUtils.isEmpty(result))
            reqXml = strAddLen(replaceBlank("<ebank>"+result+"</ebank>"));
        log.debug("【json to xml for request Channel】\n reqXml:" + reqXml);
        return reqXml;
    }

    public static String strAddLen(String requestString){
        int length = requestString.getBytes().length;
        requestString = StringUtils.leftPad(String.valueOf(length),8,"0") + requestString;
        return requestString;
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Pattern p1 = Pattern.compile("\\t|\r|\n");
            Matcher m = null;
            if(str.indexOf("PSVR000830")>-1){
                m = p1.matcher(str);
            }else{
                m = p.matcher(str);
            }

            dest = m.replaceAll("");
        }
        return dest;
    }
}
