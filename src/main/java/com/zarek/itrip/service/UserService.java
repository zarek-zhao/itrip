package com.zarek.itrip.service;

import com.zarek.itrip.pojo.entity.ItripUser;
import com.zarek.itrip.pojo.vo.userinfo.ItripUserVO;

/**
 * <b>爱旅行-用户功能业务层接口</b>
 * @author zarek
 * @version 1.0.0
 * @since 1.0.0
 */
public interface UserService {
    /**
     * <b>根据用户名查找用户信息</b>
     * @param name
     * @return
     * @throws Exception
     */
    ItripUser getUserByUserCode(String name) throws Exception;

    /**
     * <b>进行用户信息注册</b>
     * @param userVO
     * @return
     * @throws Exception
     */
    boolean registerUser(ItripUserVO userVO) throws Exception;

    /**
     * <b>激活用户信息</b>
     * @param userCode
     * @return
     * @throws Exception
     */
    boolean activateUser(String userCode) throws Exception;

}
