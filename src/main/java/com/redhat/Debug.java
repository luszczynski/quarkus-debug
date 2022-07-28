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
    public String absoluteURI;
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
    public Integer cookiesCount;
    public Boolean ssl;
    public Integer headersCount;
    public String largeJson;
    
    @Override
    public String toString() {
        return "Debug: \n\nabsoluteURI=" + absoluteURI + "\nargs=" + args + "\nbody=" + body + "\ncookieMap=" + cookieMap
                + "\ncookiesCount=" + cookiesCount + "\ncount=" + count + "\nheaders=" + headers + "\nheadersCount="
                + headersCount + "\nhost=" + host + "\nlargeJson=" + largeJson + "\nmethod=" + method + "\npath=" + path
                + "\npathParams=" + pathParams + "\nqueryParams=" + queryParams + "\nrawMethod=" + rawMethod
                + "\nremoteAddress=" + remoteAddress + "\nssl=" + ssl + "\nuri=" + uri;
    }
}