package com.wxcm.vtvq.service.impl;

import com.wxcm.vtvq.dao.primary.TrmRestrictionDao;
import com.wxcm.vtvq.entity.primary.TrmRestriction;
import com.wxcm.vtvq.service.TrafficRestrictionService;
import com.wxcm.vtvq.vo.primary.Restriction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GZH
 */
@Service
public class TrafficRestrictionServiceImpl implements TrafficRestrictionService {

    private final Logger logger = LogManager.getLogger(TrafficRestrictionServiceImpl.class);

    @Autowired
    private TrmRestrictionDao trmRestrictionDao;

    @Override
    @Cacheable(value = "restriction", key = "#city+#date")
    public Restriction getRestriction(String city, String date) {
        Restriction restriction = null;
        try {
            List<TrmRestriction> trmRestrictionList = trmRestrictionDao.findByCityAndDateOrderByUpdatetimeDesc(city, date);
            if(trmRestrictionList.size() > 0) {
                restriction = new Restriction(trmRestrictionList.get(0));
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return restriction;
    }

    @Override
    @CacheEvict(value = "restriction", allEntries = true)
    public void deleteRestrictionCache() {
        //do nothing
    }
}
