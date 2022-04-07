package com.wxcm.vtvi.service.impl;

import com.wxcm.vtvi.entity.TvqVehicle;
import com.wxcm.vtvi.service.GetVehicleViolationService;
import com.wxcm.vtvi.service.VehicleViolationService;
import com.wxcm.vtvi.util.*;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author GZH
 */
@Service
public class GetVehicleViolationServiceImpl implements GetVehicleViolationService {

    private final Logger logger = LogManager.getLogger(GetVehicleViolationServiceImpl.class);

    @Autowired
    private VehicleViolationService vehicleViolationService;

    @Override
    @Async("taskExecutor")
    public CompletableFuture<JSONObject> getVehicleViolation(String hphm, String hpzl, String sbdm, int num) {
        JSONObject jsonObject = getVehicleViolationDataByHttp(hphm, hpzl, sbdm);
        CompletableFuture<JSONObject> jsonObjectCF = CompletableFuture.completedFuture(jsonObject);
        if(jsonObject != null) {
            String status = jsonObject.getString(Constants.RESPONSE_STATUS);
            vehicleViolationService.saveVehicleViolation(hphm, hpzl, sbdm, jsonObject);
            logger.info("查询机动车违法并保存成功！(hphm=" + hphm + " hpzl=" + hpzl + " sbdm=" + sbdm + ") num为：" + num);
            if (Constants.RESPONSE_STATUS_WAIT_H.equals(status)) {
                // 数据回传中。 Constants.SLEEP_TIME毫秒后再次访问接口
                try {
                    if(num > 0) {
                        Thread.sleep(Constants.SLEEP_TIME);
                        logger.info("再次查询机动车违法！(hphm=" + hphm + " hpzl=" + hpzl + " sbdm=" + sbdm + ") num为：" + num);
                        jsonObjectCF = getVehicleViolation(hphm, hpzl, sbdm, num - 1);
                    }
                } catch (InterruptedException e) {
                    logger.error(e);
                }
            }
        }
        return jsonObjectCF;
    }

    @Override
    @Async("taskExecutor")
    public void reacquireVehicleViolation(TvqVehicle tvqVehicle) {
        logger.info("reacquireVehicleViolation vehicleId:" + tvqVehicle.getId() + " 开始...");
        JSONObject jsonObject = getVehicleViolationData(tvqVehicle.getPlate(), tvqVehicle.getPlatetype(), tvqVehicle.getIdentification());
        if(jsonObject != null) {
            vehicleViolationService.saveViolation(tvqVehicle, jsonObject);
            logger.info("reacquireVehicleViolation保存完成！");
        }
    }

    /**
     * https接口（带域名）访问
     */
    private JSONObject getVehicleViolationData(String hphm, String hpzl, String sbdm) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("session", Global.session);
        map.put("hphm", hphm);
        map.put("hpzl", hpzl);
        map.put("sbdm", sbdm);
        String auth = DigestUtils.md5DigestAsHex((hphm + hpzl + sbdm + Global.password).getBytes());
        map.put("auth", auth);
        logger.info("访问机动车违章查询接口参数：" + map);
        JSONObject jsonObject = null;
        try {
            jsonObject = SendPostRequest.sendPostRequest(Constants.URL_IP_PORT + Constants.VEHICLE_URL, map);
            logger.info("访问机动车违章查询接口返回结果：" + jsonObject);
        } catch (Exception e) {
            logger.error(e);
        }
        return jsonObject;
    }

    /**
     * http接口（IP地址）访问
     */
    private JSONObject getVehicleViolationDataByIP(String hphm, String hpzl, String sbdm) {
        JSONObject jsonObject = null;
        try {
            Map<String, Object> map = new HashMap<>(8);
            map.put("xh", Constants.COMPANY_ID);
            String datetime = DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
            map.put("sj", datetime);
            map.put("hphm", hphm);
            map.put("hpzl", hpzl);
            map.put("sbdm", sbdm);
            String encryptStr = Constants.PREFIX_KEY + hphm + "-" + hpzl + "-" + datetime + Constants.SUFFIX_KEY;
            String auth = DigestUtils.md5DigestAsHex(DigestUtils.md5DigestAsHex(encryptStr.getBytes()).getBytes());
            map.put("ah", auth);
            logger.info("访问机动车违章查询接口（IP）参数：" + map);
            jsonObject = SendPostRequest.sendPostRequest(Constants.IP_VEHICLE_URL, map);
            logger.info("访问机动车违章查询接口（IP）返回结果：" + jsonObject);
        } catch (Exception e) {
            logger.error(e);
        }
        return jsonObject;
    }

    /**
     * http接口（IP地址）访问（新）
     */
    private JSONObject getVehicleViolationDataByHttp(String hphm, String hpzl, String sbdm) {
        JSONObject jsonObject = null;
        try {
            JSONObject object = new JSONObject();
            object.element("hphm", hphm);
            object.element("hpzl", hpzl);
            object.element("sbdm", sbdm);
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(8);
            map.add("appNo", Constants.APP_NO);
            map.add("jsonData", GetJsonData.getJsonData(Global.token, object));
            logger.info("访问机动车违章查询接口（http）(hphm=" + hphm + " hpzl=" + hpzl + " sbdm=" + sbdm + ") 参数：" + map);
            jsonObject = SendPostRequest.sendPostRequest(Constants.HTTP_VEHICLE_URL, map);
            logger.info("访问机动车违章查询接口（http）(hphm=" + hphm + " hpzl=" + hpzl + " sbdm=" + sbdm + ") 返回结果：" + jsonObject);
        } catch (Exception e) {
            logger.error(e);
        }
        return jsonObject;
    }

}
