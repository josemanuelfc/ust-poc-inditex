package com.ustglobal.inditex.service.controller;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ustglobal.inditex.common.api.ServiceRequest;
import com.ustglobal.inditex.common.api.ServiceResponse;
import com.ustglobal.inditex.etc.Constants;
import com.ustglobal.inditex.service.service.IBusinessService;
import rx.Single;

@RestController
public class ServiceController {

    private static final Logger log = LoggerFactory.getLogger(ServiceController.class);

    private IBusinessService businessService;
    
    private String asiocopUser;

    @Autowired
    public void setBusinessService(final IBusinessService businessService) {
        this.businessService = businessService;
    }

    @RequestMapping(value = "/service", method = { RequestMethod.GET, RequestMethod.POST }, consumes = {
            MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE,
            "application/x-msgpack" }, produces = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_JSON_UTF8_VALUE, "application/x-msgpack" })
    public ResponseEntity<Single<ServiceResponse>> service(
            @RequestParam(value = "type", defaultValue = "json") String type,
            @RequestBody ServiceRequest request, HttpServletRequest httpServletRequest) {

        final StringBuilder tracking = new StringBuilder();
        tracking.append("[REQUEST]:").append(request.getHeader().getUid());
        tracking.append("|[TYPE]:").append(type);
        MDC.put("TRACKING", tracking.toString());
        log.info("Request: {}", request);

        ServiceResponse response = businessService.execute(request, Constants.toMediaType(type));

        log.info("Response: {}", response);

        return ResponseEntity.ok(Single.just(response));
    }

}
