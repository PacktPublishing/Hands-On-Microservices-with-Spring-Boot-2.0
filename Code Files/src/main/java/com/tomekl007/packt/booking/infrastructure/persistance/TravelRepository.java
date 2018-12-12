package com.tomekl007.packt.booking.infrastructure.persistance;

import com.tomekl007.packt.booking.domain.Travel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TravelRepository extends CrudRepository<Travel, Long> {

    @Query("select t from Travel t where t.userId =:userId")
    List<Travel> findByUserId(@Param("userId") String userId);

    @Query("select t from Travel t where t.destination =:destination")
    List<Travel> findByDestination(@Param("destination") String destination);
}
