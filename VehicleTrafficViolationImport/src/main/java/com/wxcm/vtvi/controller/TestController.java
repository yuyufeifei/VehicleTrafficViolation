package com.wxcm.vtvi.controller;

import com.wxcm.vtvi.dao.TvqVehicleDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author GZH
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    private final Logger logger = LogManager.getLogger(TestController.class);

    @Autowired
    private TvqVehicleDao tvqVehicleDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public void test() {
        logger.info("测试info！");
        logger.error("测试error！");
        System.out.println(tvqVehicleDao.count());
    }

}
