package com.zarek.itrip.service;

import com.zarek.itrip.pojo.entity.ItripUser;
import org.springframework.stereotype.Service;

/**
 * <b>爱旅行-用户功能业务层接口</b>
 * @author zarek
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public interface UserService {
    /**
     * <b>根据用户名查找用户信息</b>
     * @param name
     * @return
     * @throws Exception
     */
    ItripUser getUserByUserCode(String name) throws Exception;
}
