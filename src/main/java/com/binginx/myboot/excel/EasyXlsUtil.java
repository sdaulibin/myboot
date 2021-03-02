package com.binginx.myboot.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;

import java.io.IOException;
import java.util.List;

public class EasyXlsUtil {
    public static List<EsbData> saxReadJavaModelV2007(String pathFile) throws IOException {
        EsbDataListener esbDataListener = new EsbDataListener();
        ExcelReader excelReader = EasyExcel.read(pathFile, EsbData.class, esbDataListener).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
        return esbDataListener.getEsbDataList();
    }

    public static List<CampDtlReadModel> saxCampDtlReadModelV2007(String pathFile) throws IOException {
        CampDtlExcelListener excelListener = new CampDtlExcelListener();
        ExcelReader excelReader = EasyExcel.read(pathFile, CampDtlReadModel.class, excelListener).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
        excelReader.finish();
        return excelListener.getCampDtlReadModels();
    }
}
