package com.binginx.myboot.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class WbDataListener extends AnalysisEventListener<Map<Integer, String>> {
    private List<Map<Integer, String>> wbDataList = new ArrayList<Map<Integer, String>>();
    @Override
    public void invoke(Map<Integer, String> wbData, AnalysisContext context) {
        log.info("解析到一条数据:{}",JSON.toJSONString(wbData));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    public List<Map<Integer, String>> getWbDataList() {
        return wbDataList;
    }

    public void setWbDataList(List<Map<Integer, String>> wbDataList) {
        this.wbDataList = wbDataList;
    }

    /**
     * 这里会一行行的返回头
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        log.info("解析到一条头数据:{}", JSON.toJSONString(headMap));
    }
}
