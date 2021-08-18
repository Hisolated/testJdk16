package com.isolate.infrastructure.general.toolkit;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: some desc
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2021/8/18 14:09
 */
public class GaoDeUtil {

    /**
     * 高德key
     */
    private static final String GAO_DE_KEY = "08c71e112dee3a4784f18f39b954855f";

    /**
     * 将IP信息转换为地理位置信息的url  ref: https://lbs.amap.com/api/webservice/guide/api/ipconfig/
     */
    private static final String IP_URL = "https://restapi.amap.com/v5/ip";

    /**
     * 逆地理编码API服务地址的url  ref: https://lbs.amap.com/api/webservice/guide/api/georegeo/
     */
    private static final String REGEO_URL = "https://restapi.amap.com/v3/geocode/regeo";

    @Autowired
    private static RestTemplate restTemplate;

    /**
     * 获取精确的地址位置
     * @param ip 需要查询的ip
     * @param type 值为 4 或 6，4 表示 IPv4，6 表示 IPv6
     * @return 精确的地理地址
     */
    public static String getLocation(String ip, Integer type) throws JsonProcessingException {
        /*
         *  1. 先获取该ip所对应的经纬度
         */
        Map<String,Object> param = new HashMap<>(4);
        param.put("key",GAO_DE_KEY);
        param.put("type",type);
        param.put("ip",ip);
        String jsonStr = RestTemplateUtil.getForObject(IP_URL, param);

        ObjectMapper mapper = new ObjectMapper();

        String location = mapper.readTree(jsonStr).path("location").asText();


        Map<String,Object> param2 = new HashMap<>();
        param2.put("key",GAO_DE_KEY);
        param2.put("location",location);
        param2.put("radius",200);
        param2.put("extensions","all");
        param2.put("batch",false);

        String jsonStr2 = RestTemplateUtil.getForObject(REGEO_URL, param2);

        ObjectMapper mapper2 = new ObjectMapper();
        JsonNode regeocodes = mapper2.readTree(jsonStr2).path("regeocode");

        String formatted_address = regeocodes.path("formatted_address").asText();
        return formatted_address;

    }

}
