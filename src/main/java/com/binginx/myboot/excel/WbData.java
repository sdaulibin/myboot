package com.binginx.myboot.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class WbData {
    @ExcelProperty(index = 0)
    private String name;
    @ExcelProperty(index = 2)
    private String department;
    @ExcelProperty(index = 5)
    private String userId;
    @ExcelProperty("考勤结果")
    private String details;
}
