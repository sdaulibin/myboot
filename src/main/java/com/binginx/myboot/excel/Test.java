package com.binginx.myboot.excel;

import cn.hutool.core.util.NumberUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.poifs.filesystem.FileMagic;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class Test {
    public static List<SmsInfoReadModel> saxReadJavaModelV2007(String pathFile) throws IOException {
        SmsExcelListener excelListener = new SmsExcelListener();
        ExcelReader excelReader = EasyExcel.read(pathFile, SmsInfoReadModel.class, excelListener).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
        return excelListener.getSmsInfoReadModels();
    }


    public static ExcelTypeEnum valueOf(InputStream inputStream) {
        try {
            FileMagic fileMagic = FileMagic.valueOf(inputStream);
            if (FileMagic.OOXML.equals(fileMagic)) {
                return ExcelTypeEnum.XLSX;
            }
            return ExcelTypeEnum.XLS;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) throws IOException {
        String filepath = "/Users/binginx/Downloads/MCI-CUSTLST-20201221000001.xlsx";
//        String filepath = "/Users/binginx/Downloads/MCI-SMS-20201112000004-2.xlsx";
//        List<SmsInfoReadModel> smsInfoReadModels = saxReadJavaModelV2007(filepath);
//        for (SmsInfoReadModel smsInfoReadModel:smsInfoReadModels) {
//            if(!NumberUtil.isNumber(smsInfoReadModel.getCustNbr())) {
//                log.info("smsInfoReadModel====>>>{}", smsInfoReadModel);
//            }
//        }
//        List<CampDtlReadModel> infoReadModels = EasyXlsUtil.saxCampDtlReadModelV2007(filepath);
    }
}
