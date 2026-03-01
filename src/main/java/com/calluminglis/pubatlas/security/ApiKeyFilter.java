package com.calluminglis.pubatlas.security;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class ApiKeyFilter implements ContainerRequestFilter {

    @ConfigProperty(name = "app.api.key")
    String apiKey;

    @Override
    public void filter(ContainerRequestContext requestContext) {

        String method = requestContext.getMethod();

        if ("OPTIONS".equalsIgnoreCase(method) || "GET".equalsIgnoreCase(method)) {
            return;
        }

        String header = requestContext.getHeaderString("X-API-Key");

        if (header == null || !header.equals(apiKey)) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Missing or invalid API key")
                    .build());
        }
    }
}