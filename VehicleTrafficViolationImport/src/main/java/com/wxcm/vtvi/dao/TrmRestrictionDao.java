package com.wxcm.vtvi.dao;

import com.wxcm.vtvi.entity.TrmRestriction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author GZH
 */
public interface TrmRestrictionDao extends JpaRepository<TrmRestriction, Long>, JpaSpecificationExecutor<TrmRestriction> {

}
