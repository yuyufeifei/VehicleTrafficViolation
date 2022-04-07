package com.wxcm.vtvq.dao.secondary;

import com.wxcm.vtvq.entity.secondary.ApaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author GZH
 */
public interface ApaUserDao extends JpaRepository<ApaUser, Long>, JpaSpecificationExecutor<ApaUser> {

    /**
     * 通过IPTV账号查询出ApaUser
     * @param iptv IPTV账号
     * @return ApaUser
     */
    ApaUser findByIptv(String iptv);

}
