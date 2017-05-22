package com.ustglobal.inditex.common.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ustglobal.inditex.common.InditexMessage;

@JsonInclude(Include.NON_NULL)
public class ServiceResponse extends InditexMessage<ServiceResponsePayload> {

    transient private static final long serialVersionUID = 860430703669310398L;

    @Override
    public String toString() {
        return super.toString();
    }
    
    public String sayHello(String user) {
        return "Response for the user " + user;
    }

}
