package com.ustglobal.inditex.etc;


public class Constants {

    private Constants() {
    }

    public static final String MEDIATYPE_APPLICATION_JSON = "application/json";
    public static final String MEDIATYPE_APPLICATION_MSGPACK = "application/x-msgpack";

    public static String toMediaType(String type) {
        if ("json".equals(type))
            return MEDIATYPE_APPLICATION_JSON;
        else
            return MEDIATYPE_APPLICATION_MSGPACK;
    }

}
