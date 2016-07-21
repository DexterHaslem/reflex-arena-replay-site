package com.dmh.reflex.db.dtos;

import java.util.Date;
import java.util.List;

/**
 * Created by Dexter on 7/20/2016.
 */
// this matches the raw replay header info
public class ReplayInfoDTO {

    private int playerCount;

    private Date timestamp;

    private String gameMode;

    private String mapName;

    private long mapWorkshopId;

    private String hostName;

    // additional stuff a client that parsed itself would pass in

    private String fileName;

    private int fileSize;

    private byte[] fileContents;

    private List<ReplayInfoPlayerDTO> players;

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public List<ReplayInfoPlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<ReplayInfoPlayerDTO> players) {
        this.players = players;
    }

    public long getMapWorkshopId() {
        return mapWorkshopId;
    }

    public void setMapWorkshopId(long mapWorkshopId) {
        this.mapWorkshopId = mapWorkshopId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getFileContents() {
        return fileContents;
    }

    public void setFileContents(byte[] fileContents) {
        this.fileContents = fileContents;
    }
}
