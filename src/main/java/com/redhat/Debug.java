package com.redhat;

import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.MultivaluedMap;

import io.vertx.core.MultiMap;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpMethod;

/**
 * Debug
 */
@RequestScoped
public class Debug {
    public HttpMethod method;
    public String path;
    public String uri;
    public String args;
    public String body;
    public String remoteAddress;
    public String host;
    public MultiMap headers;
    public MultivaluedMap<String,String> pathParams;
    public MultivaluedMap<String, String> queryParams;
    public Map<String, Cookie> cookieMap;
    public String rawMethod;
    public Integer count;

    @Override
    public String toString() {
        return "Debug [args=" + args + ", body=" + body + ", cookieMap=" + cookieMap + ", count=" + count + ", headers="
                + headers + ", host=" + host + ", method=" + method + ", path=" + path + ", pathParams=" + pathParams
                + ", queryParams=" + queryParams + ", rawMethod=" + rawMethod + ", remoteAddress=" + remoteAddress
                + ", uri=" + uri + "]";
    }

    
}