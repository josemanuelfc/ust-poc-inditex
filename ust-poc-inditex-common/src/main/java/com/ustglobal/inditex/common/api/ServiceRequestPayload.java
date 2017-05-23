package com.ustglobal.inditex.common.api;

import java.io.Serializable;

public class ServiceRequestPayload implements Serializable {

    transient private static final long serialVersionUID = 5788915264199787963L;

    private String info;
    
    private String user;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "[INFO]:" + info;
    }

}
