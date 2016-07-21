package com.dmh.reflex.services;

import com.dmh.reflex.db.dtos.ReplayInfoDTO;
import com.dmh.reflex.db.dtos.ReplayInfoPlayerDTO;
import com.dmh.reflex.db.entities.*;
import com.dmh.reflex.db.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by Dexter on 7/20/2016.
 */
@Service
public class ReplayHubServiceImpl implements ReplayHubService {

    @Autowired
    private MapRepository mapRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private FileInfoRepository fileInfoJoinRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerNameRepository playerNameJoinRepository;

    @Autowired
    private PlayerGameRepository playerGameJoinRepository;


    @Override
    public ReplayInfoDTO addReplay(byte[] replayFile) {
        return null;
    }

    @Override
    public boolean addParsedReplay(ReplayInfoDTO newReplay) {
        // order is intentional - this is slightly tricky due to constraints

        // TODO: validation

        // make sure we dont upload the same replay.
        // file names have a timestamp and are somewhat unique
        List<FileInfo> fileInfos = fileInfoJoinRepository.findByName(newReplay.getFileName());
        Assert.isTrue(fileInfos.isEmpty());

        Map map = mapRepository.findOne(newReplay.getMapWorkshopId());
        if (map == null) {
            // TODO: assumes no maps of same name exist without map workshop id for now
            // which is possible because it was added in later replay header proto
            map = new Map();
            map.setName(newReplay.getMapName());
            map.setWorkshopId(newReplay.getMapWorkshopId());
            mapRepository.save(map);
        }

        // create a game object, most other things point at it from here on out
        Game game = new Game();
        game.setTimestamp(newReplay.getTimestamp());
        game.setPlayerCount(newReplay.getPlayerCount());
        game.setMapId(map.getWorkshopId());
        game.setHostName(newReplay.getHostName());
        game.setGameMode(newReplay.getGameMode());
        game.setId(UUID.randomUUID());
        gameRepository.save(game);

        // add file meta data
        // make file contents first if present
        // well we always have to add something currnetly
        File file = new File();
        file.setId(UUID.randomUUID());
        file.setContents(newReplay.getFileContents());
        if (file.getContents() == null) {
            byte[] contents = {(byte) 255};
            file.setContents(contents);
        }

        fileRepository.save(file);

        FileInfo newFileInfo = new FileInfo();
        newFileInfo.setId(UUID.randomUUID());
        newFileInfo.setName(newReplay.getFileName());
        newFileInfo.setGameId(game.getId());
        newFileInfo.setContentsId(file.getId());
        // dont rely on contents, they could have skipped upload
        newFileInfo.setSize(newReplay.getFileSize());
        fileInfoJoinRepository.save(newFileInfo);

        // next do player stuff for all players present

        List<ReplayInfoPlayerDTO> players = newReplay.getPlayers();
        Assert.notNull(players);

        // TODO: assumes steam id is always present, check
        for(ReplayInfoPlayerDTO player : players) {
            Player playerEnt = playerRepository.findOne(player.getSteamId());
            if (playerEnt == null) {
                playerEnt = new Player();
                playerEnt.setSteamId(player.getSteamId());
                playerRepository.save(playerEnt);
            }

            // create game join for this specific game
            PlayerGame playerGame = new PlayerGame();
            playerGame.setId(UUID.randomUUID());
            playerGame.setPlayerSteamId(player.getSteamId());
            playerGame.setScore(player.getScore());
            playerGame.setGameId(game.getId());
            playerGame.setTeam(player.getTeam());
            playerGameJoinRepository.save(playerGame);

            // dont forget to check to update all aliases for this player
            AddPlayerAlias(player);
        }

        return true;
    }

    private void AddPlayerAlias(ReplayInfoPlayerDTO player) {
        // see if we have any for current name, if not add
        List<PlayerName> playerNames = playerNameJoinRepository.findByPlayerSteamId(player.getSteamId());
        // if its empty, first time seeing this player, so we know we can add
        // else we check if its just a new alias
        if (playerNames.isEmpty() || playerNames.stream().allMatch(pn -> !pn.getName().equals(player.getName()))){
            PlayerName newAlias = new PlayerName();
            newAlias.setId(UUID.randomUUID());
            newAlias.setPlayerSteamId(player.getSteamId());
            newAlias.setName(player.getName());
            playerNameJoinRepository.save(newAlias);
        }
    }
}
