package com.zarek.itrip.controller;

import com.zarek.itrip.pojo.dto.Dto;
import com.zarek.itrip.pojo.entity.ItripUser;
import com.zarek.itrip.pojo.vo.userinfo.ItripUserVO;
import com.zarek.itrip.service.UserService;
import com.zarek.itrip.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <b>爱旅行-认证功能控制层类</b>
 *
 * @author zarek
 * @version 1.0.0
 * @since 1.0.0
 */

@RestController("authController")
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
        } else {
            // 根据用户名查到相应的数据，那么说明用户名已被占用，不可以使用
            resultDto.setSuccess("false");
            resultDto.setErrorCode("1");
            resultDto.setMsg("用户名已被占用，无法使用。");
        }

        return resultDto;
    }

    /**
     * <b>使用邮箱地址进行用户信息注册</b>
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @PostMapping("/doregister")//@RequestBody注解将前端传的值映射为一个json格式和@ResponseBody有异曲同工之妙
    public Dto<Object> registryUser(@RequestBody ItripUserVO userVO) throws Exception
    {
        // 创建Dto对象
        Dto<Object> dto = new Dto<Object>();
        // 获得用户所填写的邮箱地址
        String userCode = userVO.getUserCode();
        // 对于改邮箱地址进行唯一性校验
        // 通过userCode查询用户信息
        ItripUser user = userService.getUserByUserCode(userCode);
        if (user == null) {
            // 此时userCode未被占用
            //校验用户是否提交了登录密码
            if (userVO.getUserPassword() != null && !"".equals(userVO.getUserPassword().trim())) {
                // 对用户所提交了登录密码进行MD5加密
                //使用链式写法可以将一些不常用的变量省略掉，减少内存碎空间的使用，增加代码的执行效率
                userVO.setUserCode(MD5Util.getMD5(userVO.getUserPassword().trim()));
                //保存用户信息
                boolean isSuccess = userService.registerUser(userVO);
                if (isSuccess) {
                    // registry success
                    dto.setSuccess("true");
                    dto.setMsg("用户注册成功!");
                } else {
                    // registry failure
                    dto.setSuccess("false");
                    dto.setMsg("用户注册失败!");
                }
            } else {
                // 用户所填写的密码错误
                dto.setSuccess("false");
                dto.setMsg("请填写有效的登录密码!");
            }
        } else {
            // 此时userCode已被占用
            // 返回邮件已被占用的信息
            dto.setSuccess("false");
            dto.setMsg("该邮件已被占用");
        }
        return dto;
    }

}
