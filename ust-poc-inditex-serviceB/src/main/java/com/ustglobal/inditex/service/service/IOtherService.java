package com.ustglobal.inditex.service.service;

import com.ustglobal.inditex.common.api.ServiceRequest;
import com.ustglobal.inditex.common.api.ServiceResponse;
import rx.Observable;

public interface IOtherService {

    Observable<ServiceResponse> execute(ServiceRequest request, String contentType);

}
