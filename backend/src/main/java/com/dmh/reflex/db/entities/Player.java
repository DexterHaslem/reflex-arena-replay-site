package com.dmh.reflex.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Dexter on 7/20/2016.
 */
@Entity
@Table(name="player", schema="replays")
public class Player {
    @Id
    @Column(name="steam_id", unique = true, nullable = false)
    private long steamId;

    public long getSteamId() {
        return steamId;
    }

    public void setSteamId(long steamId) {
        this.steamId = steamId;
    }
}
