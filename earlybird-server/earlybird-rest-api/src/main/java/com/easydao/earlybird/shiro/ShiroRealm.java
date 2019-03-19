package com.easydao.earlybird.shiro;


import com.easydao.earlybird.bean.User;
import com.easydao.earlybird.utils.ShiroToken;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.easydao.earlybird.service.UserService;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *@author  czg
 *@date 2018/10/15 16:49
 *@description 自定义realm
 */
@Component
public class ShiroRealm extends AuthorizingRealm {

    private final String REGEX = "perms\\[(.*?)\\]";

    @Autowired
    private UserService userService;

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        ShiroToken token = (ShiroToken) authenticationToken;
        String username = token.getUsername();
        char[] password = token.getPassword();
        if(StringUtils.isBlank(username)){
            throw new AccountException("用户名不能为空");
        }
//        if(password == null){
//            throw new AccountException("密码不能为空");
//        }
        User user = userService.findUserByName(username);
        if(user == null){
            throw new AccountException("用户名不存在");
        }
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getUser_passwd(), ByteSource.Util.bytes(user.getSalt()), getName());
        return info;
    }

    /**
     * 权限认证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
      return null;
    }

    private Set<String> convertPermission(Set<String> permissions, String regex) {
        Set<String> result = new HashSet<String>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(StringUtils.join(permissions, ","));
        while (matcher.find()) {
            result.add(matcher.group(1));
        }
        return result;
    }

    public static void main(String[] args) {
        String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
        String password = new SimpleHash("md5", "123456", salt, 2).toHex();
        System.out.println(salt);
        System.out.println(password);

    }
}
