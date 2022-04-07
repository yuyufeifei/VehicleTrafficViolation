package com.wxcm.vtvi.schedule;

import com.wxcm.vtvi.util.*;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GZH
 * 注解 @Configuration      1.主要用于标记配置类，兼备Component的效果。
 * 注解 @EnableScheduling   2.开启定时任务
 */
@Configuration
@EnableScheduling
public class GetSession {

    private final Logger logger = LogManager.getLogger(GetSession.class);

    /**
     * 3.添加定时任务@Scheduled(cron = "0 0 0/4 * * ? ")或直接指定时间间隔，例如：4.5小时
     */
//    @Scheduled(fixedRate = 16200000)
    public void getSession(){
        //unixTimestamp 也可使用new Date().getTime()
        long unixTimestamp = System.currentTimeMillis()/1000;
        String md5Password = DigestUtils.md5DigestAsHex((Constants.PASSWORD + unixTimestamp).getBytes());
        Map<String, Object> map = new HashMap<>(8);
        map.put("ordertime", unixTimestamp);
        map.put("username", Constants.USER_NAME);
        map.put("password", md5Password);
        Global.password = md5Password;
        logger.info("获取session请求参数：" + map);
        try {
            JSONObject jsonObject = SendPostRequest.sendPostRequest(Constants.URL_IP_PORT + Constants.SESSION_URL, map);
            logger.info("获取session返回结果：" + jsonObject);
            String status = jsonObject.getString("status");
            if(Constants.RESPONSE_STATUS_SUCCESS.equals(status)){
                Global.session = jsonObject.getString("session");
                logger.info("得到session:" + Global.session);
//                Global.domainUrl = jsonObject.getString("domainUrl");
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * 3.添加定时任务@Scheduled(cron = "0 0 0/2 * * ? ")或直接指定时间间隔，例如：2小时
     * 设置定时任务间隔 1小时50分钟
     */
    @Scheduled(fixedRate = 6600000)
    public void getToken(){
        try {
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(8);
            map.add("appNo", Constants.APP_NO);
            map.add("jsonData", GetJsonData.getJsonData("", new JSONObject()));
            logger.info("获取token请求参数：" + map);
            JSONObject jsonObject = SendPostRequest.sendPostRequest(Constants.TOKEN_URL, map);
            logger.info("获取token返回结果：" + jsonObject);
            String status = jsonObject.getString("status");
            if(Constants.RESPONSE_STATUS_SUCCESS.equals(status)){
                Global.token = jsonObject.getJSONObject("data").getString("token");
                logger.info("得到token:" + Global.token);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

}
