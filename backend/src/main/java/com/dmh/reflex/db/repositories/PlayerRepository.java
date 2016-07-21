package com.dmh.reflex.db.repositories;

import com.dmh.reflex.db.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by Dexter on 7/20/2016.
 */
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
