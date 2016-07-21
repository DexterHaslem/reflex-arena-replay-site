package com.dmh.reflex.db.repositories;

import com.dmh.reflex.db.entities.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

/**
 * Created by Dexter on 7/20/2016.
 */
public interface FileInfoRepository extends JpaRepository<FileInfo, UUID> {
    List<FileInfo> findByName(String name);
}
