package com.binginx.myboot.excel;

import com.alibaba.excel.EasyExcel;

import java.util.HashMap;
import java.util.Map;

public class FillTest {
    public static void main(String[] args) {
        // 模板注意 用{} 来表示你要用的变量 如果本来就有"{","}" 特殊字符 用"\{","\}"代替
        String templateFileName = "/Users/binginx/Downloads/lkbb.xlsx";

        // 方案1 根据对象填充
        String fileName = "/Users/binginx/Downloads/test.xlsx";

        // 这里 会填充到第一个sheet， 然后文件流会自动关闭
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("date", "2021-02-25");
        map.put("employeeNo", "employeeNo");
        map.put("employeeName","employeeName");
        EasyExcel.write(fileName).withTemplate(templateFileName).sheet().doFill(map);
    }
}
