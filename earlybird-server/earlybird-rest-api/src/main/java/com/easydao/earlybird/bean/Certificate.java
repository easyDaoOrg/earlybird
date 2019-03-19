package com.easydao.earlybird.bean;

import java.io.Serializable;
import java.util.Date;

/*
 *@author  wyl
 *@date 2019/03/05
 *@description 用户证件信息实体类
 */
public class Certificate implements Serializable {
    private static final long serialVersionUID = 128031977203944940L;
    private Long id;
    private Long passenger_id;
    private String certificate_type;
    private String certificate_no;
    private String certificate_issue_place;
    private String certificate_valid_date;
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

    public Long getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(Long passenger_id) {
        this.passenger_id = passenger_id;
    }

    public String getCertificate_type() {
        return certificate_type;
    }

    public void setCertificate_type(String certificate_type) {
        this.certificate_type = certificate_type;
    }

    public String getCertificate_no() {
        return certificate_no;
    }

    public void setCertificate_no(String certificate_no) {
        this.certificate_no = certificate_no;
    }

    public String getCertificate_issue_place() {
        return certificate_issue_place;
    }

    public void setCertificate_issue_place(String certificate_issue_place) {
        this.certificate_issue_place = certificate_issue_place;
    }

    public String getCertificate_valid_date() {
        return certificate_valid_date;
    }

    public void setCertificate_valid_date(String certificate_valid_date) {
        this.certificate_valid_date = certificate_valid_date;
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
