package com.wxcm.vtvq.dao.secondary;

import com.wxcm.vtvq.entity.secondary.ApaLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author GZH
 * @date 2021-03-22
 */
public interface ApaLicenseDao extends JpaRepository<ApaLicense, Long>, JpaSpecificationExecutor<ApaLicense> {

    /**
     * 根据ApaUser的iptv查询对应的List<ApaLicense>
     * @param iptv iptv账号
     * @return List<ApaLicense>
     */
    List<ApaLicense> findByApaUser_Iptv(String iptv);

    /**
     *根据条件查询对应的ApaLicense
     * @param iptv iptv账号
     * @param idnumber 身份证号后四位
     * @param filenumber 12位档案编号
     * @return ApaLicense
     */
    ApaLicense findByApaUser_IptvAndIdnumberAndFilenumber(String iptv, String idnumber, String filenumber);

    /**
     * 根据条件查询ApaLicense
     * @param userid ApaUser.id
     * @param idnumber 身份证号后四位
     * @param filenumber 12位档案编号
     * @return ApaLicense
     */
    ApaLicense findByUseridAndIdnumberAndFilenumber(Long userid, String idnumber, String filenumber);

    /**
     * 根据条件查询ApaLicense
     * @param idnumber 身份证号后四位
     * @param filenumber 12位档案编号
     * @return ApaLicense
     */
    ApaLicense findByIdnumberAndFilenumber(String idnumber, String filenumber);

}
