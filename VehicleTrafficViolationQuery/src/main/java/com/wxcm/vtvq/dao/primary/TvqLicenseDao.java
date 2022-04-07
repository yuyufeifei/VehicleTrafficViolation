package com.wxcm.vtvq.dao.primary;

import com.wxcm.vtvq.entity.primary.TvqLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author GZH
 * @date 2020-07-16
 */
public interface TvqLicenseDao extends JpaRepository<TvqLicense, Long>, JpaSpecificationExecutor<TvqLicense> {

    /**
     * 通过条件查询驾驶证
     * @param idNumber  身份证号码后四位
     * @param fileNumber    12位档案编号
     * @return  TvqLicense
     */
    TvqLicense findByIdnumberAndFilenumber(String idNumber, String fileNumber);

}
