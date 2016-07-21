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
@Table(name="player_name", schema="replays")
public class PlayerName {

    @Id
    @Column(name="id", unique = true, nullable = false)
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name="player_steam_id", unique = true, nullable = false)
    private long playerSteamId;

    @Column(name="name")
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public long getPlayerSteamId() {
        return playerSteamId;
    }

    public void setPlayerSteamId(long playerSteamId) {
        this.playerSteamId = playerSteamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
