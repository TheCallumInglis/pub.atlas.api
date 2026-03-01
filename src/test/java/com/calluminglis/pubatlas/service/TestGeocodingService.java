package com.calluminglis.pubatlas.service;

import com.calluminglis.pubatlas.dto.GeocodeResult;
import jakarta.annotation.Priority;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;

@Alternative
@Priority(1)
@ApplicationScoped
public class TestGeocodingService implements GeocodingService {

    @Override
    public GeocodeResult geocode(String name) {
        return new GeocodeResult(55.9533, -3.1883);
    }
}
