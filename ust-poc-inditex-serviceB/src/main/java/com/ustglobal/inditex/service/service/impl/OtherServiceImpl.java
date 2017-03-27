package com.ustglobal.inditex.service.service.impl;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ustglobal.inditex.common.api.ServiceRequest;
import com.ustglobal.inditex.common.api.ServiceResponse;
import com.ustglobal.inditex.common.api.ServiceResponsePayload;
import com.ustglobal.inditex.service.service.IOtherService;
import rx.Observable;

@Service
public class OtherServiceImpl implements IOtherService {

    private static final Logger log = LoggerFactory.getLogger(OtherServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service.url}")
    private String serviceUrl;

    @Override
    @HystrixCommand(fallbackMethod = "getReliable")
    public Observable<ServiceResponse> execute(ServiceRequest request, String contentType) {

        log.debug("contentType = {}", contentType);

        MediaType mediaType = MediaType.valueOf(contentType);

        return Observable.create(subscriber -> {
            String url = serviceUrl;

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(mediaType));
            headers.setContentType(mediaType);
            HttpEntity<ServiceRequest> httpEntity = new HttpEntity<>(request, headers);

            final ResponseEntity<ServiceResponse> result = restTemplate.exchange(url, HttpMethod.POST, httpEntity,
                    ServiceResponse.class);

            log.info("[RESULT]:{}", result);

            subscriber.onNext(result.getBody());
            subscriber.onCompleted();
        });

    }

    public ServiceResponse getReliable(ServiceRequest request, String contentType) {
        ServiceResponsePayload payload = new ServiceResponsePayload();
        payload.setData("Reliable");
        ServiceResponse response = new ServiceResponse();
        response.setHeader(request.getHeader());
        response.setBody(payload);
        return response;
    }

}
