package com.wxcm.vtvq.controller;

import com.wxcm.vtvq.service.TrafficRestrictionService;
import com.wxcm.vtvq.util.Constants;
import com.wxcm.vtvq.vo.primary.Restriction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GZH
 */
@RestController
public class TrafficRestrictionController {

    private final Logger logger = LogManager.getLogger(TrafficRestrictionController.class);

    @Autowired
    private TrafficRestrictionService trafficRestrictionService;

    @GetMapping("/restriction")
    public Object receiveRestriction(@RequestParam String city, @RequestParam String date) {
        Map<String, Object> result = new HashMap<>(8);
        int code = -1;
        if(!"".equals(city) && !"".equals(date)) {
            try {
                Restriction restriction = trafficRestrictionService.getRestriction(city, date);
                if(restriction != null) {
                    result.put("data", restriction);
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
        logger.info("/restriction param: city=" + city + " date=" + date + " ;result: " + result);
        return result;
    }

    @GetMapping("/restriction/delCache")
    public String deleteRestrictionCache() {
        logger.info("/restriction/delCache");
        String msg;
        try {
            trafficRestrictionService.deleteRestrictionCache();
            msg = "OK";
        } catch (Exception e) {
            logger.error(e);
            msg = e.getMessage();
        }
        return msg;
    }
}
