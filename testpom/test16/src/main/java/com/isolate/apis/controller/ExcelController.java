package com.isolate.apis.controller;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.isolate.apis.model.MediaClientDTO;
import com.isolate.domain.events.listener.BaseUploadExcelListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @description: some desc
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2021/8/25 9:21
 */
public class ExcelController {

//    @ApiOperation(value = "批量上传", notes = "批量上传")
//    @PostMapping("/batchUpload")
//    public Result BatchUpload(MultipartFile file,
//                              @RequestParam Integer firstClass) throws IOException {
//        //数据处理结果
//        //数据处理结果
//        Class excelDto = null;
//        switch (firstClass) {
//            case FirstClass.MEETINGS:
//                excelDto = MeetingsDto.class;
//                break;
//        }
//        BaseUploadExcelListener listener = new BaseUploadExcelListener();
//        //逐行读取Excel
//        EasyExcel.read(file.getInputStream(), excelDto, listener).sheet().doRead();
//        List<MediaClientDTO> dcList = listener.getListData();
//        if (CollUtil.isEmpty(dcList)) {
//            throw new ServiceException("解析excel数据为空");
//        }
//
//        //查询用户单位
//        SysUnit sysUnit = sysUnitCache.getById(user.getUnitId());
//        if (sysUnit == null) {
//            throw new ServiceException("未查询到当前登录用户对应单位");
//        }
//        dcList.stream().forEach(a -> {
//            a.setUploadUnit(sysUnit.getUnitName());
//        });
//        try {
//            this.dcArticleService.batchUploadHandler(dcList, firstClass, user.getId(), user.getUnitId(), user.getRegionCode(), user.getLevel());
//        } catch (IllegalArgumentException e) {
//            return Result.failed(e.getMessage());
//        }
//        return null;
//    }
}
