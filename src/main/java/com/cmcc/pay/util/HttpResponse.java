package com.cmcc.pay.util;

/**
 * Created by echo on 2016/10/27.
 */

/**
 * Created by ech0 on 2016/3/16.
 */
public class HttpResponse {

    private String content;

    private String protocolVersion;//HTTP/1.1 200 OK

    private int statusCode;//200

    private String reasonPhrase;//OK


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(String protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }
}
