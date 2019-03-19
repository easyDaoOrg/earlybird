package com.easydao.earlybird.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.easydao.earlybird.bean.User;
import com.easydao.earlybird.service.UserService;
import com.easydao.earlybird.utils.ShiroUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Random;
import javax.servlet.http.HttpServletResponse;
import com.easydao.earlybird.utils.ShiroToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;

/*
 *@author  czg
 *@date 2018/9/27 15:05
 *@description 用户控制层
 */
@CrossOrigin
@RestController
@RequestMapping("easydao/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    /*
     *@author  czg
     *@date 2018/11/8 14:39
     *@description 登录拦截
     */
    @GetMapping("/login")
    public String login(HttpServletResponse response){
        JSONObject result = new JSONObject();
        if (ShiroUtils.isLogin()) {
            result.put("isLogin", true);
        } else {
            // 设置返回状态码
            response.setStatus(401);
        }
        return JSON.toJSONString(result);
    }

    /*
     *@author  czg
     *@date 2018/11/8 14:40
     *@description  登录验证
     */
    @PostMapping("doLogin")
    public String doLogin(@RequestBody User user, HttpServletResponse response) {
        JSONObject result = new JSONObject();
        ShiroToken token = null;
        if("passwd".equals(user.getLogin_flag())){
            token = new ShiroToken(user.getUser_account(), user.getUser_passwd().toCharArray());
        }else if ("dynamic".equals(user.getLogin_flag())){
            User loginUser = userService.findUserByName(user.getUser_account());
            token = new ShiroToken(loginUser.getUser_account(), loginUser.getUser_passwd_show().toCharArray());
        }
        try {
            SecurityUtils.getSubject().login(token);
            SecurityUtils.getSubject().getSession().setAttribute("token", token);
            String sessionid = SecurityUtils.getSubject().getSession().getId().toString();
            result.put("token", sessionid);
            result.put("code", 200);
            result.put("message", "登录成功");
            User reUser = ShiroUtils.getCurrentUser();
            reUser.setUser_passwd_show("");
            reUser.setUser_passwd("");
            result.put("user",reUser);
        } catch (AuthenticationException authException) {
            result.put("code", 500);
            if (authException instanceof IncorrectCredentialsException) {
                result.put("message", "用户名密码不正确");
            } else {
                result.put("message", authException.getMessage());
                //result.put("message", "用户名密码不正确");
            }
            logger.error("登录出错", authException);
        }
        return result.toJSONString();
    }


    /*
     *@author  czg
     *@date 2018/9/28 11:20
     *@description 用户列表
     */

    @PostMapping("list")
    public String getList(@RequestBody Map<String, Object> paramMap){
        JSONObject result = new JSONObject();
        PageInfo<User> pageInfo = userService.getUserList(paramMap);
        result.put("rows", pageInfo.getList());
        result.put("total", pageInfo.getTotal());
        return JSON.toJSONString(result, SerializerFeature.WriteDateUseDateFormat);
    }
    
    /*
     *@author  czg
     *@date 2018/9/28 17:38
     *@description 新建/编辑用户
     */
    @PostMapping("saveUser")
    public String addUser(@RequestBody  User user){
        JSONObject result = new JSONObject();
        result.put("flag", false);
        try {

           if(!user.getUser_phone().isEmpty()){
               user.setUser_account(user.getUser_phone());
           }else if(!user.getUser_email().isEmpty()){
               user.setUser_account(user.getUser_email());
           }
            String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
            String user_passwd_show = getRandomNum(8);
            String user_passwd = new SimpleHash("md5", user_passwd_show, salt, 2).toHex();
            user.setUser_passwd(user_passwd);
            user.setSalt(salt);
            user.setUser_passwd_show(user_passwd_show);
            userService.addUser(user);
            result.put("flag", true);
        } catch (Exception e) {
            logger.error("添加/修改用户异常", e);
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }


    private String getRandomNum(int length) {
        String val = "";
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            if( "char".equalsIgnoreCase(charOrNum) ) {
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char)(random.nextInt(26) + temp);
            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /*
     *@author  czg
     *@date 2018/9/28 18:59
     *@description 删除用户
     */
    @DeleteMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        JSONObject result = new JSONObject();
        result.put("flag", false);
        try {
            userService.deleteUser(id);
            result.put("flag", true);
        } catch (Exception e) {
            logger.error("删除用户异常!",e);
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }


    /*
     *@author  czg
     *@date 2018/11/23 10:22
     *@description 进入修改密码，获取当前登录用户的信息
   */
    @GetMapping("inputEditPasswd")
    public String inputEditPasswd(){
        JSONObject result = new JSONObject();
        User user = ShiroUtils.getCurrentUser();
        result.put("user", user);
        return JSONObject.toJSONString(result);
    }

    /*
     *@author  czg
     *@date 2018/11/23 10:37
     *@description 修改密码
     */
    @GetMapping("editPasswd")
    public String editPasswd(String user_passwd){
        JSONObject result = new JSONObject();
        result.put("flag", true);
        try {
            User user = ShiroUtils.getCurrentUser();
            System.out.println("user_passwd:["+user_passwd+"]");
            if(StringUtils.isNotBlank(user_passwd)){
                String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
                System.out.println("salt:["+salt+"]");
                String password = new SimpleHash("md5", user_passwd, salt, 2).toHex();
                user.setSalt(salt);
                user.setUser_passwd(password);
                user.setUser_passwd_show(user_passwd);
                userService.editPasswd(user);
            }else {
                result.put("flag", false);
                result.put("msg", "密码不许为空");
            }
        } catch (Exception e) {
            result.put("flag", false);
            result.put("msg", "修改密码失败");
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    @GetMapping("editUserPhone")
    public String editUserPhone(String user_phone){
        JSONObject result = new JSONObject();
        result.put("flag", true);
        try {
            User user = ShiroUtils.getCurrentUser();
            user.setUser_phone(user_phone);
            userService.editUserPhone(user);

        } catch (Exception e) {
            result.put("flag", false);
            result.put("msg", "修改手机号失败");
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

    @GetMapping("editUserEmail")
    public String editUserEmail(String user_email){
        JSONObject result = new JSONObject();
        result.put("flag", true);
        try {
            User user = ShiroUtils.getCurrentUser();
            user.setUser_email(user_email);
            userService.editUserEmail(user);
        } catch (Exception e) {
            result.put("flag", false);
            result.put("msg", "修改邮箱失败");
            e.printStackTrace();
        }
        return JSON.toJSONString(result);
    }

}
