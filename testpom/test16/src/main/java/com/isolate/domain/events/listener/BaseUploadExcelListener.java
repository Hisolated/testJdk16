package com.isolate.domain.events.listener;


import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.isolate.apis.model.MediaClientDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: some desc
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2021/8/25 9:22
 */
@Slf4j
public class BaseUploadExcelListener extends AnalysisEventListener<MediaClientDTO> {


    private List<MediaClientDTO> listData = new ArrayList<>();

    public BaseUploadExcelListener() {
    }

    @Override
    public void invoke(MediaClientDTO dto, AnalysisContext analysisContext) {
        log.info("BaseUploadExcelListener,dto:" + dto.toString());
        String name;
        if (StrUtil.isBlank(dto.getCounty())){
            if(StrUtil.isBlank(dto.getCity())){
                name = dto.getProvince();
            }else {
                name = dto.getCity();
            }
        }else {
            name = dto.getCounty();
        }
        /*
         * 通过name去查找city表，获取区域编码33开头的编码，
         */
        dto.setAreaCode("获取的区域编码");

        listData.add(dto);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
    public List<MediaClientDTO> getListData() {
        return listData;
    }

}
