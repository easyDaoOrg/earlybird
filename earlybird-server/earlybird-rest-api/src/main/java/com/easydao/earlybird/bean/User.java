package com.easydao.earlybird.bean;

import java.io.Serializable;
import java.util.Date;

/*
 *@author  wyl
 *@date 2019/03/05
 *@description 用户信息实体
 */
public class User implements Serializable {

    private static final long serialVersionUID = 5841918163597493478L;

    private Long id;
    private String user_name;
    private String user_email;
    private String user_phone;
    private String user_account;
    private String user_passwd;
    private String user_passwd_show;
    private String salt;
    private Integer del_flag;
    private Long create_by_id;
    private String create_by;
    private Date create_date;
    private Long update_by_id;
    private String update_by;
    private Date update_date;
    //用户类型 0：管理员  1：普通用户
    private Integer user_type;
    //登录标识
    private String login_flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_passwd() {
        return user_passwd;
    }

    public void setUser_passwd(String user_passwd) {
        this.user_passwd = user_passwd;
    }

    public Integer getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(Integer del_flag) {
        this.del_flag = del_flag;
    }

    public Long getCreate_by_id() {
        return create_by_id;
    }

    public void setCreate_by_id(Long create_by_id) {
        this.create_by_id = create_by_id;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Long getUpdate_by_id() {
        return update_by_id;
    }

    public void setUpdate_by_id(Long update_by_id) {
        this.update_by_id = update_by_id;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public Date getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(Date update_date) {
        this.update_date = update_date;
    }

    public Integer getUser_type() {
        return user_type;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getLogin_flag() {
        return login_flag;
    }

    public void setLogin_flag(String login_flag) {
        this.login_flag = login_flag;
    }

    public String getUser_passwd_show() {
        return user_passwd_show;
    }

    public void setUser_passwd_show(String user_passwd_show) {
        this.user_passwd_show = user_passwd_show;
    }
}
