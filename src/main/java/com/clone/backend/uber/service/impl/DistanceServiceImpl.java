package com.clone.backend.uber.service.impl;

import com.clone.backend.uber.service.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceImpl implements DistanceService {
    @Override
    public double calculateDistance(Point src, Point dest) {
        // TODO Call OSRM API to fetch the Distance
        return 0;
    }
}
