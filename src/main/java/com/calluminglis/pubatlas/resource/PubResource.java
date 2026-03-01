package com.calluminglis.pubatlas.resource;

import com.calluminglis.pubatlas.domain.Pub;

import com.calluminglis.pubatlas.dto.GeocodeBackfillResponse;
import com.calluminglis.pubatlas.dto.GeocodeResult;
import com.calluminglis.pubatlas.service.GeocodingService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.ClientWebApplicationException;
import org.jboss.logging.Logger;

@Tag(name = "Pubs")
@Path("/pubs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PubResource {
    private static final Logger LOG = Logger.getLogger(PubResource.class);

    @Inject
    GeocodingService geocodingService;

    @GET
    @Operation(summary = "Get all pubs")
    public List<Pub> getAll() {
        return Pub.listAll();
    }

    @POST
    @Operation(summary = "Create a new pub", description = "Creates a new pub, attempts to lookup coordinates if not provided.")
    @Transactional
    public Pub create(Pub pub) {
        try {
            GeocodeResult result = geocodingService.geocode(pub.name);
            if (result != null) {
                pub.latitude = result.latitude();
                pub.longitude = result.longitude();
            }
        } catch (ClientWebApplicationException ex) {
            int status = ex.getResponse() != null ? ex.getResponse().getStatus() : -1;

            if (status == 429) {
                LOG.warnf("Geocoding rate-limited (429) for pub '%s'. Saving without coordinates.", pub.name);
            } else {
                LOG.errorf(ex, "Geocoding HTTP error (status=%d) for pub '%s'. Saving without coordinates.", status, pub.name);
            }
        } catch (Exception ex) {
            LOG.errorf(ex, "Unexpected geocoding error for pub '%s'. Saving without coordinates.", pub.name);
        }

        pub.persist();
        return pub;
    }

    @POST
    @Path("/geocode-missing")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public GeocodeBackfillResponse geocodeMissing(@QueryParam("limit") @DefaultValue("20") int limit) {
        int safeLimit = Math.max(1, Math.min(limit, 20));
        var pubs = Pub.findMissingCoordinates(safeLimit);

        int updated = 0;

        for (Pub pub : pubs) {
            try {
                GeocodeResult result = geocodingService.geocode(pub.name);
                if (result != null) {
                    pub.latitude = result.latitude();
                    pub.longitude = result.longitude();
                    updated++;
                } else {
                    LOG.errorf("Unexpected geocoding error for pub '%s'. Saving without coordinates.", pub.name);
                }
                Thread.sleep(1100);
            } catch (ClientWebApplicationException ex) {
                int status = ex.getResponse() != null ? ex.getResponse().getStatus() : -1;
                if (status == 429) {
                    LOG.warnf("Geocoding rate-limited (429) for pub '%s'. Saving without coordinates.", pub.name);
                    break;
                }
            } catch (Exception ex) {
                LOG.errorf("Unhandled exception processing %s", pub.name);
                continue;
            }
        }

        return new GeocodeBackfillResponse(updated);
    }
}