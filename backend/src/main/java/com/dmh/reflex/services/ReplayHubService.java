package com.dmh.reflex.services;

import com.dmh.reflex.db.dtos.ReplayInfoDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Dexter on 7/20/2016.
 */
public interface ReplayHubService {

    boolean addParsedReplay(ReplayInfoDTO newReplay);

    String addFile(String filename, byte[] contents);
}
