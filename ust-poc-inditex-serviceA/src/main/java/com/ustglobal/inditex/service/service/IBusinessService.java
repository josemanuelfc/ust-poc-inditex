package com.ustglobal.inditex.service.service;

import com.ustglobal.inditex.common.api.ServiceRequest;
import com.ustglobal.inditex.common.api.ServiceResponse;

public interface IBusinessService {

    ServiceResponse execute(ServiceRequest request, String contentType);

}
