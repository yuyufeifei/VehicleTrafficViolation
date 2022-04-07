package com.wxcm.vtvq.dao.primary;

import com.wxcm.vtvq.entity.primary.TrmRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author GZH
 */
public interface TrmRestrictionDao extends JpaRepository<TrmRestriction, Long>, JpaSpecificationExecutor<TrmRestriction> {

    /**
     * 根据city和date查询限行信息
     * @param city  城市
     * @param date  日期
     * @return  TrmRestriction
     */
    List<TrmRestriction> findByCityAndDateOrderByUpdatetimeDesc(String city, String date);

}
