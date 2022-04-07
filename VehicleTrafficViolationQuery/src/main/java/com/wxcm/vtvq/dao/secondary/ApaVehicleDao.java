package com.wxcm.vtvq.dao.secondary;

import com.wxcm.vtvq.entity.secondary.ApaVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author GZH
 */
public interface ApaVehicleDao extends JpaRepository<ApaVehicle, Long>, JpaSpecificationExecutor<ApaVehicle> {

    /**
     * 根据ApaUser的id查询对应的ApaVehicle
     * @param userId ApaUser的id
     * @return List<ApaVehicle>
     */
    List<ApaVehicle> findByUserid(Long userId);

    /**
     * 根据ApaUser的iptv查询对应的List<ApaVehicle>
     * @param iptv iptv账号
     * @return List<ApaVehicle>
     */
    List<ApaVehicle> findByApaUser_Iptv(String iptv);

    /**
     * 根据条件查询对应的ApaVehicle
     * @param iptv iptv账号
     * @param plate 机动车号牌
     * @param platetype 机动车号牌种类
     * @param identification 车辆识别代号后四位
     * @return ApaVehicle
     */
    ApaVehicle findByApaUser_IptvAndPlateAndPlatetypeAndIdentification(String iptv, String plate, String platetype, String identification);

    /**
     * 根据条件查询ApaVehicle
     * @param userid ApaUser.id
     * @param plate 机动车号牌
     * @param platetype 机动车号牌种类
     * @param identification 车辆识别代号后四位
     * @return ApaVehicle
     */
    ApaVehicle findByUseridAndPlateAndPlatetypeAndIdentification(Long userid, String plate, String platetype, String identification);

    /**
     * 根据条件查询ApaVehicle
     * @param plate 机动车号牌
     * @param platetype 机动车号牌种类
     * @param identification 车辆识别代号后四位
     * @return ApaVehicle
     */
    ApaVehicle findByPlateAndPlatetypeAndIdentification(String plate, String platetype, String identification);

}
