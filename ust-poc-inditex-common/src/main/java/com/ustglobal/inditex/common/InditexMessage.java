package com.ustglobal.inditex.common;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class InditexMessage<T extends Serializable> implements Serializable {

    transient private static final long serialVersionUID = -4095350033440825242L;

    private InditexHeader header;

    private T body;

    public InditexHeader getHeader() {
        return header;
    }

    public void setHeader(InditexHeader header) {
        this.header = header;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Message{[HEADER]:").append(header);
        sb.append("|[BODY]:").append(body);
        sb.append("}");
        return sb.toString();
    }

}
