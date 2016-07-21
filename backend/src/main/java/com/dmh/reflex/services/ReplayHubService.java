package com.dmh.reflex.services;

import com.dmh.reflex.db.dtos.ReplayInfoDTO;
import org.springframework.stereotype.Service;

/**
 * Created by Dexter on 7/20/2016.
 */
public interface ReplayHubService {
    ReplayInfoDTO addReplay(byte[] replayFile);
    boolean addParsedReplay(ReplayInfoDTO newReplay);
}
