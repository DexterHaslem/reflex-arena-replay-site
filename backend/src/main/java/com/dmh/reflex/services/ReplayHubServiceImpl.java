package com.dmh.reflex.services;

import com.dmh.reflex.db.dtos.ReplayInfoDTO;
import com.dmh.reflex.db.dtos.ReplayInfoPlayerDTO;
import com.dmh.reflex.db.entities.*;
import com.dmh.reflex.db.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
    private FileInfoRepository fileInfoRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerNameRepository playerNameJoinRepository;

    @Autowired
    private PlayerGameRepository playerGameJoinRepository;


    @Override
    @Transactional
    public boolean addParsedReplay(ReplayInfoDTO newReplay) {
        // order is intentional - this is slightly tricky due to constraints

        // TODO: validation

        // make sure we dont upload the same replay.
        // file names have a timestamp and are somewhat unique
        try {
            List<FileInfo> fileInfos = fileInfoRepository.findByName(newReplay.getFileName());
            if (!fileInfos.isEmpty()) {
                // already uploaded
                return false;
            }
            //Assert.isTrue(fileInfos.isEmpty());

            Map map = null;
            long mapWorkshopId = newReplay.getMapWorkshopId();
            if (mapWorkshopId == 0) {
                // old header or no id, try to find by name
                List<Map> mapsByName = mapRepository.findByName(newReplay.getMapName());
                if (!mapsByName.isEmpty()) {
//                    if (mapsByName.size() > 1) {
//                        System.out.println("Dup maps by name: " + newReplay.getMapName() + mapsByName.toArray());
//                    }
                    map = mapsByName.get(0); // if we somehow end up w/ dups go with first
                    Assert.isTrue(mapsByName.size() < 2);
                }
            } else {
                List<Map> mapsByWorkshopId = mapRepository.findByWorkshopId(mapWorkshopId);
                if (!mapsByWorkshopId.isEmpty()) {
//                    if (mapsByWorkshopId.size() > 1) {
//                        System.out.println("Dup map by wid: " + newReplay.getMapName());
//                    }

                    map = mapsByWorkshopId.get(0);

                    // while we're here, find any maps by same name that were created w/o workshop id
                    // and delete them to prevent duplicates
                    if (map != null) {
                        List<Map> mapsByName = mapRepository.findByName(newReplay.getMapName());
                        List<Map> toDelete = mapsByName.stream().filter(m -> m.getWorkshopId() < 1).collect(Collectors.toList());
                        mapRepository.delete(toDelete);
                    }
                }
            }

            if (map == null) {
                map = new Map();
                map.setId(UUID.randomUUID());
                map.setName(newReplay.getMapName());
                map.setWorkshopId(newReplay.getMapWorkshopId());
                mapRepository.save(map);
            }

            // create a game object, most other things point at it from here on out
            Game game = new Game();
            game.setTimestamp(newReplay.getTimestamp());
            game.setPlayerCount(newReplay.getPlayerCount());
            game.setMapId(map.getId());
            game.setHostName(newReplay.getHostName());
            game.setGameMode(newReplay.getGameMode());
            game.setId(UUID.randomUUID());
            gameRepository.save(game);

            // add file meta data
            // make file contents first if present
            // FILE CONTENTS moved to /addFile
            // well we always have to add something currnetly
            //File file = new File();
            //file.setId(UUID.randomUUID());
            //file.setContents(newReplay.getFileContents());
            //if (file.getContents() == null) {
            //    byte[] contents = {(byte) 255};
            //    file.setContents(contents);
            //}

            //fileRepository.save(file);

            FileInfo newFileInfo = new FileInfo();
            newFileInfo.setId(UUID.randomUUID());
            newFileInfo.setName(newReplay.getFileName());
            newFileInfo.setGameId(game.getId());
            //newFileInfo.setContentsId(file.getId());
            // dont rely on contents, they could have skipped upload
            newFileInfo.setSize(newReplay.getFileSize());
            fileInfoRepository.save(newFileInfo);

            // next do player stuff for all players present

            List<ReplayInfoPlayerDTO> players = newReplay.getPlayers();
            Assert.notNull(players);

            try {
                // TODO: assumes steam id is always present, check
                for (ReplayInfoPlayerDTO player : players) {
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
                    playerGame.setPlayerName(player.getName());
                    playerGame.setScore(player.getScore());
                    playerGame.setGameId(game.getId());
                    playerGame.setTeam(player.getTeam());
                    playerGameJoinRepository.save(playerGame);

                    // dont forget to check to update all aliases for this player
                    addPlayerAlias(player);
                }
            } catch (Exception ex) {
                return false;
            }

            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public String addFile(String filename, byte[] contents) {
        // first thing we need to do is find the metadata for given file
        // if it doesnt exist, thats when we would parse server side (TODO)

        List<FileInfo> fileInfos =  fileInfoRepository.findByName(filename);
        if (fileInfos.isEmpty()){
            // PARSE HERE TODO
            return "";
        }
        FileInfo fileInfo = fileInfos.get(0);

        // we shouldnt end up with more than one file info per replay
        Assert.isTrue(fileInfos.size() == 1);

        File dbFile = new File();
        dbFile.setId(UUID.randomUUID());
        dbFile.setContents(contents);

        fileRepository.save(dbFile);

        fileInfo.setContentsId(dbFile.getId());
        fileInfo.setSize(contents.length);
        fileInfoRepository.save(fileInfo);

        return "OK";
    }

    private void addPlayerAlias(ReplayInfoPlayerDTO player) {
        // see if we have any for current name, if not add
        List<PlayerName> playerNames = playerNameJoinRepository.findByPlayerSteamId(player.getSteamId());
        // if its empty, first time seeing this player, so we know we can add
        // else we check if its just a new alias
        if (playerNames.isEmpty() || playerNames.stream().allMatch(pn -> !pn.getName().equals(player.getName()))) {
            PlayerName newAlias = new PlayerName();
            newAlias.setId(UUID.randomUUID());
            newAlias.setPlayerSteamId(player.getSteamId());
            newAlias.setName(player.getName());
            playerNameJoinRepository.save(newAlias);
        }
    }
}
