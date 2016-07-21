package com.dmh.reflex.db.entities;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

/**
 * Created by Dexter on 7/20/2016.
 */
@Entity
@Table(name="game", schema="replays")
public class Game {

    @Id
    @Column(name="id", unique = true, nullable = false)
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name="timestamp")
    private long timestamp;

    @Column(name="player_count")
    private int playerCount;

    @Column(name="map_id")
    private UUID mapId;

    @Column(name="host_name")
    private String hostName;

    @Column(name="game_mode")
    private String gameMode;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
