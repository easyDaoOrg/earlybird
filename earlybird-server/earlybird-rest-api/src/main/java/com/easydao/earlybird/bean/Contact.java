package com.easydao.earlybird.bean;

import java.io.Serializable;
import java.util.Date;

/*
 *@author  wyl
 *@date 2019/03/05
 *@description 用户联系人信息实体类
 */
public class Contact implements Serializable {
    private static final long serialVersionUID = 7033775400778528297L;

    private Long id;
    private Long user_id;
    private String contact_name;
    private String contact_email;
    private String contact_phone;
    private Long create_by_id;
    private String create_by;
    private Date create_date;
    private Long update_by_id;
    private String update_by;
    private Date update_date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_email() {
        return contact_email;
    }

    public void setContact_email(String contact_email) {
        this.contact_email = contact_email;
    }

    public String getContact_phone() {
        return contact_phone;
    }

    public void setContact_phone(String contact_phone) {
        this.contact_phone = contact_phone;
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
}
