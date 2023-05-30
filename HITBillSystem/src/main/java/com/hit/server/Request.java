package com.hit.server;

import java.io.Serializable;
import java.util.Map;

public class Request implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String, String> headers;
    private Map<String, String> body;

    public Request(Map<String, String> headers, Map<String, String> body) {
        this.headers = headers;
        this.body = body;
    }

    public Map<String, String> getBody() {
        return this.body;
    }

    public void addToBody(String key,String value) {
        this.body.put(key,value);
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public String getAction(){
        return this.headers.get("action");
    }
    public String getType(){
        return this.headers.get("type");
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String toString() {
        return "Request [headers=" + this.headers + ", body=" + this.body + "]";
    }
}
