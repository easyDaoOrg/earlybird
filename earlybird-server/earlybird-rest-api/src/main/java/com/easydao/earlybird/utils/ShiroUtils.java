package com.easydao.earlybird.utils;

import com.easydao.earlybird.bean.User;
import org.apache.shiro.SecurityUtils;

public class ShiroUtils {

	/*
	 *@author  czg
	 *@date 2018/9/29 13:38
	 *@description 判断当前用户是否认证
	 */
	public static boolean isLogin() {
		return SecurityUtils.getSubject().isAuthenticated();
	}

	/*
	 *@author  czg
	 *@date 2018/9/29 13:38
	 *@description 获取当前用户身份
	 */
	public static User getCurrentUser() {
		return (User) SecurityUtils.getSubject().getPrincipal();
	}

}
