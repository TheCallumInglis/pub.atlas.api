package com.calluminglis.pubatlas.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/healthcheck")
public class HealthCheckResource {

    public record HealthResponse(String status) {}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HealthResponse healthCheck() {
        return new HealthResponse("ok");
    }
}
