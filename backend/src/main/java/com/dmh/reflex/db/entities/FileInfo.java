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
@Table(name="file_info", schema="replays")
public class FileInfo {

    @Id
    @Column(name="id", unique = true, nullable = false)
    @Type(type = "pg-uuid")
    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="game_id")
    @Type(type = "pg-uuid")
    private UUID gameId;

    @Column(name="contents_id")
    @Type(type = "pg-uuid")
    private UUID contentsId;

    @Column(name="size")
    private int size;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getGameId() {
        return gameId;
    }

    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    public UUID getContentsId() {
        return contentsId;
    }

    public void setContentsId(UUID contentsId) {
        this.contentsId = contentsId;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
