package com.binginx.myboot.utils;

import com.alibaba.excel.EasyExcel;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class Test {
    static int a = 0;
    static {
        a = 1;
        b = 1;
    }
    static int b = 0;

    public static void main(String[] args) {
        System.out.println("a = " + a);
        System.out.println("b = " + b);

        long timeMillis = System.currentTimeMillis();
        AtomicReferenceArray array = new AtomicReferenceArray<>(2);
        long timeId = timeMillis / 500;
        System.out.println(timeId % array.length());
        System.out.println(timeMillis - timeMillis % 500);

        List<String> result = new ArrayList<String>();

        for (int i = 0; i < 3; i++) {
            if ((i + 1) % 3 != 0 ) {
                result.add(i, Integer.toString(i));
            } else {
                result.add(i, "Fizz");
            }

        }
        System.out.println(StringUtils.join(result, "_"));
        JSONObject jsonObject = JSONObject.fromObject(new XMLSerializer().read(""));
    }
}
