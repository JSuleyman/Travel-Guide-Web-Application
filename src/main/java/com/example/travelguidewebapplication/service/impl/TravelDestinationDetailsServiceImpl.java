package com.example.travelguidewebapplication.service.impl;

import com.example.travelguidewebapplication.DTO.response.PlacesToVisitDetailsResponseDTO;
import com.example.travelguidewebapplication.model.ImageData;
import com.example.travelguidewebapplication.model.TravelDestinationDetails;
import com.example.travelguidewebapplication.repository.StorageRepository;
import com.example.travelguidewebapplication.repository.TravelDestinationDetailsRepository;
import com.example.travelguidewebapplication.service.inter.TravelDestinationDetailsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        List<String> imageUrls = getImageUrlsByDestinationId(destinationId);
        TravelDestinationDetails travelDestinationDetails = getTravelDestinationDetailsByDestinationId(destinationId);

        return buildPlacesToVisitDetailsResponseDTO(destinationId, travelDestinationDetails, imageUrls);
    }

    @Override
    public TravelDestinationDetails getById(String id) {
        return travelDestinationDetailsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TravelDestinationDetails not found for ID: " + id));
    }


    private List<String> getImageUrlsByDestinationId(String destinationId) {
        List<ImageData> imageDataList = storageRepository.findByTravelDestinationId_Id(destinationId);
        return imageDataList.stream()
                .map(ImageData::getName)
                .collect(Collectors.toList());
    }

    private TravelDestinationDetails getTravelDestinationDetailsByDestinationId(String destinationId) {
        return travelDestinationDetailsRepository.findByTravelDestinationId(destinationId);
    }

    private PlacesToVisitDetailsResponseDTO buildPlacesToVisitDetailsResponseDTO(String destinationId,
                                                                                 TravelDestinationDetails travelDestinationDetails,
                                                                                 List<String> imageUrls) {
        return PlacesToVisitDetailsResponseDTO.builder()
                .travelDestinationId(destinationId)
                .userComments(travelDestinationDetails.getTravelDestinationDescription())
                .events(travelDestinationDetails.getEvents())
                .status(travelDestinationDetails.getTravelDestination().getStatus())
                .id(travelDestinationDetails.getId())
                .imageUrls(imageUrls)
                .build();
    }
}

