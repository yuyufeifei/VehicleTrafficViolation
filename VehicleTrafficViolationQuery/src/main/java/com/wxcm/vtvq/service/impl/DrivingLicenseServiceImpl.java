package com.wxcm.vtvq.service.impl;

import com.wxcm.vtvq.dao.primary.TvqLicenseDao;
import com.wxcm.vtvq.entity.primary.TvqLicense;
import com.wxcm.vtvq.service.DrivingLicenseService;
import com.wxcm.vtvq.util.ConfigProperties;
import com.wxcm.vtvq.util.Constants;
import com.wxcm.vtvq.util.SendPostRequest;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author GZH
 * @date 2020-07-16
 */
@Service
public class DrivingLicenseServiceImpl implements DrivingLicenseService {

    private final Logger logger = LogManager.getLogger(DrivingLicenseServiceImpl.class);

    @Autowired
    private TvqLicenseDao tvqLicenseDao;

    @Autowired
    private ConfigProperties configProperties;

    @Override
    @Cacheable(value = "drivingLicense", key = "#sfzh+#dabh", unless = "#result == null")
    public TvqLicense getDrivingLicense(String sfzh, String dabh) {
        return tvqLicenseDao.findByIdnumberAndFilenumber(sfzh, dabh);
    }

    @Override
    @CacheEvict(value = "drivingLicense", key = "#sfzh+#dabh", beforeInvocation = true)
    public Object getDrivingLicenseByVtvi(String sfzh, String dabh) {
        Object object = null;
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(8);
        map.add("sfzh", sfzh);
        map.add("dabh", dabh);
        JSONObject jsonObject = SendPostRequest.sendPostRequest(configProperties.getPrefix() + Constants.DRIVING_LICENSE, map);
        logger.info("访问vtvi接口'" + Constants.DRIVING_LICENSE +"' 参数：(sfzh=" + sfzh + " dabh=" + dabh + ") 结果：" + jsonObject);
        if(jsonObject != null && Constants.SUCCESS_CODE.equals(jsonObject.getString(Constants.CODE))) {
            object = com.alibaba.fastjson.JSONObject.parse(jsonObject.getString(Constants.DATA));
        }
        return object;
    }

    @Override
    @CacheEvict(value = "drivingLicense", allEntries = true)
    public void deleteDrivingLicenseCache() {
        //do nothing
    }

}
