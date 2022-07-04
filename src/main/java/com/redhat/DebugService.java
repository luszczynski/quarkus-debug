package com.redhat;

import javax.enterprise.context.ApplicationScoped;
import io.vertx.core.http.HttpServerRequest;
import javax.ws.rs.core.UriInfo;

/**
 * DebugService
 */
@ApplicationScoped
public class DebugService {

    private int counter = 0;

    public int increment() {
      return this.counter++;
    }

    public Debug getDebug(HttpServerRequest request, UriInfo info) {
      Debug debug = new Debug();
      debug = getCommomParams(request, info, debug);
      
      return debug;
    }

    public Debug getDebug(HttpServerRequest request, UriInfo info, String body) {
      Debug debug = new Debug();
      debug = getCommomParams(request, info, debug);

      debug.body = body;
      return debug;
    }

    private Debug getCommomParams(HttpServerRequest request, UriInfo info, Debug debug) {
      if(debug == null)
        debug = new Debug();
      
      debug.path = info.getPath();
      debug.pathParams = info.getPathParameters();
      debug.queryParams = info.getQueryParameters();
      debug.cookiesCount = request.cookieCount();
      debug.host = request.host();
      debug.cookieMap = request.cookieMap();
      debug.method = request.method();
      debug.uri = request.uri();
      debug.absoluteURI = request.absoluteURI();
      debug.remoteAddress = request.remoteAddress().toString();
      debug.headers = request.headers();
      debug.ssl = request.connection().isSsl();
      debug.headersCount = request.headers().size();

      debug.count = this.counter++;

      return debug;
    }
}