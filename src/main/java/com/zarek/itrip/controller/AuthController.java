package com.zarek.itrip.controller;

import com.zarek.itrip.pojo.dto.Dto;
import com.zarek.itrip.pojo.entity.ItripUser;
import com.zarek.itrip.pojo.enums.ActivatedEnum;
import com.zarek.itrip.pojo.vo.ItripTokenVO;
import com.zarek.itrip.pojo.vo.userinfo.ItripUserVO;
import com.zarek.itrip.service.UserService;
import com.zarek.itrip.util.ActivationCodeUtil;
import com.zarek.itrip.util.MD5Util;
import com.zarek.itrip.util.MailUtil;
import com.zarek.itrip.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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
    @Autowired
    private MailUtil mailUtil;
    @Autowired
    private HttpSession session;

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
    public Dto<Object> registryUserByEmail(@RequestBody ItripUserVO userVO) throws Exception
    {
        // 创建Dto对象
        Dto<Object> dto = new Dto<Object>();
        // 获得用户所填写的邮箱地址
        String userCode = userVO.getUserCode();
        // 对于该邮箱地址进行唯一性校验
        // 通过userCode查询用户信息
        ItripUser user = userService.getUserByUserCode(userCode);
        if (user == null) {
            // 此时userCode未被占用
            //校验用户是否提交了登录密码
            if (userVO.getUserPassword() != null && !"".equals(userVO.getUserPassword().trim())) {
                // 对用户所提交了登录密码进行MD5加密
                //使用链式写法可以将一些不常用的变量省略掉，减少内存碎空间的使用，增加代码的执行效率
                userVO.setUserPassword(MD5Util.getMD5(userVO.getUserPassword().trim()));
                //保存用户信息
                boolean isSuccess = userService.registerUser(userVO);
                if (isSuccess) {
                    // 注册成功
                    // 获得激活码，并且将激活码发送到用户的邮箱地址，并且将该激活码保存到HttpSession中
                    // 使用激活码工具获得一个六位数的激活码
                    String code = ActivationCodeUtil.createActivationCode();
                    // 将获得的激活码绑定到 HttpSession 中
                    dto.setSuccess("true");
                    session.setAttribute(userVO.getUserCode(), code);
                    // 使用邮箱工具类发送激活码
                    SmsUtil.sendActivationCode(userVO.getUserCode(),code);
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
            dto.setMsg("该手机号码已被占用");
        }
        return dto;
    }

    /**
     * <b>邮箱注册用户激活</b>
     *
     * @param user
     * @param code
     * @return
     * @throws Exception
     */
    @PutMapping("/activate")
    public Dto<Object> activateUser(String user, String code) throws Exception
    {

        Dto<Object> dto = new Dto<Object>();
        // 根据用户所给定的邮箱地址，在 HttpSession 中查找是否有相应的激活码
        String activationCode = (String) session.getAttribute(user);
        if (activationCode != null) {
            // 已经找到用户的注册码信息，那么比较用户所提交的和 HttpSession 中所绑定的是否一致
            if (activationCode.equals(code)) {
                // 用户所提交的激活码正确，将用户的状态修改为已激活
                boolean isSuccess = userService.activateUser(user);
                if (isSuccess) {
                    // 激活成功
                    dto.setSuccess("ture");
                    dto.setMsg("激活成功");
                } else {
                    // 激活失败
                    dto.setSuccess("false");
                    dto.setMsg("激活失败");
                }

            } else {
                // 用户所提交的激活码错误，提示用户
                dto.setSuccess("false");
                dto.setMsg("激活码错误！");
            }
        } else {
            // 没有找到激活码
            dto.setSuccess("false");
            dto.setMsg("该用户未注册！");
        }
        return null;
    }

    /**
     * <b>使用手机号码进行用户信息注册</b>
     *
     * @param userVO
     * @return
     * @throws Exception
     */
    @PostMapping("/registerbyphone")//@RequestBody注解将前端传的值映射为一个json格式和@ResponseBody有异曲同工之妙
    public Dto<Object> registryUserByCellPhone(@RequestBody ItripUserVO userVO) throws Exception
    {
        // 创建Dto对象
        Dto<Object> dto = new Dto<Object>();
        // 获得用户所填写的手机号码
        String userCode = userVO.getUserCode();
        // 对于该手机号码进行唯一性校验
        // 通过userCode查询用户信息
        ItripUser user = userService.getUserByUserCode(userCode);
        if (user == null) {
            // 此时userCode未被占用
            //校验用户是否提交了登录密码
            if (userVO.getUserPassword() != null && !"".equals(userVO.getUserPassword().trim())) {
                // 对用户所提交了登录密码进行MD5加密
                //使用链式写法可以将一些不常用的变量省略掉，减少内存碎空间的使用，增加代码的执行效率
                userVO.setUserPassword(MD5Util.getMD5(userVO.getUserPassword().trim()));
                //保存用户信息
                boolean isSuccess = userService.registerUser(userVO);
                if (isSuccess) {
                    // 注册成功
                    // 获得激活码，并且将激活码发送到用户的邮箱地址，并且将该激活码保存到HttpSession中
                    // 使用激活码工具获得一个六位数的激活码
                    String code = ActivationCodeUtil.createActivationCode();
                    // 将获得的激活码绑定到 HttpSession 中
                    dto.setSuccess("true");
                    session.setAttribute(userVO.getUserCode(), code);
                    // 使用邮箱工具类发送激活码
                    mailUtil.sendActivationCodeMail(userVO.getUserCode(), code);
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

    /**
     * <b>手机注册用户激活</b>
     *
     * @param user
     * @param code
     * @return
     * @throws Exception
     */
    @PutMapping("/validatephone")
    public Dto<Object> activateUserByCelLphone(String user, String code) throws Exception
    {

        Dto<Object> dto = new Dto<Object>();
        // 根据用户所给定的手机号码，在 HttpSession 中查找是否有相应的激活码
        String activationCode = (String) session.getAttribute(user);
        if (activationCode != null) {
            // 已经找到用户的注册码信息，那么比较用户所提交的和 HttpSession 中所绑定的是否一致
            if (activationCode.equals(code)) {
                // 用户所提交的激活码正确，将用户的状态修改为已激活
                boolean isSuccess = userService.activateUser(user);
                if (isSuccess) {
                    // 激活成功
                    dto.setSuccess("ture");
                    dto.setMsg("激活成功");
                } else {
                    // 激活失败
                    dto.setSuccess("false");
                    dto.setMsg("激活失败");
                }

            } else {
                // 用户所提交的激活码错误，提示用户
                dto.setSuccess("false");
                dto.setMsg("激活码错误！");
            }
        } else {
            // 没有找到激活码
            dto.setSuccess("false");
            dto.setMsg("该用户未注册！");
        }
        return null;
    }

    /**
     * <b>用户登录功能</b>
     * @param name
     * @param password
     * @return
     * @throws Exception
     */
    @PostMapping("/dologin")
    public Dto<Object> loginUser(String name, String password) throws Exception{

        Dto<Object> dto = new Dto<Object>();

        // 检验用户所提交的登录信息是否有效
        if(name!=null && !"".equals(name) && password!=null && !"".equals(password)){
            // 用户所提交的登录信息有效
            password = MD5Util.getMD5(password);
            //使用用户名查找相关用户信息
            ItripUser user = userService.getUserByUserCode(name);
            if(user != null){
                // 通过用户名查找到相关信息
                // 比较该用户的密码和用户所提交的密码是否相同
                if(user.getUserPassword().equals(password)){
                    // 该用户提交的登录信息正确，校验该用户是否已经激活
                    if(user.getActivated() == ActivatedEnum.ACTIVATED_ENABLE.getCode()){
                        // 该用户登陆成功，绑定 HttpSession 信息
                        session.setAttribute("user", user);
                        dto.setSuccess("true");
                        // 创建 ItripTokenVO 对象，返回相关信息
                        ItripTokenVO tokenVO = new ItripTokenVO();
                        tokenVO.setToken(MD5Util.getMD5(String.valueOf(System.currentTimeMillis())));
                        tokenVO.setExpTime(System.currentTimeMillis() + 30  * 60 * 1000);
                        tokenVO.setGenTime(System.currentTimeMillis());
                        dto.setData(tokenVO);
                        dto.setMsg("登录成功！");
                    }else{
                        dto.setSuccess("false");
                        dto.setMsg("该用户尚未激活！");
                    }
                }else{
                    // 该用户所提交的登录密码错误
                    dto.setSuccess("false");
                    dto.setMsg("登录密码错误！");
                }

            }else{
                // 登录失败
                dto.setSuccess("true");
                dto.setMsg("用户名或密码错误");
            }
        }else{
            dto.setSuccess("false");
            dto.setMsg("请填写有效的登录信息");
        }

        return dto;

    }

}
