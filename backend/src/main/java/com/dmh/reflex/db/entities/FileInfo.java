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
    private long game_id;

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
}
