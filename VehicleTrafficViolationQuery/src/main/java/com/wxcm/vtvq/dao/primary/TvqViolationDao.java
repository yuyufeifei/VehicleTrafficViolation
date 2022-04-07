package com.wxcm.vtvq.dao.primary;

import com.wxcm.vtvq.entity.primary.TvqViolation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author GZH
 */
public interface TvqViolationDao extends JpaRepository<TvqViolation, Long>, JpaSpecificationExecutor<TvqViolation> {

    /**
     * 根据vehicleId查TvqViolationList
     * @param vehicleId 机动车ID
     * @return 违法信息列表
     */
    List<TvqViolation> findByVehicleid(Long vehicleId);

}
