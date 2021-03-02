package com.binginx.myboot.excel;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class SmsExcelListener extends AnalysisEventListener<SmsInfoReadModel> {
    private List<SmsInfoReadModel> smsInfoReadModels = new ArrayList<SmsInfoReadModel>();
    Map<String,String> map = new HashMap<String,String>();

    @Override
    public void invoke(SmsInfoReadModel smsInfoReadModel, AnalysisContext analysisContext) {
        if(null != smsInfoReadModel && !StrUtil.isEmpty(smsInfoReadModel.getCustNbr())) {
            if(smsInfoReadModel.getCustNbr().equals(map.get(smsInfoReadModel.getCustNbr()))){
                smsInfoReadModel.setFlag("1");
            }
            map.put(smsInfoReadModel.getCustNbr(), smsInfoReadModel.getCustNbr());
//			logger.info(smsInfoReadModel.toString());
            smsInfoReadModels.add(smsInfoReadModel);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    public List<SmsInfoReadModel> getSmsInfoReadModels() {
        return smsInfoReadModels;
    }

    public void setSmsInfoReadModels(List<SmsInfoReadModel> smsInfoReadModels) {
        this.smsInfoReadModels = smsInfoReadModels;
    }
}
