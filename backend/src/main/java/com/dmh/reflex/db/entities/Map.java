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
@Table(name="map", schema="replays")
public class Map {
    @Id
    @Column(name="workshop_id", unique = true, nullable = false)
    private long workshopId;

    @Column(name = "name")
    private String name;

    public long getWorkshopId() {
        return workshopId;
    }

    public void setWorkshopId(long workshopId) {
        this.workshopId = workshopId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
