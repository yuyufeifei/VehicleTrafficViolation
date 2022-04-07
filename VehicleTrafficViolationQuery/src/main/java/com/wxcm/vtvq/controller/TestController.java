package com.wxcm.vtvq.controller;

import com.wxcm.vtvq.dao.primary.TvqVehicleDao;
import com.wxcm.vtvq.dao.secondary.ApaUserDao;
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

    @Autowired
    private TvqVehicleDao tvqVehicleDao;
    @Autowired
    private ApaUserDao apaUserDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public void Test() {
        System.out.println(tvqVehicleDao.findAll().size());
        System.out.println(apaUserDao.findAll().size());
    }
}
