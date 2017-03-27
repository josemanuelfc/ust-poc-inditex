package com.ustglobal.inditex.service;

import org.apache.coyote.http2.Http2Protocol;
import org.msgpack.jackson.dataformat.MessagePackFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.system.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class ServiceApplication {


    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ServiceApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
    }

    @Bean
    @Profile("http2")
    public EmbeddedServletContainerCustomizer tomcatCustomizer() {
        return (container) -> {
            if (container instanceof TomcatEmbeddedServletContainerFactory) {
                ((TomcatEmbeddedServletContainerFactory) container).addConnectorCustomizers((connector) -> {
                    connector.addUpgradeProtocol(new Http2Protocol());
                });
            }
        };
    }

    @Bean
    public HttpMessageConverter<?> messagePackMessageConverter() {
        return new AbstractJackson2HttpMessageConverter(new ObjectMapper(new MessagePackFactory()),
                new MediaType("application", "x-msgpack")) {};
    }

}
