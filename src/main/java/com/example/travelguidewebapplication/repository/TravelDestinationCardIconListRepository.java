package com.example.travelguidewebapplication.repository;

import com.example.travelguidewebapplication.model.TravelDestinationCardIconList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelDestinationCardIconListRepository extends JpaRepository<TravelDestinationCardIconList, String> {
    @Query("""
            SELECT i.iconName FROM TravelDestinationCardIconList i 
            where i.travelDestination.id =:id
            """)
    List<String> findNameByTravelDestinationId(String id);
}
