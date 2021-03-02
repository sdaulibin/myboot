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
public class CampDtlExcelListener extends AnalysisEventListener<CampDtlReadModel> {
    private List<CampDtlReadModel> campDtlReadModels = new ArrayList<CampDtlReadModel>();
    Map<String,String> map = new HashMap<String,String>();

    @Override
    public void invoke(CampDtlReadModel campDtlReadModel, AnalysisContext analysisContext) {

        analysisContext.getCurrentRowAnalysisResult();
        if(null != campDtlReadModel && !StrUtil.isEmpty(campDtlReadModel.getCustNbr())) {
            if(campDtlReadModel.getCustNbr().equals(map.get(campDtlReadModel.getCustNbr()))){
                campDtlReadModel.setFlag("1");
            }
            map.put(campDtlReadModel.getCustNbr(), campDtlReadModel.getCustNbr());
            log.info(campDtlReadModel.toString());
            campDtlReadModels.add(campDtlReadModel);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    public List<CampDtlReadModel> getCampDtlReadModels() {
        return campDtlReadModels;
    }

    public void setCampDtlReadModels(List<CampDtlReadModel> campDtlReadModels) {
        this.campDtlReadModels = campDtlReadModels;
    }
}
