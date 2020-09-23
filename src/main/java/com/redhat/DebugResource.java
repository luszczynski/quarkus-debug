package com.redhat;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.logging.Logger;

import io.vertx.core.http.HttpServerRequest;

@Path("/debug")
public class DebugResource {
    private static final Logger LOG = Logger.getLogger(DebugResource.class);

    @Inject
    DebugService debugService;

    @Context
    UriInfo info;

    @Context
    HttpServerRequest request;

    @Inject
    Debug debug;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response helloGet(
        @QueryParam("delay") Long delay,
        @QueryParam("statusCode") Integer statusCode
        ) throws InterruptedException {
        if(delay != null)
            Thread.sleep(delay);

        debug.path = info.getPath();
        debug.remoteAddress = request.remoteAddress().toString();
        debug.headers = request.headers();
        debug.pathParams = info.getPathParameters();
        debug.queryParams = info.getQueryParameters();
        debug.cookieMap = request.cookieMap();
        debug.method = request.method();
        debug.uri = request.uri();
        debug.count = debugService.increment();

        LOG.info(debug.toString());

        if(statusCode != null)
            return Response.status(statusCode).entity(debug).build();
        else 
            return Response.status(200).entity(debug).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response helloPost(
        @QueryParam("delay") Long delay,
        @QueryParam("statusCode") Integer statusCode,
        String body
    ) throws InterruptedException {
        if(delay != null)
            Thread.sleep(delay);

        debug.path = info.getPath();
        debug.remoteAddress = request.remoteAddress().toString();
        debug.headers = request.headers();
        debug.pathParams = info.getPathParameters();
        debug.queryParams = info.getQueryParameters();
        debug.cookieMap = request.cookieMap();
        debug.method = request.method();
        debug.body = body;
        debug.uri = request.uri();
        debug.count = debugService.increment();

        LOG.info(debug.toString());

        if(statusCode != null)
            return Response.status(statusCode).entity(debug).build();
        else 
            return Response.status(200).entity(debug).build();
    }
}