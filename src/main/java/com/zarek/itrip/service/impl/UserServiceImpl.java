package com.zarek.itrip.service.impl;

import com.zarek.itrip.dao.UserDao;
import com.zarek.itrip.pojo.entity.ItripUser;
import com.zarek.itrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <b>爱旅行-用户功能业务层实现类</b>
 * @author zarek
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * <b>根据用户名查找用户信息</b>
     * @param userCode
     * @return
     * @throws Exception
     */
    @Override
    public ItripUser getUserByUserCode(String userCode) throws Exception
    {
        ItripUser user = userDao.findUserByUserCode(userCode);
        return user;
    }
}
