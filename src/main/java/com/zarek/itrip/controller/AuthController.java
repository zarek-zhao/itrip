package com.zarek.itrip.controller;

import com.zarek.itrip.pojo.dto.Dto;
import com.zarek.itrip.pojo.entity.ItripUser;
import com.zarek.itrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>爱旅行-认证功能控制层类</b>
 *
 * @author zarek
 * @version 1.0.0
 * @since 1.0.0
 */

@RestController
@RequestMapping("/auth/api")
public class AuthController {
    @Autowired
    private UserService userService;

    /**
     * <b>验证是否已存在该用户</b>
     *
     * @param name
     * @return
     * @throws Exception
     */
    @GetMapping("/ckusr")
    public Dto<Object> checkUserName(String name) throws Exception
    {
        Dto<Object> resultDto = new Dto<Object>();
        // 注册检查用户所提交的用户名是否重复
        // 根据用户所提交的用户名在 itrip_user 表中进行查询，如果能够查询到数据，那么返回false，否则为true
        // 使用用户名进行查找
        ItripUser user = userService.getUserByUserCode(name);
        // 根据查询结果进行判断是否该用户名可用
        // 创建前端返回对象

        if (user == null) {
            // 根据用户名未查到相应的数据，那么说明该用户名未被占用，可以使用
            resultDto.setSuccess("true");
            resultDto.setErrorCode("0");
        }else{
            // 根据用户名查到相应的数据，那么说明用户名已被占用，不可以使用
            resultDto.setSuccess("false");
            resultDto.setErrorCode("1");
            resultDto.setMsg("用户名已被占用，无法使用。");
        }

        return resultDto;
    }

}
