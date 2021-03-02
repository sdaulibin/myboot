package com.binginx.myboot.excel;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.xml.XMLSerializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class Main {
    public static void main(String[] args) {
        List<EsbData> esbDataList = null;
        try {
            esbDataList = EasyXlsUtil.saxReadJavaModelV2007("/Users/binginx/Desktop/esb-20200803/10.1.52.53-9.50-50-10.10.xls");
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<EsbRespData> esbRespDataList = new ArrayList<>();
//        Map<String,String> map = new HashMap<>();
        Map<String,EsbRespData> esbRespDataMap = new HashMap<>();
        for (EsbData esbData:esbDataList) {
            EsbRespData esbRespData = new EsbRespData();
//            System.out.println(esbData.toString());
            JSONObject jsonObject = null;
            try {
                jsonObject = JSONObject.fromObject(new XMLSerializer().read(esbData.getTransDoc()));;
            } catch (Exception e) {
                System.out.println(esbData.getCorrId());
                continue;
            }
            JSONObject svcHdr = (JSONObject) jsonObject.get("svcHdr");
            String corrId = svcHdr.getString("corrId");
            if(corrId.equals(esbData.getCorrId())) {
                esbRespData.setCorrId(corrId);
                if(!svcHdr.containsKey("respCde")) {
                    JSONObject appBody = jsonObject.getJSONObject("appBody");
                    String cardNbr = appBody.getString("cardNbr");
                    String cifNo = appBody.getString("cifNo");
                    if(StrUtil.isEmpty(cardNbr) || "[]".equals(cardNbr)) {
                        cifNo = appBody.getString("cifNo");
                        esbRespData.setCifNbr(cifNo);
                    } else {
                        esbRespData.setCardNbr(cardNbr);
                        esbRespData.setCifNbr(cifNo.replace("[]",""));
                    }
                    //System.out.println("cardNbr========"+cardNbr);
                    if(ObjectUtil.isNotEmpty(esbRespDataMap.get(corrId))) {
                        EsbRespData respData = esbRespDataMap.get(corrId);
                        respData.setCardNbr(esbRespData.getCardNbr());
                        respData.setCifNbr(esbRespData.getCifNbr());
                        esbRespDataMap.put(corrId,respData);
                    } else {
                        esbRespDataMap.put(corrId,esbRespData);
                    }
                } else {
                    JSONObject appBody = jsonObject.getJSONObject("appBody");
                    Object custField = appBody.get("custField");
                    if(custField instanceof JSONObject) {
                        String aCNbr = ((JSONObject) custField).getString("aCNbr");
                        esbRespData.setAccNbr(aCNbr);
                        if(ObjectUtil.isNotEmpty(esbRespDataMap.get(corrId))) {
                            EsbRespData respData = esbRespDataMap.get(corrId);
                            respData.setAccNbr(aCNbr);
                            esbRespDataMap.put(corrId,respData);
                        } else {
                            esbRespDataMap.put(corrId,esbRespData);
                        }
                    } else if(custField instanceof JSONArray) {
                        JSONArray jsonArray = (JSONArray) custField;
                        for (int i = 0; i < jsonArray.size(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String aCNbr = object.getString("aCNbr");
                            if(!StrUtil.isEmpty(esbRespData.getAccNbr())) {
                                esbRespData.setAccNbr(esbRespData.getAccNbr() + "|" +aCNbr);
                            }else {
                                esbRespData.setAccNbr(aCNbr);
                            }
                        }
                        if(ObjectUtil.isNotEmpty(esbRespDataMap.get(corrId))) {
                            EsbRespData respData = esbRespDataMap.get(corrId);
                            respData.setAccNbr(esbRespData.getAccNbr());
                            esbRespDataMap.put(corrId,respData);
                        } else {
                            esbRespDataMap.put(corrId,esbRespData);
                        }
                    }
                }
            }
//            System.out.println(jsonObject.toString());
        }
        for (Map.Entry<String,EsbRespData> entry:esbRespDataMap.entrySet()) {
            //System.out.println(entry.getKey()+"===="+entry.getValue().toString());
            esbRespDataList.add(entry.getValue());
        }

        String fileName = "/Users/binginx/Desktop/esb-20200803/10.1.52.53-9.50-50-10.10-1.xls";
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        EasyExcel.write(fileName, EsbRespData.class).sheet("模板").doWrite(esbRespDataList);
    }
}
