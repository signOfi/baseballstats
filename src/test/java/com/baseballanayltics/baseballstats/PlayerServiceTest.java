package com.baseballanayltics.baseballstats;

import com.baseballanayltics.baseballstats.dao.PlayerRepository;
import com.baseballanayltics.baseballstats.entity.Player;
import com.baseballanayltics.baseballstats.exceptions.PlayerNotFoundException;
import com.baseballanayltics.baseballstats.exceptions.PlayerNotValidException;
import com.baseballanayltics.baseballstats.service.PlayerServiceImpl;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Log
public class PlayerServiceTest {

    @Autowired
    ApplicationContext context;

    @MockBean
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerServiceImpl playerService;

    private Player player1;
    private Player player2;
    private Player player3;

    @BeforeEach
    public void setUp() {

        /*
         * Setup before each test
         * Sets up 3 players in the database
         */

        /* Create test players */
        player1 = new Player(1, "Anthony Lam", "Dodgers",
                "P", 25, 1, 15, 50,
                100, 20, 5, 15, 50,
                15, 5, 60, 80, 3,
                4, 2, 0.333, 0.400, 150.5,
                0.500
        );

        player2 = new Player(2, "Jake Den", "Angels",
                "P", 30, 10, 25, 10,
                25, 10, 3, 5, 5,
                6, 5, 60, 80, 3,
                3, 2, 0.944, 0.510, 17.5,
                0.320
        );

        player3 = new Player(3, "Mark Cuban", "Rangers",
                "P", 63, 1, 2, 1,
                2, 3, 1, 0, 1,
                3, 1, 12, 0, 2,
                3, 2, 0.144, 0.110, 7.5,
                0.101
        );

        // Populate playerRepository with 3 players, MOCK
        when(playerRepository.findAll())
                .thenReturn(Arrays.asList(player1,player2,player3));

    }

    @Test
    @DisplayName("Get all Players")
    public void testGetAllPlayers() {

        // Get List of players from service, service calls on repository
        List<Player> players = playerService.getAllPlayers();

        // Check if we have 3 players
        assertEquals(3, players.size());

    }

    @Test
    @DisplayName("Delete a player, id D.N.E")
    public void testDeletePlayerDoesNotExist() {

        // Invalid Id
        int invalidId = -5;

        // When an invalid id is searched then an empty is returned
        when(playerRepository.findById(invalidId))
                .thenReturn(Optional.empty());

        // simulate when the service is being handled with the exception
        assertThrows(PlayerNotFoundException.class,
                () -> playerService.deletePlayer(invalidId));

        // check if delete was never invoked since we just throw an exception instead
        verify(playerRepository, never()).deleteById(invalidId);
    }

    @Test
    @DisplayName("Delete a player with a valid ID")
    public void testDeletePlayerValidId() {
        // Valid User ID
        int validId = 2;

        // ID is found with the matching player in this case player2
        when(playerRepository.findById(validId))
                .thenReturn(Optional.of(player2));

        // Delete the player
        playerService.deletePlayer(validId);

        // Check if it worked
        verify(playerRepository).deleteById(validId);
    }

    @Test
    @DisplayName("Update Valid Player")
    public void testUpdatePlayer() {

        /*
         * Goal of this method is to change the name of player 2
         * From Jake Den -> Jake Denver
         */

        // Valid user ID
        int validId = 2;

        // Set up the DB with player 2 in it and save
        when(playerRepository.findById(validId))
                .thenReturn(Optional.of(player2));

        // Changing contents of player2
        player2.setName("Jake Denver");

        // Updates the player using player service
        playerService.updatePlayer(validId, player2);

        assertEquals("Jake Denver", player2.getName());

        /* Need to check if it saves onto the repository */
        verify(playerRepository).save(player2);

    }

    @Test
    @DisplayName("Update Player with bad id")
    public void testUpdatePlayerWithBadId() {

        int invalidId = 10;

        // An invalid id should return an empty optional container
        when(playerRepository.findById(invalidId))
                .thenReturn(Optional.empty());

        // Throw exception for updating invalid id
        assertThrows(
                PlayerNotFoundException.class,
                () -> playerService.updatePlayer(invalidId, player2)
        );
    }

    @Test
    @DisplayName("Create a new Player")
    public void testCreateNewPlayer() {

        // Mock repository with save
        when(playerRepository.save(player2))
                .thenReturn(player2);

        // Create new player with service
        playerService.createNewPlayer(player2);

        // Check if save was called
        verify(playerRepository).save(player2);
    }

    @Test
    @DisplayName("Create a new player that's empty")
    public void testCreateInvalidPlayer() {

        // Create an empty player validation
        Player player = new Player();

        assertThrows(
                PlayerNotValidException.class,
                () -> playerService.createNewPlayer(player)
        );

        // Give a name, and team, missing age so still should throw an exception
        player.setTeam("Dodgers");
        player.setName("John F.");

        assertThrows(
                PlayerNotValidException.class,
                () -> playerService.createNewPlayer(player)
        );

    }

    @Test
    @DisplayName("Get a single player by id")
    public void testGetSinglePlayerById() {

        // Valid ID 3 corresponds with player3 object
        int validId = 3;

        // ID:3 is matched with player 3 of type optional
        when(playerRepository.findById(validId))
                .thenReturn(Optional.of(player3));

        // Check if they are equal
        assertEquals(Optional.of(player3), playerService.getPlayerById(validId));

    }

    @Test
    @DisplayName("Invalid ID for getting a single player")
    public void testInvalidGetId() {

        // Invalid id
        int invalidId = -5;

        // Call repository given invalid id, then return an empty container
        when(playerRepository.findById(invalidId))
                .thenReturn(Optional.empty());

        // Throw a exception since we have an empty()
        assertThrows(
                PlayerNotFoundException.class,
                () -> playerService.getPlayerById(invalidId)
        );
    }

}
