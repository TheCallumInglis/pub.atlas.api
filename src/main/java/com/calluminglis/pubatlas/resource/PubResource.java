package com.calluminglis.pubatlas.resource;

import com.calluminglis.pubatlas.domain.Pub;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Tag(name = "Pubs")
@Path("/pubs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PubResource {

    @GET
    @Operation(summary = "Get all pubs")
    public List<Pub> getAll() {
        return Pub.listAll();
    }

    @POST
    @Operation(summary = "Create a new pub")
    @Transactional
    public Pub create(Pub pub) {
        pub.persist();
        return pub;
    }
}