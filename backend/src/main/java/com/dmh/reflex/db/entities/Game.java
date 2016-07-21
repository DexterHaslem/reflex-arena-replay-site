package com.dmh.reflex.db.entities;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
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
    private Date timestamp;

    @Column(name="player_count")
    private int playerCount;

    @Column(name="map_id")
    private long mapId;

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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public long getMapId() {
        return mapId;
    }

    public void setMapId(long mapId) {
        this.mapId = mapId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }
}
