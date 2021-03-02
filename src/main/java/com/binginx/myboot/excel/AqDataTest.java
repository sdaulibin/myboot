package com.binginx.myboot.excel;

import com.alibaba.excel.EasyExcel;

public class AqDataTest {
    public static void main(String[] args) {
        complexHeaderRead();
    }

    public static void complexHeaderRead() {
        String fileName2 = "/Users/binginx/Downloads/互联网类应用系统基础信息统计表（自用）-20201023.xlsx";
        EasyExcel.read(fileName2,AqData.class,new AqDataListener()).sheet(2).headRowNumber(2).doRead();
    }
}
