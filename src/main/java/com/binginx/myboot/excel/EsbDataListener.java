package com.binginx.myboot.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EsbDataListener extends AnalysisEventListener<EsbData> {
    private List<EsbData> esbDataList = new ArrayList<EsbData>();
    @Override
    public void invoke(EsbData esbData, AnalysisContext context) {
//        System.out.println(esbData.toString());
        esbDataList.add(esbData);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    public List<EsbData> getEsbDataList() {
        return esbDataList;
    }

    public void setEsbDataList(List<EsbData> esbDataList) {
        this.esbDataList = esbDataList;
    }
}
