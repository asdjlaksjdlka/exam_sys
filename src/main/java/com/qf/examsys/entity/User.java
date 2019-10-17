package com.qf.examsys.entity;

import java.io.Serializable;

public class User  implements Serializable {
    private Integer uid;
    private String uPhone;
    private String uPassword;
    private String uName;
    private String uStatus;
    private Integer roId;


    public Integer getRoId() {
        return roId;
    }

    public void setRoId(Integer roId) {
        this.roId = roId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getuPhone() {
        return uPhone;
    }

    public void setuPhone(String uPhone) {
        this.uPhone = uPhone;
    }

    public String getuPassword() {
        return uPassword;
    }

    public void setuPassword(String uPassword) {
        this.uPassword = uPassword;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuStatus() {
        return uStatus;
    }

    public void setuStatus(String uStatus) {
        this.uStatus = uStatus;
    }


    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uPhone='" + uPhone + '\'' +
                ", uPassword='" + uPassword + '\'' +
                ", uName='" + uName + '\'' +
                ", uStatus='" + uStatus + '\'' +
                '}';
    }
}
