package com.calluminglis.pubatlas.service;

import com.calluminglis.pubatlas.dto.GeocodeResult;

public interface GeocodingService {
    GeocodeResult geocode(String name);
}
