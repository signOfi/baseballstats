package com.baseballanayltics.baseballstats.service;

import com.baseballanayltics.baseballstats.dao.PlayerRepository;
import com.baseballanayltics.baseballstats.entity.Player;

import java.util.*;

public interface PlayerService {

    List<Player> getAllPlayers();

    void updatePlayer(int id, Player player);

    void deletePlayer(int id);

    void createNewPlayer(Player player);

    Optional<Player> getPlayerById(int id);

}
