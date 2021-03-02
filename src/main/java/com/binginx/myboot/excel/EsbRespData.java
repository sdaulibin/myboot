package com.binginx.myboot.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class EsbRespData {
    @ExcelProperty("corrId")
    private String corrId;
    @ExcelProperty("卡号")
    private String cardNbr;
    @ExcelProperty("客户号")
    private String cifNbr;
    @ExcelProperty("账号")
    private String accNbr;
}
