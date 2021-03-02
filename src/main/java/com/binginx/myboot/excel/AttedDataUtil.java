package com.binginx.myboot.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;

@Slf4j
public class AttedDataUtil {
    public static List<JSONObject> getAttendDataList(String pathFile) throws IOException {
        AttendDataListener attendDataListener = new AttendDataListener();
        ExcelReader excelReader = EasyExcel.read(pathFile, attendDataListener).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).headRowNumber(4).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
        return attendDataListener.getAttendDataList();
    }
}
