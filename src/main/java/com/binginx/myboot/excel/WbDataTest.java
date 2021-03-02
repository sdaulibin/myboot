package com.binginx.myboot.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class WbDataTest {
    public static void main(String[] args) throws IOException {
        complexHeaderRead();
    }

    public static void complexHeaderRead() throws IOException {
        String fileName = "/Users/binginx/Downloads/青岛银行外包管理_月度汇总_20201001-20201028.xlsx";
//        EasyExcel.read(fileName,new WbDataListener()).sheet(0).headRowNumber(4).doRead();
        List<JSONObject> attendDataList = AttedDataUtil.getAttendDataList(fileName);
        for (JSONObject jsonObject:attendDataList) {
            log.info("解析到一条jsonObject数据:{}", jsonObject.toString());
        }
    }
}
