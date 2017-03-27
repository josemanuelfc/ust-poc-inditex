package com.ustglobal.inditex.service.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ustglobal.inditex.common.api.ServiceRequest;
import com.ustglobal.inditex.common.api.ServiceResponse;
import com.ustglobal.inditex.common.api.ServiceResponsePayload;
import com.ustglobal.inditex.service.service.IBusinessService;
import com.ustglobal.inditex.service.service.IOtherService;

@Service
public class BusinessServiceImpl implements IBusinessService {

    private static final Logger log = LoggerFactory.getLogger(BusinessServiceImpl.class);

    private IOtherService otherService;

    @Autowired
    public void setOtherService(final IOtherService otherService) {
        this.otherService = otherService;
    }

    @Override
    public ServiceResponse execute(ServiceRequest request, String contentType) {

        ServiceResponse otherResponse = new ServiceResponse();
        otherService.execute(request, contentType).subscribe(resp -> {
            otherResponse.setHeader(resp.getHeader());
            otherResponse.setBody(resp.getBody());
        });


        ServiceResponsePayload payload = new ServiceResponsePayload();
        payload.setData("service-a: " + request.getBody().getInfo() + "|" + otherResponse.getBody().getData());

        ServiceResponse response = new ServiceResponse();
        response.setHeader(request.getHeader());
        response.setBody(payload);

        log.info("[RESULT]:{}", response);

        return response;
    }

}
