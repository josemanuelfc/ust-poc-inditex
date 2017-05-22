package com.ustglobal.inditex.common.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ustglobal.inditex.common.InditexMessage;

@JsonInclude(Include.NON_NULL)
public class ServiceRequest extends InditexMessage<ServiceRequestPayload> {

    transient private static final long serialVersionUID = 6902298553825328809L;

    @Override
    public String toString() {
        return super.toString();
    }
    
    public String say_hello(String user) {
        return "Hello " + user;
    }

}
