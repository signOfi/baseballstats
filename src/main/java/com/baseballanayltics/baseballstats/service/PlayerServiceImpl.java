package com.baseballanayltics.baseballstats.service;

import com.baseballanayltics.baseballstats.dao.PlayerRepository;
import com.baseballanayltics.baseballstats.entity.Player;
import com.baseballanayltics.baseballstats.exceptions.PlayerNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> getAllPlayers() {
        // Finds the list of all players
        return playerRepository.findAll();
    }

    @Override
    @Transactional
    public void updatePlayer(int id, Player player) {

        // First get player by id
        Optional<Player> playerOptional = getPlayerById(id);

        // Check if play exists
        if(playerOptional.isPresent()) {
            // Get Player then update ALL the fields from passed player
            Player dbPlayer = playerOptional.get();
            dbPlayer.updateFields(player);
            // Save player onto db
            playerRepository.save(dbPlayer);
        }
        // Else throw exception
        throw new PlayerNotFoundException("Cannot update player, player id: " + id + " not found...");

    }

    @Override
    @Transactional
    public void deletePlayer(int id) {

        /* Delete player by ID: If not found throw an exception */

        if (playerRepository.existsById(id)) {
            playerRepository.deleteById(id);
        }

        throw new PlayerNotFoundException("Player cannot be deleted since ID: " + id + " does not exist.");

    }

    @Override
    @Transactional
    public void createNewPlayer(Player player) {
        playerRepository.save(player);
    }

    @Override
    public Optional<Player> getPlayerById(int id) {
        // Find player by id
        Optional<Player> player = playerRepository.findById(id);

        // Check if play exists
        if (player.isPresent()) {
            return player;
        }

        // Else throw an exception
        throw new PlayerNotFoundException("Player is not found");
    }
}
