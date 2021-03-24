package com.zarek.itrip.service.impl;

import com.zarek.itrip.dao.UserDao;
import com.zarek.itrip.pojo.entity.ItripUser;
import com.zarek.itrip.pojo.enums.ActivatedEnum;
import com.zarek.itrip.pojo.enums.UserTypeEnum;
import com.zarek.itrip.pojo.vo.userinfo.ItripUserVO;
import com.zarek.itrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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


    /**
     * <b>进行用户信息注册</b>
     * @param userVO
     * @return
     * @throws Exception
     */
    @Override
    public boolean registerUser(ItripUserVO userVO) throws Exception
    {

        //创建 ItripUser 对象，并且根据 ItripUserVO 的值填充 ItripUser 的属性
        ItripUser user = new ItripUser();
        user.setUserCode(userVO.getUserCode());
        user.setUserPassword(userVO.getUserPassword());
        user.setUserType(UserTypeEnum.USER_TYPE_SELF.getCode());
        user.setUserName(userVO.getUserName());
        user.setCreationDate(new Date());
        user.setModifyDate(new Date());
        // 设定用户为未激活状态
        user.setActivated(ActivatedEnum.ACTIVATED_DISABLE.getCode());
        // 保存用户信息
        int count = userDao.saveUser(user);
        if(count > 0){
            // 用户注册成功
            return true;
        }else{
            // 用户注册失败
            return false;
        }
    }

    /**
     * <b>激活用户信息</b>
     * @param userCode
     * @return
     * @throws Exception
     */
    @Override
    public boolean activateUser(String userCode) throws Exception
    {

        // 根据 userCode 查找用户激活信息
        ItripUser user = userDao.findUserByUserCode(userCode);
        // 将状态修改为已激活状态
        user.setActivated(ActivatedEnum.ACTIVATED_ENABLE.getCode());
        int count = userDao.updateUser(user);
        if(count > 0){
            return true;
        }
        return false;
    }
}
