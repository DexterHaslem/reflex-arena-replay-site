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
@Table(name="player_game", schema="replays")
public class PlayerGame {
    @Id
    @Column(name="id", unique = true, nullable = false)
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name="player_steam_id", unique = true, nullable = false)
    private long playerSteamId;

    @Column(name="game_id", unique = true, nullable = false)
    @Type(type = "pg-uuid")
    private UUID gameId;

    // maybe not the best idea, by sticking state in here we cant use @JoinTable and get these
    // guys easily
    @Column(name="score")
    private int score;

    @Column(name="team")
    private int team;

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

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }
}
