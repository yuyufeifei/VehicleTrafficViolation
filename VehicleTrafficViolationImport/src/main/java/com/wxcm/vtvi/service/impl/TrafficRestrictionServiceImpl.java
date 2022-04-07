package com.wxcm.vtvi.service.impl;

import com.wxcm.vtvi.dao.TrmRestrictionDao;
import com.wxcm.vtvi.entity.TrmRestriction;
import com.wxcm.vtvi.service.TrafficRestrictionService;
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
public class TrafficRestrictionServiceImpl implements TrafficRestrictionService {

    private final Logger logger = LogManager.getLogger(TrafficRestrictionServiceImpl.class);

    @Autowired
    private TrmRestrictionDao trmRestrictionDao;

    @Override
    @Transactional(value = "transactionManager", rollbackFor = Exception.class)
    public void receiveRestriction(String city, String date, String content) {
        try {
            TrmRestriction trmRestriction = new TrmRestriction();
            trmRestriction.setCity(city);
            trmRestriction.setDate(date);
            trmRestriction.setContent(content);
            trmRestriction.setUpdatetime(new Date());
            trmRestrictionDao.save(trmRestriction);
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
