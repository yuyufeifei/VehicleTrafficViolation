package com.wxcm.vtvi.dao;

import com.wxcm.vtvi.entity.TvqLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author GZH
 */
public interface TvqLicenseDao extends JpaRepository<TvqLicense, Long>, JpaSpecificationExecutor<TvqLicense> {

    /**
     * 通过条件查询驾驶证
     * @param idNumber  身份证号码后四位
     * @param fileNumber    12位档案编号
     * @return  TvqLicense
     */
    TvqLicense findByIdnumberAndFilenumber(String idNumber, String fileNumber);

    /**
     * 根据到期时间查询驾驶证
     * @param expiryDate    到期时间
     * @return  List<TvqLicense>
     */
    List<TvqLicense> findByExpirydate(String expiryDate);

}
