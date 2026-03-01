package com.calluminglis.pubatlas.dto;

import java.util.Map;

public class StatsResponse {
    public long totalVisited;
    public long totalWant;
    public long totalOverall;
    public Map<String, Long> areas;
    public Double averageRating;
}
