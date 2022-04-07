package com.wxcm.vtvi.service.impl;

import com.wxcm.vtvi.dao.TvqVehicleDao;
import com.wxcm.vtvi.dao.TvqViolationDao;
import com.wxcm.vtvi.entity.TvqVehicle;
import com.wxcm.vtvi.entity.TvqViolation;
import com.wxcm.vtvi.service.VehicleViolationService;
import com.wxcm.vtvi.util.Constants;
import com.wxcm.vtvi.vo.VehicleViolation;
import com.wxcm.vtvi.vo.Violation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void saveVehicleViolation(String hphm, String hpzl, String sbdm, JSONObject jsonObject) {
        TvqVehicle tvqVehicle = tvqVehicleDao.findByPlateAndPlatetypeAndIdentification(hphm, hpzl, sbdm);
        if (tvqVehicle == null) {
            tvqVehicle = new TvqVehicle();
            tvqVehicle.setPlate(hphm);
            tvqVehicle.setPlatetype(hpzl);
            tvqVehicle.setIdentification(sbdm);
            tvqVehicleDao.save(tvqVehicle);
        }
//        saveViolation(tvqVehicle, jsonObject);
        saveViolations(tvqVehicle, jsonObject);
    }

    private void saveViolations(TvqVehicle tvqVehicle, JSONObject jsonObject) {
        try {
            String status = jsonObject.getString(Constants.RESPONSE_STATUS);
            tvqVehicle.setStatus(status);
            tvqVehicle.setUpdatetime(new Date());
            if (Constants.RESPONSE_STATUS_SUCCESS.equals(status)) {
                tvqVehicle.setMsg("");
                JSONObject object = jsonObject.getJSONObject(Constants.RESPONSE_DATA);
                tvqVehicle.setVehiclestatus(object.getString(Constants.RESPONSE_ZT));
                tvqVehicle.setInspectiondate(object.getString(Constants.RESPONSE_YXQZ));
                Integer lastViolationNum = tvqVehicle.getViolationnum();
                int violationNum = object.getInt(Constants.RESPONSE_WFNUM);
                tvqVehicle.setViolationnum(violationNum);
                if(lastViolationNum != null && lastViolationNum > 0){
                    List<TvqViolation> tvqViolationList = tvqViolationDao.findByVehicleid(tvqVehicle.getId());
                    if(tvqViolationList != null && tvqViolationList.size() > 0) {
                        tvqViolationDao.deleteAll(tvqViolationList);
                    }
                }
                if(violationNum > 0){
                    JSONArray violationArr = object.getJSONArray(Constants.RESPONSE_WFXX);
                    List<TvqViolation> tvqViolationList = new ArrayList<>();
                    for(int i = 0; i < violationArr.size(); i++) {
                        TvqViolation tvqViolation = new TvqViolation();
                        tvqViolation.setTvqVehicleByVehicleid(tvqVehicle);
                        tvqViolation.setVehicleid(tvqVehicle.getId());
                        JSONObject violationObj = violationArr.getJSONObject(i);
                        tvqViolation.setDatetime(violationObj.getString(Constants.RESPONSE_WFSJ));
                        tvqViolation.setPlace(violationObj.getString(Constants.RESPONSE_WFDZ));
                        tvqViolation.setAct(violationObj.getString(Constants.RESPONSE_WFXW));
                        tvqViolation.setFine(violationObj.getString(Constants.RESPONSE_FKJE));
                        tvqViolation.setPoints(violationObj.getInt(Constants.RESPONSE_KFFS));
                        tvqViolationList.add(tvqViolation);
                    }
                    tvqViolationDao.saveAll(tvqViolationList);
                }
            } else {
                tvqVehicle.setMsg(jsonObject.getString(Constants.RESPONSE_MSG));
            }
            tvqVehicleDao.save(tvqVehicle);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void saveViolation(TvqVehicle tvqVehicle, JSONObject jsonObject) {
        try {
            String status = jsonObject.getString(Constants.RESPONSE_STATUS);
            tvqVehicle.setStatus(status);
            tvqVehicle.setUpdatetime(new Date());
            if (Constants.RESPONSE_STATUS_SUCCESS.equals(status)) {
                tvqVehicle.setMsg("");
                tvqVehicle.setVehiclestatus(jsonObject.getString(Constants.RESPONSE_ZT));
                tvqVehicle.setInspectiondate(jsonObject.getString(Constants.RESPONSE_YXQZ));
                Integer lastViolationNum = tvqVehicle.getViolationnum();
                int violationNum = jsonObject.getInt(Constants.RESPONSE_WFNUM);
                tvqVehicle.setViolationnum(violationNum);
                if(lastViolationNum != null && lastViolationNum > 0){
                    List<TvqViolation> tvqViolationList = tvqViolationDao.findByVehicleid(tvqVehicle.getId());
                    if(tvqViolationList != null && tvqViolationList.size() > 0) {
                        tvqViolationDao.deleteAll(tvqViolationList);
                    }
                }
                if(violationNum > 0){
                    JSONArray violationArr = jsonObject.getJSONArray(Constants.RESPONSE_WFXX);
                    List<TvqViolation> tvqViolationList = new ArrayList<TvqViolation>();
                    for(int i = 0; i < violationArr.size(); i++) {
                        TvqViolation tvqViolation = new TvqViolation();
                        tvqViolation.setTvqVehicleByVehicleid(tvqVehicle);
                        tvqViolation.setVehicleid(tvqVehicle.getId());
                        JSONObject violationObj = violationArr.getJSONObject(i);
                        tvqViolation.setDatetime(violationObj.getString(Constants.RESPONSE_WFSJ));
                        tvqViolation.setPlace(violationObj.getString(Constants.RESPONSE_WFDZ));
                        tvqViolation.setAct(violationObj.getString(Constants.RESPONSE_WFXW));
                        tvqViolation.setFine(violationObj.getString(Constants.RESPONSE_FKJE));
                        tvqViolation.setPoints(violationObj.getInt(Constants.RESPONSE_KFFS));
                        tvqViolationList.add(tvqViolation);
                    }
                    tvqViolationDao.saveAll(tvqViolationList);
                }
            } else {
                tvqVehicle.setMsg(jsonObject.getString(Constants.RESPONSE_MSG));
            }
            tvqVehicleDao.save(tvqVehicle);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    public VehicleViolation getVehicleViolation(String hphm, String hpzl, String sbdm) {
        VehicleViolation vehicleViolation = null;
        try {
            TvqVehicle tvqVehicle = tvqVehicleDao.findByPlateAndPlatetypeAndIdentification(hphm, hpzl, sbdm);
            if(tvqVehicle != null) {
                vehicleViolation = new VehicleViolation(tvqVehicle);
                Integer violationNum = tvqVehicle.getViolationnum();
                if(violationNum != null && violationNum > 0) {
                    List<TvqViolation> tvqViolationList = tvqViolationDao.findByVehicleid(tvqVehicle.getId());
                    List<Violation> violations = new ArrayList<>();
                    for (TvqViolation tvqViolation : tvqViolationList) {
                        violations.add(new Violation(tvqViolation));
                    }
                    vehicleViolation.setWFXX(violations);
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return vehicleViolation;
    }

}
