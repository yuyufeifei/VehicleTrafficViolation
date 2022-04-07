package com.wxcm.vtvq.controller;

import com.wxcm.vtvq.service.UserService;
import com.wxcm.vtvq.util.Constants;
import com.wxcm.vtvq.vo.secondary.License;
import com.wxcm.vtvq.vo.secondary.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author GZH
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getVehicle", method = RequestMethod.GET)
    public Object getVehicle(@RequestParam String iptv) {
        Map<String, Object> result = new HashMap<>(8);
        int code = -1;
        if(!"".equals(iptv)) {
            try {
                List<Vehicle> data = userService.getVehiclesByIptv(iptv);
                if(!data.isEmpty()) {
                    result.put("data", data);
                    code = 1;
                } else {
                    code = 2;
                }
            } catch (Exception e) {
                logger.error(e);
            }
        } else {
            code = 0;
        }
        result.put("code", code);
        result.put("msg", Constants.CODE_TO_MSG.get(code));
        logger.info("/user/getVehicle param: iptv=" + iptv + " ;result: " + result);
        return result;
    }

    @RequestMapping(value = "/addVehicle", method = RequestMethod.POST)
    public Object addVehicle(@RequestParam String iptv, @RequestParam String phone, @RequestParam String plate,
                             @RequestParam String newenegry, @RequestParam String platetype, @RequestParam String identification) {
        Map<String, Object> result = new HashMap<>(8);
        int code = -1;
        if(!"".equals(iptv) && !"".equals(phone) && !"".equals(plate) && !"".equals(newenegry) && !"".equals(platetype)
                && identification.length() == 4){
            try {
                code = userService.addVehicle(iptv, phone, plate, newenegry, platetype, identification);
            } catch (Exception e) {
                logger.error(e);
            }
        } else {
            code = 0;
        }
        result.put("code", code);
        result.put("msg", Constants.CODE_TO_MSG.get(code));
        logger.info("/user/addVehicle param: iptv=" + iptv + " phone=" + phone + " plate=" + plate + " newenegry="
                + newenegry + " platetype=" + platetype + " identification=" + identification + " ;result: " + result);
        return result;
    }

    @RequestMapping(value = "/deleteVehicle", method = RequestMethod.POST)
    public Object deleteVehicle(@RequestParam String iptv, @RequestParam String plate, @RequestParam String platetype,
                                @RequestParam String identification) {
        Map<String, Object> result = new HashMap<>(8);
        int code = -1;
        if(!"".equals(iptv) && !"".equals(plate) && !"".equals(platetype) && !"".equals(identification)){
            try {
                code = userService.deleteVehicle(iptv, plate, platetype, identification);
            } catch (Exception e) {
                logger.error(e);
            }
        } else {
            code = 0;
        }
        result.put("code", code);
        result.put("msg", Constants.CODE_TO_MSG.get(code));
        logger.info("/user/deleteVehicle param: iptv=" + iptv + " plate=" + plate + " platetype=" + platetype
                + " identification=" + identification + " ;result: " + result);
        return result;
    }

    @RequestMapping(value = "/getLicense", method = RequestMethod.GET)
    public Object getLicense(@RequestParam String iptv) {
        Map<String, Object> result = new HashMap<>(8);
        int code = -1;
        if(!"".equals(iptv)) {
            try {
                List<License> data = userService.getLicensesByIptv(iptv);
                if(!data.isEmpty()) {
                    result.put("data", data);
                    code = 1;
                } else {
                    code = 2;
                }
            } catch (Exception e) {
                logger.error(e);
            }
        } else {
            code = 0;
        }
        result.put("code", code);
        result.put("msg", Constants.CODE_TO_MSG.get(code));
        logger.info("/user/getLicense param: iptv=" + iptv + " ;result: " + result);
        return result;
    }

    @RequestMapping(value = "/addLicense", method = RequestMethod.POST)
    public Object addLicense(@RequestParam String iptv, @RequestParam String phone, @RequestParam String idnumber,
                             @RequestParam String filenumber) {
        Map<String, Object> result = new HashMap<>(8);
        int code = -1;
        if(!"".equals(iptv) && !"".equals(phone) && !"".equals(idnumber) && !"".equals(filenumber)
                && idnumber.length() == 4 && filenumber.length() == 12){
            try {
                code = userService.addLicense(iptv, phone, idnumber, filenumber);
            } catch (Exception e) {
                logger.error(e);
            }
        } else {
            code = 0;
        }
        result.put("code", code);
        result.put("msg", Constants.CODE_TO_MSG.get(code));
        logger.info("/user/addLicense param: iptv=" + iptv + " phone=" + phone + " idnumber=" + idnumber
                + " filenumber=" + filenumber + " ;result: " + result);
        return result;
    }

    @RequestMapping(value = "/deleteLicense", method = RequestMethod.POST)
    public Object deleteLicense(@RequestParam String iptv, String idnumber, String filenumber) {
        Map<String, Object> result = new HashMap<>(8);
        int code = -1;
        if(!"".equals(iptv) && !"".equals(idnumber) && !"".equals(filenumber)){
            try {
                code = userService.deleteLicense(iptv, idnumber, filenumber);
            } catch (Exception e) {
                logger.error(e);
            }
        } else {
            code = 0;
        }
        result.put("code", code);
        result.put("msg", Constants.CODE_TO_MSG.get(code));
        logger.info("/user/deleteLicense param: iptv=" + iptv + " idnumber=" + idnumber + " filenumber=" + filenumber
                + " ;result: " + result);
        return result;
    }

    @GetMapping("/delCache")
    public String deleteRestrictionCache() {
        logger.info("/user/delCache");
        String msg;
        try {
            userService.deleteUserCache();
            msg = "OK";
        } catch (Exception e) {
            logger.error(e);
            msg = e.getMessage();
        }
        return msg;
    }

}
