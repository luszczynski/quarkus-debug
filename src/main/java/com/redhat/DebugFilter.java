package com.redhat;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import io.vertx.core.http.HttpServerRequest;

@Provider
public class DebugFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(DebugFilter.class);

    @Inject
    DebugService debugService;

    @Context
    UriInfo info;

    @Context
    HttpServerRequest request;

    @Override
    public void filter(ContainerRequestContext context) {

        final String method = context.getMethod();
        final String path = info.getPath();
        final String address = request.remoteAddress().toString();
        
        LOG.infof("\n\n================== REQUEST %s =====================", debugService.getCounter());
        LOG.infof("Request %s %s from IP %s", method, path, address);
        context.getHeaders().entrySet().forEach(System.out::println);
        debugService.increment();
    }
}