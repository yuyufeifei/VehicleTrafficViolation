package com.wxcm.vtvi.controller;

import com.wxcm.vtvi.service.TrafficRestrictionService;
import com.wxcm.vtvi.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/restriction")
    public Object receiveRestriction(@RequestParam String city, @RequestParam String date, @RequestParam String content) {
        Map<String, Object> result = new HashMap<>(8);
        int code = -1;
        if(!"".equals(city) && !"".equals(date) && date.length() == 10 && !"".equals(content)) {
            try {
                trafficRestrictionService.receiveRestriction(city, date, content);
                code = 1;
            } catch (Exception e) {
                logger.error(e);
            }
        } else {
            code = 0;
        }
        result.put("code", code);
        result.put("msg", Constants.CODE_TO_MSG.get(code));
        logger.info("/restriction param: city=" + city + " date=" + date + " content=" + content + " ;result: " + result);
        return result;
    }


}
