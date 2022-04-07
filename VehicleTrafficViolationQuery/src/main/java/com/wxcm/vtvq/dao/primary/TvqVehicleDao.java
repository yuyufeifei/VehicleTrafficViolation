package com.wxcm.vtvq.dao.primary;

import com.wxcm.vtvq.entity.primary.TvqVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author GZH
 */
public interface TvqVehicleDao extends JpaRepository<TvqVehicle, Long>, JpaSpecificationExecutor<TvqVehicle> {

    /**
     * 通过机动车号牌、机动车号牌种类、车辆识别代号后四位查询
     * @param plate  机动车号牌
     * @param plateType  机动车号牌种类
     * @param identification  车辆识别代号后四位
     * @return TvqVehicle
     */
    TvqVehicle findByPlateAndPlatetypeAndIdentification(String plate, String plateType, String identification);

}
