package com.clone.uber.service.impl;

import com.clone.uber.service.DistanceService;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {
    @Override
    public double calculateDistance(Point src, Point dest) {
        //TODO Call OSRM Third Party API to calculate Distance
        return 0;
    }
}
