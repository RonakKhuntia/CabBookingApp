package com.clone.backend.uber.service.impl;

import com.clone.backend.uber.service.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceImpl implements DistanceService {

    private static final String OSRM_API_BASE_URL = "http://router.project-osrm.org/route/v1/driving/";

    @Override
    public double calculateDistance(Point src, Point dest) {
        try {
            DistanceServiceImpl.OsrmResponseDto osrmResponseDto = RestClient.builder()
                    .baseUrl(OSRM_API_BASE_URL)
                    .build()
                    .get()
                    .uri("%s,%s;%s,%s", src.getX(), src.getY(), dest.getX(), dest.getY())
                    .retrieve()
                    .body(DistanceServiceImpl.OsrmResponseDto.class);
            return osrmResponseDto.getRoutes().get(0).getDistance() / 1000.0;
        } catch (Exception e) {
            throw new RuntimeException(String.format("Error during OSRM API call %s", e.getMessage()));
        }
    }

    @Data
    static class OsrmResponseDto {
        private List<OsrmRoute> routes;
        @Data
        static class OsrmRoute {
            private Double distance;
        }
    }

}
