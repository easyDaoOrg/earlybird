package com.easydao.earlybird.bean;

import java.io.Serializable;
import java.util.Date;

/*
 *@author  wyl
 *@date 2019/03/05
 *@description 用户旅客信息实体类
 */
public class Passenger implements Serializable {
    private static final long serialVersionUID = -8840228279902521291L;

    private Long id;
    private Long user_id;
    private String passenger_name_cn;
    private String passenger_name_en;
    private String passenger_nationality;
    private String passenger_gender;
    private String passenger_birthday;
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

    public String getPassenger_name_cn() {
        return passenger_name_cn;
    }

    public void setPassenger_name_cn(String passenger_name_cn) {
        this.passenger_name_cn = passenger_name_cn;
    }

    public String getPassenger_name_en() {
        return passenger_name_en;
    }

    public void setPassenger_name_en(String passenger_name_en) {
        this.passenger_name_en = passenger_name_en;
    }

    public String getPassenger_nationality() {
        return passenger_nationality;
    }

    public void setPassenger_nationality(String passenger_nationality) {
        this.passenger_nationality = passenger_nationality;
    }

    public String getPassenger_gender() {
        return passenger_gender;
    }

    public void setPassenger_gender(String passenger_gender) {
        this.passenger_gender = passenger_gender;
    }

    public String getPassenger_birthday() {
        return passenger_birthday;
    }

    public void setPassenger_birthday(String passenger_birthday) {
        this.passenger_birthday = passenger_birthday;
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
