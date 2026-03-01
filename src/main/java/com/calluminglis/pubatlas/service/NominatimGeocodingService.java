package com.calluminglis.pubatlas.service;

import com.calluminglis.pubatlas.client.NominatimClient;
import com.calluminglis.pubatlas.dto.GeocodeResult;
import com.calluminglis.pubatlas.dto.NominatimResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class NominatimGeocodingService implements GeocodingService {

    @Inject
    @RestClient
    NominatimClient nominatimClient;

    @Override
    public GeocodeResult geocode(String name) {
        if (name == null || name.isBlank()) return null;

        List<NominatimResult> results = nominatimClient.search(
                name + ", UK",
                "json",
                1
        );

        if (results == null || results.isEmpty()) return null;

        NominatimResult first = results.getFirst();
        return new GeocodeResult(
                Double.parseDouble(first.lat()),
                Double.parseDouble(first.lon())
        );
    }
}
