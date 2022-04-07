package com.wxcm.vtvq.service.impl;

import com.wxcm.vtvq.dao.secondary.ApaLicenseDao;
import com.wxcm.vtvq.dao.secondary.ApaUserDao;
import com.wxcm.vtvq.dao.secondary.ApaVehicleDao;
import com.wxcm.vtvq.entity.secondary.ApaLicense;
import com.wxcm.vtvq.entity.secondary.ApaUser;
import com.wxcm.vtvq.entity.secondary.ApaVehicle;
import com.wxcm.vtvq.service.UserService;
import com.wxcm.vtvq.vo.secondary.License;
import com.wxcm.vtvq.vo.secondary.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GZH
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Autowired
    private ApaUserDao apaUserDao;
    @Autowired
    private ApaVehicleDao apaVehicleDao;
    @Autowired
    private ApaLicenseDao apaLicenseDao;

    @Override
    @Cacheable(value = "userVehicle", key = "#iptv")
    public List<Vehicle> getVehiclesByIptv(String iptv) {
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            List<ApaVehicle> apaVehicles = apaVehicleDao.findByApaUser_Iptv(iptv);
            for (ApaVehicle apaVehicle : apaVehicles) {
                vehicles.add(new Vehicle(apaVehicle));
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return vehicles;
    }

    @Override
    @Transactional("transactionManagerSecondary")
    @CacheEvict(value = "userVehicle", key = "#iptv", condition = "#result eq 1")
    public Integer addVehicle(String iptv, String phone, String plate, String newenegry, String platetype, String identification) {
        int resultCode = -1;
        try {
            ApaUser apaUser = apaUserDao.findByIptv(iptv);
            ApaVehicle apaVehicleUnique = apaVehicleDao.findByPlateAndPlatetypeAndIdentification(plate, platetype, identification);
            boolean addFlag = false;
            if (apaVehicleUnique != null) {
                //plate, platetype, identification已被其他IPTV账号绑定
                resultCode = 30005;
            } else {
                if (apaUser != null) {
                    ApaVehicle apaVehicle = apaVehicleDao.findByUseridAndPlateAndPlatetypeAndIdentification(apaUser.getId(),
                            plate, platetype, identification);
                    if (apaVehicle != null) {
                        //已有此条绑定关系，不进行绑定
                        resultCode = 30001;
                    } else {
                        //添加此条绑定关系
                        addFlag = true;
                    }
                } else {
                    //添加此条绑定关系
                    apaUser = new ApaUser();
                    apaUser.setIptv(iptv);
                    apaUserDao.save(apaUser);
                    addFlag = true;
                }
            }
            if(addFlag) {
                ApaVehicle apaVehicle = new ApaVehicle(apaUser.getId(), phone, plate, newenegry, platetype, identification, apaUser);
                apaVehicleDao.save(apaVehicle);
                resultCode = 1;
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return resultCode;
    }

    @Override
    @Transactional("transactionManagerSecondary")
    @CacheEvict(value = "userVehicle", key = "#iptv", condition = "#result eq 1")
    public Integer deleteVehicle(String iptv, String plate, String platetype, String identification) {
        int resultCode = -1;
        try {
            ApaVehicle apaVehicle = apaVehicleDao.findByApaUser_IptvAndPlateAndPlatetypeAndIdentification(iptv, plate, platetype, identification);
            if(apaVehicle != null) {
                apaVehicleDao.delete(apaVehicle);
                resultCode = 1;
            } else {
                resultCode = 30002;
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return resultCode;
    }

    @Override
    @Cacheable(value = "userLicense", key = "#iptv")
    public List<License> getLicensesByIptv(String iptv) {
        List<License> licenses = new ArrayList<>();
        try {
            List<ApaLicense> apaLicenses = apaLicenseDao.findByApaUser_Iptv(iptv);
            for (ApaLicense apaLicense : apaLicenses) {
                licenses.add(new License(apaLicense));
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return licenses;
    }

    @Override
    @Transactional("transactionManagerSecondary")
    @CacheEvict(value = "userLicense", key = "#iptv", condition = "#result eq 1")
    public Integer addLicense(String iptv, String phone, String idnumber, String filenumber) {
        int resultCode = -1;
        try {
            ApaUser apaUser = apaUserDao.findByIptv(iptv);
            ApaLicense apaLicenseUnique = apaLicenseDao.findByIdnumberAndFilenumber(idnumber, filenumber);
            boolean addFlag = false;
            if (apaLicenseUnique != null) {
                //idnumber, filenumber已被其他IPTV账号绑定
                resultCode = 30006;
            } else {
                if (apaUser != null) {
                    ApaLicense apaLicense = apaLicenseDao.findByUseridAndIdnumberAndFilenumber(apaUser.getId(), idnumber, filenumber);
                    if (apaLicense != null) {
                        //已有此条绑定关系，不进行绑定
                        resultCode = 30003;
                    } else {
                        //添加此条绑定关系
                        addFlag = true;
                    }
                } else {
                    //添加此条绑定关系
                    apaUser = new ApaUser();
                    apaUser.setIptv(iptv);
                    apaUserDao.save(apaUser);
                    addFlag = true;
                }
            }
            if(addFlag) {
                ApaLicense apaLicense = new ApaLicense(apaUser.getId(), phone, idnumber, filenumber, apaUser);
                apaLicenseDao.save(apaLicense);
                resultCode = 1;
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return resultCode;
    }

    @Override
    @Transactional("transactionManagerSecondary")
    @CacheEvict(value = "userLicense", key = "#iptv", condition = "#result eq 1")
    public Integer deleteLicense(String iptv, String idnumber, String filenumber) {
        int resultCode = -1;
        try {
            ApaLicense apaLicense = apaLicenseDao.findByApaUser_IptvAndIdnumberAndFilenumber(iptv, idnumber, filenumber);
            if(apaLicense != null) {
                apaLicenseDao.delete(apaLicense);
                resultCode = 1;
            } else {
                resultCode = 30004;
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return resultCode;
    }

    @Override
    @Caching(evict = {@CacheEvict(value = "userVehicle", allEntries = true), @CacheEvict(value = "userLicense", allEntries = true)})
    public void deleteUserCache() {
        //do nothing
    }
}
