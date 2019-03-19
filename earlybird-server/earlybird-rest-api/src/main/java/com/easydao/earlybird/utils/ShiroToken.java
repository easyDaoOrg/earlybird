package com.easydao.earlybird.utils;

import org.apache.shiro.authc.UsernamePasswordToken;
/*
 *@author  czg
 *@date 2018/10/12 14:29
 *@description  继承shiro token
 */
public class ShiroToken extends UsernamePasswordToken {

    private String username;

    private char[] password;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public char[] getPassword() {
        return password;
    }

    @Override
    public void setPassword(char[] password) {
        this.password = password;
    }

    public ShiroToken(String username, char[] password) {
        this.username = username;
        this.password = password;
    }


}