package com.binginx.myboot.excel;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.*;

@Slf4j
public class AttendDataListener extends AnalysisEventListener<Map<Integer, String>> {
    private List<JSONObject> attendDataList = new ArrayList<JSONObject>();
    private Map<String,Integer> attendDataHead = new HashMap<String,Integer>();

    public Map<String, Integer> getAttendDataHead() {
        return attendDataHead;
    }

    public void setAttendDataHead(Map<String, Integer> attendDataHead) {
        this.attendDataHead = attendDataHead;
    }

    @Override
    public void invoke(Map<Integer, String> attendData, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(attendData));
        //attendDataList.add(JSON.parseObject(JSON.toJSONString(attendData)));

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
    }

    public List<JSONObject> getAttendDataList() {
        return attendDataList;
    }

    public void setAttendDataList(List<JSONObject> attendDataList) {
        this.attendDataList = attendDataList;
    }

    /**
     * 这里会一行行的返回头
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        String attendanceDate= DateUtil.now();
        int dayKey = 48;
        log.info("解析到一条头数据:{}", JSON.toJSONString(headMap).toString());
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(headMap));
        if(jsonObject.containsKey("0")) {
            String line = jsonObject.get("0").toString();
            if(StrUtil.isNotEmpty(line) && StrUtil.contains(line,"月度汇总 统计日期：")) {
                attendanceDate = StrUtil.subWithLength(line,"月度汇总 统计日期：".length(),10);
                log.info("解析到一条头数据:{}", attendanceDate);
            }
        }
        do {
            log.info("{}:{}",dayKey,jsonObject.get(String.valueOf(dayKey)));
            dayKey++;
        } while (jsonObject.containsKey(String.valueOf(dayKey)));
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put(attendanceDate,dayKey-48);
    }
}
