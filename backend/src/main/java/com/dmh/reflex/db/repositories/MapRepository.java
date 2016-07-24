package com.dmh.reflex.db.repositories;

import com.dmh.reflex.db.entities.Map;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by Dexter on 7/20/2016.
 */
public interface MapRepository extends JpaRepository<Map, Long> {
    List<Map> findByName(String name);
    List<Map> findByWorkshopId(long workshopId);
}
