package com.wxcm.vtvq.service.impl;

import com.wxcm.vtvq.dao.primary.TvqVehicleDao;
import com.wxcm.vtvq.dao.primary.TvqViolationDao;
import com.wxcm.vtvq.entity.primary.TvqVehicle;
import com.wxcm.vtvq.entity.primary.TvqViolation;
import com.wxcm.vtvq.service.VehicleViolationService;
import com.wxcm.vtvq.util.ConfigProperties;
import com.wxcm.vtvq.util.Constants;
import com.wxcm.vtvq.util.SendPostRequest;
import com.wxcm.vtvq.vo.primary.Vehicle;
import com.wxcm.vtvq.vo.primary.VehicleViolation;
import com.wxcm.vtvq.vo.primary.Violation;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GZH
 */

@Service
public class VehicleViolationServiceImpl implements VehicleViolationService {

    private final Logger logger = LogManager.getLogger(VehicleViolationServiceImpl.class);

    @Autowired
    private TvqVehicleDao tvqVehicleDao;
    @Autowired
    private TvqViolationDao tvqViolationDao;

    @Autowired
    private ConfigProperties configProperties;

    @Override
    @Cacheable(value = "vehicle", key = "#hphm+#hpzl+#sbdm", unless = "#result == null")
    public Vehicle getVehicleViolation(String hphm, String hpzl, String sbdm) {
        TvqVehicle tvqVehicle = tvqVehicleDao.findByPlateAndPlatetypeAndIdentification(hphm, hpzl, sbdm);
        Vehicle vehicle = null;
        if (tvqVehicle != null) {
            vehicle = new Vehicle(tvqVehicle);
        }
        return vehicle;
    }

    @Override
    @Cacheable(value = "vehicleViolation", key = "#hphm+#hpzl+#sbdm")
    public VehicleViolation getVehicleViolation(String hphm, String hpzl, String sbdm, Vehicle vehicle) throws Exception {
        VehicleViolation vehicleViolation = new VehicleViolation(vehicle);
        Integer violationNum = vehicle.getViolationnum();
        if(violationNum != null && violationNum > 0) {
            List<TvqViolation> tvqViolationList = tvqViolationDao.findByVehicleid(vehicle.getId());
            List<Violation> violations = new ArrayList<>();
            for (TvqViolation tvqViolation : tvqViolationList) {
                violations.add(new Violation(tvqViolation));
            }
            vehicleViolation.setWFXX(violations);
        }
        logger.info("从数据库中查找结果 (hphm=" + hphm + " hpzl=" + hpzl + " sbdm=" + sbdm + ")");
        return vehicleViolation;
    }

    @Override
    @Caching(evict = {@CacheEvict(value = "vehicle", key = "#hphm+#hpzl+#sbdm", beforeInvocation = true),// TODO beforeInvocation = true值得商榷
            @CacheEvict(value = "vehicleViolation", key = "#hphm+#hpzl+#sbdm", beforeInvocation = true)})
    public Object getVehicleViolationByVtvi(String hphm, String hpzl, String sbdm) {
        Object object = null;
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(8);
        map.add("hphm", hphm);
        map.add("hpzl", hpzl);
        map.add("sbdm", sbdm);
        JSONObject jsonObject = SendPostRequest.sendPostRequest(configProperties.getPrefix() + Constants.VEHICLE_VIOLATION, map);
        logger.info("访问vtvi接口'" + Constants.VEHICLE_VIOLATION +"' 参数：(hphm=" + hphm + " hpzl=" + hpzl + " sbdm=" + sbdm + ") 结果：" + jsonObject);
        if(jsonObject != null && Constants.SUCCESS_CODE.equals(jsonObject.getString(Constants.CODE))) {
            object = com.alibaba.fastjson.JSONObject.parse(jsonObject.getString(Constants.DATA));
        }
        return object;
    }

    @Override
    @Caching(evict = {@CacheEvict(value = "vehicle", allEntries = true), @CacheEvict(value = "vehicleViolation", allEntries = true)})
    public void deleteVehicleViolationCache() {
        //do nothing
    }
}
