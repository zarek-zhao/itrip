package com.zarek.itrip.dao;

import com.zarek.itrip.pojo.entity.ItripUser;
import org.springframework.stereotype.Repository;

/**
 * <b>爱旅行-用户功能数据持久层接口</b>
 * @author zarek
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface UserDao {

    /**
     * <b>使用 userCode 查询用户信息</b>
     * @param userCode
     * @return
     * @throws Exception
     */
    ItripUser findUserByUserCode(String userCode) throws Exception;

    /**
     * <b>保存用户的信息</b>
     * @param user
     * @return
     * @throws Exception
     */
    int saveUser(ItripUser user) throws Exception;
}
