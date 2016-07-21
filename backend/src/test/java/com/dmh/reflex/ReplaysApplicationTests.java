package com.dmh.reflex;

import com.dmh.reflex.db.dtos.ReplayInfoDTO;
import com.dmh.reflex.db.dtos.ReplayInfoPlayerDTO;
import com.dmh.reflex.services.ReplayHubService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ReplaysApplication.class)
public class ReplaysApplicationTests {

	//@Test
    //public void get

    @Autowired
    private ReplayHubService replayHubService;

    @Test
    public void testInsertMockNewParsedReplay() {
        ReplayInfoDTO testDto = new ReplayInfoDTO();

        testDto.setFileName("dexTest.rep");
        testDto.setFileSize(13378);
        testDto.setGameMode("1v1");
        testDto.setHostName("cool server");
        testDto.setMapName("Phrantic");
        testDto.setMapWorkshopId(1234568900);
        testDto.setTimestamp(new Date());
        testDto.setPlayerCount(7);

        List<ReplayInfoPlayerDTO> testPlayers = new ArrayList<>();
        ReplayInfoPlayerDTO player = new ReplayInfoPlayerDTO();
        player.setName("dexter");
        player.setScore(444);
        player.setTeam(1);
        player.setSteamId(8888888);
        testPlayers.add(player);
        testDto.setPlayers(testPlayers);

        Assert.assertTrue(replayHubService.addParsedReplay(testDto));
    }
}
