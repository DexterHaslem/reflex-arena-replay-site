package com.dmh.reflex.db.dtos;

/**
 * Created by Dexter on 7/20/2016.
 */
// this matches the raw replay header info
public class ReplayInfoPlayerDTO {
    private String name;
    private int score;
    private int team;
    private long steamId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public long getSteamId() {
        return steamId;
    }

    public void setSteamId(long steamId) {
        this.steamId = steamId;
    }
}
