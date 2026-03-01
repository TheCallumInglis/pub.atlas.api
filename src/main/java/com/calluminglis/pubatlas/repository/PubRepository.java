package com.calluminglis.pubatlas.repository;

import com.calluminglis.pubatlas.domain.Pub;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class PubRepository implements PanacheRepository<Pub> {

    public List<Pub> findMissingCoordinates(int limit) {
        return find("latitude is null or longitude is null")
                .page(Page.ofSize(limit))
                .list();
    }
}
