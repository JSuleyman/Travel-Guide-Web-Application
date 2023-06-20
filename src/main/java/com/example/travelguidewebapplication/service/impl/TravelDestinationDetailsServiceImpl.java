package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.response.PlacesToVisitDetailsResponseDTO;
import com.example.travelguidewebapplication.model.ImageData;
import com.example.travelguidewebapplication.model.TravelDestinationDetails;
import com.example.travelguidewebapplication.repository.TravelDestinationDetailsRepository;
import com.example.travelguidewebapplication.repository.StorageRepository;
import com.example.travelguidewebapplication.service.inter.TravelDestinationDetailsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelDestinationDetailsServiceImpl implements TravelDestinationDetailsService {

    private final TravelDestinationDetailsRepository travelDestinationDetailsRepository;
    private final StorageRepository storageRepository;


    @Override
    public void save(TravelDestinationDetails destinationDetails) {
        travelDestinationDetailsRepository.save(destinationDetails);
    }

    @Override
    @Transactional
    public PlacesToVisitDetailsResponseDTO getDetailsByPlacesId(String destinationId) {
        List<String> imageUrls = new ArrayList<>();
        List<ImageData> imageData = storageRepository.findByTravelDestinationId_Id(destinationId);
        TravelDestinationDetails travelDestinationDetails = travelDestinationDetailsRepository.findByTravelDestinationId(destinationId);

        for (ImageData imageData1 : imageData) {
            imageUrls.add(imageData1.getName());
        }

        PlacesToVisitDetailsResponseDTO placesToVisitDetailsResponseDTO = PlacesToVisitDetailsResponseDTO.builder()
                .travelDestinationId(destinationId)
                .userComments(travelDestinationDetails.getTravelDestinationDescription())
                .events(travelDestinationDetails.getEvents())
                .status(travelDestinationDetails.getTravelDestination().getStatus())
                .id(travelDestinationDetails.getId())
                .imageUrls(imageUrls)
                .build();


        return placesToVisitDetailsResponseDTO;
    }

    @Override
    public TravelDestinationDetails getById(String id) {
        return travelDestinationDetailsRepository.findById(id).orElseThrow();
    }
}
