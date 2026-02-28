package com.calluminglis.pubatlas.resource;

import java.util.HashMap;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import com.calluminglis.pubatlas.domain.Pub;
import com.calluminglis.pubatlas.domain.StatsResponse;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Tag(name = "Stats")
@Path("/stats")
@Produces(MediaType.APPLICATION_JSON)
public class StatsResource {
    @GET
    @Operation(summary = "Get statistics about visited pubs")
    public StatsResponse getStats() {
        StatsResponse response = new StatsResponse();

        // Placeholders
        response.totalVisited = 0;
        response.totalWant = 0;
        response.totalOverall = Pub.countAll();
        response.areas = new HashMap<String, Long>();
        response.averageRating = 0.0;

        return response;
    }
}
