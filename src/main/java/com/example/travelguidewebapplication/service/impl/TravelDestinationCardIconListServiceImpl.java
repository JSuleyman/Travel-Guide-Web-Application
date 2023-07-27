package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.repository.TravelDestinationCardIconListRepository;
import com.example.travelguidewebapplication.service.inter.TravelDestinationCardIconListService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelDestinationCardIconListServiceImpl implements TravelDestinationCardIconListService {
    private final TravelDestinationCardIconListRepository cardIconListRepository;

    @Override
    public List<String> findNameByTravelDestinationId(String id) {
        return cardIconListRepository.findNameByTravelDestinationId(id);
    }
}
