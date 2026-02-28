package com.calluminglis.pubatlas.resource;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/healthcheck")
public class HealthCheckResource {

    @ConfigProperty(name = "APP_VERSION", defaultValue = "dev")
    private String appVersion;
    
    public record HealthResponse(String status, String version) {}

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HealthResponse healthCheck() {
        return new HealthResponse("ok", appVersion);
    }
}
