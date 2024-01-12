package com.baseballanayltics.baseballstats.service;

import com.baseballanayltics.baseballstats.dao.PlayerRepository;
import com.baseballanayltics.baseballstats.entity.Player;
import com.baseballanayltics.baseballstats.exceptions.PlayerNotFoundException;
import com.baseballanayltics.baseballstats.exceptions.PlayerNotValidException;
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
    public void updatePlayer(int id, Player updatedPlayer) {

        /*
         * Find by id then 2 situations
         * Case 1: A Player is returned, so we update the fields and update the repository
         * Case II: Thrown new exception if the optional is empty.
         */

        playerRepository.findById(id).ifPresentOrElse(
                player -> {
                    player.updateFields(updatedPlayer);
                    playerRepository.save(player);
                },
                () -> {
                    throw new PlayerNotFoundException("Player with id " + id + " not found cannot update...");
                }
        );
    }

    @Override
    @Transactional
    public void deletePlayer(int id) {

        playerRepository.findById(id).ifPresentOrElse(
                player ->
                        playerRepository.deleteById(id),
                () -> {
                    throw new PlayerNotFoundException("Player with id: " + id + " not found, cannot delete");
                }
        );

    }

    @Override
    @Transactional
    public void createNewPlayer(Player player) {

            /* Required Fields */
            if (player.getName() == null || player.getAge() == null || player.getTeam() == null ) {
                throw new PlayerNotValidException("Player needs a name, age, and team");
            }

            // Else save if valid player creation
            playerRepository.save(player);
    }

    @Override
    public Optional<Player> getPlayerById(int id) {

        // an optional checks if id contains a player if not we throw an exception
        return Optional.ofNullable(playerRepository.findById(id)
                .orElseThrow(() -> new PlayerNotFoundException("Player with id " + id +  " not found")));
    }

}


