package com.binginx.myboot.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class EsbData {
    @ExcelProperty(index = 0)
    private String corrId;
    @ExcelProperty(index = 1)
    private String transDoc;
}
