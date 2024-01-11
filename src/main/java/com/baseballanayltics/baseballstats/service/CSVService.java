package com.baseballanayltics.baseballstats.service;

import com.baseballanayltics.baseballstats.dao.PlayerRepository;
import com.baseballanayltics.baseballstats.entity.Player;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@Service
@Log
public class CSVService {
    private final PlayerRepository playerRepository;
    private final ResourceLoader resourceLoader;

    @Autowired
    public CSVService(PlayerRepository playerRepository, ResourceLoader resourceLoader) {
        this.playerRepository = playerRepository;
        this.resourceLoader = resourceLoader;
    }

    public void getCsvData(String fileName) {
        Resource resource = resourceLoader.getResource("classpath:" + fileName);

        // Use try-with-resources for AutoCloseable resources
        try (Reader reader = new InputStreamReader(resource.getInputStream());
             CSVReader csvReader = new CSVReader(reader)) {


            // Skips first line b/c it contains column names
            csvReader.readNext();

            // Empty array of strings to save
            String[] nextLine;

            // Loop until line is null, assume valid CSV input
            while ((nextLine = csvReader.readNext()) != null) {

                // Create a new instance of a player
                Player player = new Player();

                // Set fields for each player
                player.setName(nextLine[0]);
                player.setTeam(nextLine[1]);
                player.setPos(nextLine[2]);
                player.setAge(Integer.parseInt(nextLine[3]));
                player.setGamesPlayed(Integer.parseInt(nextLine[4]));
                player.setAtBats(Integer.parseInt(nextLine[5]));
                player.setRunsScoredAllowed(Integer.parseInt(nextLine[6]));
                player.setHitsHitsAllowed(Integer.parseInt(nextLine[7]));
                player.setDoubles(Integer.parseInt(nextLine[8]));
                player.setTriples(Integer.parseInt(nextLine[9]));
                player.setHomeRunsHit(Integer.parseInt(nextLine[10]));
                player.setRunBattedIn(Integer.parseInt(nextLine[11]));
                player.setStolenBases(Integer.parseInt(nextLine[12]));
                player.setCaughtStealing(Integer.parseInt(nextLine[13]));
                player.setBaseballOnWalks(Integer.parseInt(nextLine[14]));
                player.setStrikeOuts(Integer.parseInt(nextLine[15]));
                player.setShutOuts(Integer.parseInt(nextLine[16]));
                player.setSacrificeFlies(Integer.parseInt(nextLine[17]));
                player.setTimesHitByPitch(Integer.parseInt(nextLine[18]));
                player.setAverage(Double.parseDouble(nextLine[19]));
                player.setObp(Double.parseDouble(nextLine[20]));
                player.setTotalBasesAtBat(Double.parseDouble(nextLine[21]));
                player.setOnBaseSlug(Double.parseDouble(nextLine[22]));

                // Save each player to the repository
                try {
                    playerRepository.save(player);
                } catch (Exception e) {
                    log.info("Cannot save");
                }
            }
            // Exception Handling
        } catch (IOException | CsvValidationException e) {
            log.info("There is an error with this provided csv file" + e.getMessage());
        }
    }
}