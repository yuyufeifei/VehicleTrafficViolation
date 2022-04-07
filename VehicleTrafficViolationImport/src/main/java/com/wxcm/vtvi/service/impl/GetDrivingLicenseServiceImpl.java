package com.wxcm.vtvi.service.impl;

import com.wxcm.vtvi.entity.TvqLicense;
import com.wxcm.vtvi.service.DrivingLicenseService;
import com.wxcm.vtvi.service.GetDrivingLicenseService;
import com.wxcm.vtvi.util.Constants;
import com.wxcm.vtvi.util.GetJsonData;
import com.wxcm.vtvi.util.Global;
import com.wxcm.vtvi.util.SendPostRequest;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author GZH
 */
@Service
public class GetDrivingLicenseServiceImpl implements GetDrivingLicenseService {

    private final Logger logger = LogManager.getLogger(GetDrivingLicenseServiceImpl.class);

    @Autowired
    private DrivingLicenseService drivingLicenseService;

    @Override
    @Async("taskExecutor")
    public CompletableFuture<JSONObject> getDrivingLicense(String sfzh, String dabh, int num) {
        JSONObject jsonObject = getDrivingLicenseDataByHttp(sfzh, dabh);
        CompletableFuture<JSONObject> jsonObjectCF = CompletableFuture.completedFuture(jsonObject);
        if(jsonObject != null) {
            String status = jsonObject.getString(Constants.RESPONSE_STATUS);
            drivingLicenseService.saveDrivingLicense(sfzh, dabh, jsonObject);
            logger.info("查询驾驶证并保存成功！(sfzh=" + sfzh + " dabh=" + dabh + ") num为：" + num);
            if (Constants.RESPONSE_STATUS_WAIT_H.equals(status)) {
                // 数据回传中。 Constants.SLEEP_TIME毫秒后再次访问接口
                try {
                    if(num > 0) {
                        Thread.sleep(Constants.SLEEP_TIME);
                        logger.info("再次查询驾驶证！(sfzh=" + sfzh + " dabh=" + dabh + ") num为：" + num);
                        jsonObjectCF = getDrivingLicense(sfzh, dabh, num - 1);
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
    public void reacquireDrivingLicense(TvqLicense tvqLicense) {
        logger.info("reacquireDrivingLicense开始...");
        JSONObject jsonObject = getDrivingLicenseData(tvqLicense.getIdnumber(), tvqLicense.getFilenumber());
        if(jsonObject != null) {
            String status = jsonObject.getString(Constants.RESPONSE_STATUS);
            if (!Constants.RESPONSE_STATUS_FAILED.equals(status)) {
                drivingLicenseService.saveDrivingLicense(tvqLicense, jsonObject);
                logger.info("reacquireDrivingLicense保存完成！");
            }
        }
    }

    /**
     * https接口（带域名）访问
     */
    private JSONObject getDrivingLicenseData(String sfzh, String dabh) {
        Map<String, Object> map = new HashMap<>(8);
        map.put("session", Global.session);
        map.put("sfzh", sfzh);
        map.put("dabh", dabh);
        String auth = DigestUtils.md5DigestAsHex((sfzh + dabh + Global.password).getBytes());
        map.put("auth", auth);
        logger.info("访问驾驶证查询接口参数：" + map);
        JSONObject jsonObject = null;
        try {
            jsonObject = SendPostRequest.sendPostRequest(Constants.URL_IP_PORT + Constants.LICENSE_URL, map);
            logger.info("访问驾驶证查询接口返回结果：" + jsonObject);
        } catch (Exception e) {
            logger.error(e);
        }
        return jsonObject;
    }

    /**
     * http接口（IP地址）访问（新）
     */
    private JSONObject getDrivingLicenseDataByHttp(String sfzh, String dabh) {
        JSONObject jsonObject = null;
        try {
            JSONObject object = new JSONObject();
            object.element("sfzh", sfzh);
            object.element("dabh", dabh);
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(8);
            map.add("appNo", Constants.APP_NO);
            map.add("jsonData", GetJsonData.getJsonData(Global.token, object));
            logger.info("访问驾驶证查询接口（http）(sfzh=" + sfzh + " dabh=" + dabh + ") 参数：" + map);
            jsonObject = SendPostRequest.sendPostRequest(Constants.HTTP_LICENSE_URL, map);
            logger.info("访问驾驶证查询接口（http）(sfzh=" + sfzh + " dabh=" + dabh + ") 返回结果：" + jsonObject);
        } catch (Exception e) {
            logger.error(e);
        }
        return jsonObject;
    }

}
