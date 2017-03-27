package com.ustglobal.inditex.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.ustglobal.inditex.common.api.ServiceRequest;
import com.ustglobal.inditex.common.api.ServiceResponse;
import com.ustglobal.inditex.common.api.ServiceResponsePayload;
import com.ustglobal.inditex.service.service.IBusinessService;

@Service
public class BusinessServiceImpl implements IBusinessService {

    private static final Logger log = LoggerFactory.getLogger(BusinessServiceImpl.class);

    @Override
    public ServiceResponse execute(ServiceRequest request, String contentType) {

        ServiceResponsePayload payload = new ServiceResponsePayload();
        payload.setData("service-c: " + request.getBody().getInfo());

        ServiceResponse response = new ServiceResponse();
        response.setHeader(request.getHeader());
        response.setBody(payload);

        log.info("[RESULT]:{}", response);

        return response;
    }

}
