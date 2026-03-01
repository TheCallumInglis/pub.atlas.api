package com.calluminglis.pubatlas.client;

import com.calluminglis.pubatlas.dto.NominatimResult;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

// Nominatim uses OpenStreetMap data to find locations on Earth by name and address (geocoding).
// It can also do the reverse, find an address for any location on the planet.
// Reference: https://nominatim.org/
@Path("/search")
@RegisterRestClient(configKey = "nominatim")
@Produces(MediaType.APPLICATION_JSON)
public interface NominatimClient {

    @GET
    List<NominatimResult> search(
            @QueryParam("q") String query,
            @QueryParam("format") String format,
            @QueryParam("limit") int limit
    );
}
