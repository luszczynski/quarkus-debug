package com.redhat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    public Response helloGet (
        @QueryParam("fixedDelay") Long fixedDelay,
        @QueryParam("randomDelayBegin") Long randomDelayBegin,
        @QueryParam("randomDelayEnd") Long randomDelayEnd,
        @QueryParam("statusCode") Integer statusCode,
        @QueryParam("processLargeJson") Boolean processLargeJson,
        @QueryParam("hangIndefinitely") Boolean hangIndefinitely
        ) throws InterruptedException, IOException {
        if(hangIndefinitely != null && hangIndefinitely)
            fixedDelay = Long.MAX_VALUE;
        
        if(fixedDelay != null)
            Thread.sleep(fixedDelay);
        
        if(randomDelayBegin != null && randomDelayEnd != null) {
            Long randomDelay = (long) (Math.random() * (randomDelayBegin - randomDelayEnd)) + randomDelayEnd;
            Thread.sleep(randomDelay);
        }

        Debug debug = debugService.getDebug(request, info);

        if(processLargeJson != null && processLargeJson) {
            String largeJsonContent;
            try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("large.json");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                largeJsonContent = reader.lines().collect(Collectors.joining(System.lineSeparator()));
                debug.largeJson = largeJsonContent;
            }
        }

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

        Debug debug = debugService.getDebug(request, info, body);

        LOG.info(debug.toString());

        if(statusCode != null)
            return Response.status(statusCode).entity(debug).build();
        else 
            return Response.status(200).entity(debug).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response helloPut(
        @QueryParam("delay") Long delay,
        @QueryParam("statusCode") Integer statusCode,
        String body
    ) throws InterruptedException {
        if(delay != null)
            Thread.sleep(delay);

        Debug debug = debugService.getDebug(request, info, body);

        LOG.info(debug.toString());

        if(statusCode != null)
            return Response.status(statusCode).entity(debug).build();
        else 
            return Response.status(200).entity(debug).build();
    }

    @PATCH
    @Produces(MediaType.APPLICATION_JSON)
    public Response helloPatch(
        @QueryParam("delay") Long delay,
        @QueryParam("statusCode") Integer statusCode,
        String body
    ) throws InterruptedException {
        if(delay != null)
            Thread.sleep(delay);

        Debug debug = debugService.getDebug(request, info, body);

        LOG.info(debug.toString());

        if(statusCode != null)
            return Response.status(statusCode).entity(debug).build();
        else 
            return Response.status(200).entity(debug).build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response helloDelete(
        @QueryParam("delay") Long delay,
        @QueryParam("statusCode") Integer statusCode
        ) throws InterruptedException {
        if(delay != null)
            Thread.sleep(delay);

        Debug debug = debugService.getDebug(request, info);

        LOG.info(debug.toString());

        if(statusCode != null)
            return Response.status(statusCode).entity(debug).build();
        else 
            return Response.status(200).entity(debug).build();
    }
}