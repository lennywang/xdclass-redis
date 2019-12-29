package com.xdclass.redis.domain;

import java.io.Serializable;

/**
 *
 **/
public class User implements Serializable {
    private static final long serialVersionUID = 6707746938888397429L;

    private Integer id;

    private String userName;

    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
