package com.isolate;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: some desc
 * @author: isolate
 * @email: 15071340963@163.com
 * @date: 2021/8/17 9:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestIpLocate {

    /** 如何通过ip定位到准确的地理位置及地址
     *      1. ip获取，这个很简单
     *      2. 先通过ip查经纬度，根据经纬度查地址
     *      3. 通过经纬度查询具体物理位置
     *      获取经纬度：https://webapi.amap.com/maps/ipLocation?key=7a608be3db2f08377a8dbc098421ab64&callback=jsonp_83726_&csid=49C04F2B-658F-4176-9BE1-C52B32E8FB4F （header:x-server-id:72446e765a0ee479614554419edfe3ec363ecad9edc12e0c531ccfb113325a8291e04aa4e0c91a6343850c12aec5657f）
     *      获取具体地理位置：https://restapi.amap.com/v3/geocode/regeo?key=7a608be3db2f08377a8dbc098421ab64&s=rsv3&location=120.17477,30.27084&extensions=base&callback=jsonp_564745_&platform=JS&logversion=2.0&sdkversion=1.3&appname=https%3A%2F%2Fwww.opengps.cn%2FData%2FIP%2FLocHighAcc.aspx&csid=8E84DDBB-DA00-4131-A296-B0A1E4B05319
     *
     */
    public static void main(String[] args) {

    }
}
