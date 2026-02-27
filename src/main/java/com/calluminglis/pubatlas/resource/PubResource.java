package com.calluminglis.pubatlas.resource;

import com.calluminglis.pubatlas.domain.Pub;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/pubs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PubResource {

    @GET
    public List<Pub> getAll() {
        return Pub.listAll();
    }

    @POST
    @Transactional
    public Pub create(Pub pub) {
        pub.persist();
        return pub;
    }
}