package com.isolate.apis.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @description: some desc
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2021/8/25 9:22
 */
@Data
public class MediaClientDTO implements Serializable {

    @ExcelProperty("媒体名称*")
    private String name;

    @ExcelProperty("APPID")
    private String appid;

    /**
     * 0报纸 1电视 2广播 3客户端 4微信公众 5微博 6抖音
     */
    @ExcelProperty("媒体类型*")
    private Integer type;

    @ExcelProperty("省*")
    private String province;

    @ExcelProperty("市")
    private String city;

    @ExcelProperty("县")
    private String county;

    @ExcelProperty("媒体单位")
    private String mediaUnit;

    @ExcelProperty("租户ID*")
    private String tenantId;

    @ExcelProperty("H5")
    private String h5;

    private String areaCode;
}
