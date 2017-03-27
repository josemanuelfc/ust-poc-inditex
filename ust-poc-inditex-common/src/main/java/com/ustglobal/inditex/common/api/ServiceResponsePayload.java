package com.ustglobal.inditex.common.api;

import java.io.Serializable;

public class ServiceResponsePayload implements Serializable {

    transient private static final long serialVersionUID = 667730758932001921L;

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "[DATA]:" + data;
    }

}
