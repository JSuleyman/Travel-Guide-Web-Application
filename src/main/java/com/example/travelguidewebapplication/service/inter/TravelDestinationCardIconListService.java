package com.example.travelguidewebapplication.service.inter;

import java.util.List;

public interface TravelDestinationCardIconListService {
    List<String> findNameByTravelDestinationId(String id);
}
