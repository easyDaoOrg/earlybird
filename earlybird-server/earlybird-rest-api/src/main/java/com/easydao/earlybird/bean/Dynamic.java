package com.easydao.earlybird.bean;

import java.io.Serializable;
import java.util.Date;

public class Dynamic implements Serializable {

    private static final long serialVersionUID = -2454118793481461966L;
    private Long id;
    private String user_account;
    private String user_dynamic_code;
    private Date create_date;
    private Long flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_dynamic_code() {
        return user_dynamic_code;
    }

    public void setUser_dynamic_code(String user_dynamic_code) {
        this.user_dynamic_code = user_dynamic_code;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Long getFlag() {
        return flag;
    }

    public void setFlag(Long flag) {
        this.flag = flag;
    }
}
