package com.wxcm.vtvi.service.impl;

import com.wxcm.vtvi.dao.TvqLicenseDao;
import com.wxcm.vtvi.entity.TvqLicense;
import com.wxcm.vtvi.service.DrivingLicenseService;
import com.wxcm.vtvi.util.Constants;
import com.wxcm.vtvi.vo.License;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author GZH
 */
@Service
public class DrivingLicenseServiceImpl implements DrivingLicenseService {

    private final Logger logger = LogManager.getLogger(DrivingLicenseServiceImpl.class);

    @Autowired
    private TvqLicenseDao tvqLicenseDao;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void saveDrivingLicense(String sfzh, String dabh, JSONObject jsonObject) {
        TvqLicense tvqLicense = tvqLicenseDao.findByIdnumberAndFilenumber(sfzh, dabh);
        if(tvqLicense == null) {
            tvqLicense = new TvqLicense();
            tvqLicense.setIdnumber(sfzh);
            tvqLicense.setFilenumber(dabh);
        }
        saveDrivingLicense(tvqLicense, jsonObject);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void saveDrivingLicense(TvqLicense tvqLicense, JSONObject jsonObject) {
        try {
            String status = jsonObject.getString(Constants.RESPONSE_STATUS);
            tvqLicense.setStatus(status);
            tvqLicense.setUpdatetime(new Date());
            if (Constants.RESPONSE_STATUS_SUCCESS.equals(status)) {
                tvqLicense.setMsg("");
                JSONObject object = jsonObject.getJSONObject(Constants.RESPONSE_DATA);
                tvqLicense.setExpirydate(object.getString(Constants.RESPONSE_YXQZ));
                tvqLicense.setVerifydate(object.getString(Constants.RESPONSE_SYRQ));
                tvqLicense.setPoints(object.getInt(Constants.RESPONSE_LJJF));
                tvqLicense.setLicensestatus(object.getString(Constants.RESPONSE_ZT));
            } else {
                tvqLicense.setMsg(jsonObject.getString(Constants.RESPONSE_MSG));
            }
            tvqLicenseDao.save(tvqLicense);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    @Override
    public License getDrivingLicense(String sfzh, String dabh) {
        License license = null;
        try {
            TvqLicense tvqLicense = tvqLicenseDao.findByIdnumberAndFilenumber(sfzh, dabh);
            if(tvqLicense != null) {
                license = new License(tvqLicense);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return license;
    }
}
