package com.dmh.reflex.db.repositories;

import com.dmh.reflex.db.entities.PlayerName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by Dexter on 7/20/2016.
 */
public interface PlayerNameRepository extends JpaRepository<PlayerName, UUID> {
    List<PlayerName> findByName(String name);
    List<PlayerName> findByPlayerSteamId(long playerSteamId);
}
