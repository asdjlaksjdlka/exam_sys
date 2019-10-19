package com.qf.examsys.entity;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {

    private Integer peid;
    private String pName;
    private String pDesc;
    private String pType;
    private String url;
    private Integer pParentid;

    private List<Menu> menus;

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public Integer getPeid() {
        return peid;
    }

    public void setPeid(Integer peid) {
        this.peid = peid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getpParentid() {
        return pParentid;
    }

    public void setpParentid(Integer pParentid) {
        this.pParentid = pParentid;
    }
}
