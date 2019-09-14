package com.barley.sercurity.modal;

/**
 * 用户基本模块基本字典信息 
 * @author peculiar.1@163.com
 * @version $ID: UserDict.java, V1.0.0 2015年3月20日 下午8:38:30 $
 */
public class UserDict {
    private String id;

    private String name;

    private Short type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
}